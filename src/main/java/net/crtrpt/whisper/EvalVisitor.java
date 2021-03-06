package net.crtrpt.whisper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.crtrpt.whisper.gen.WhisperLanguageBaseVisitor;
import net.crtrpt.whisper.gen.WhisperLanguageLexer;
import net.crtrpt.whisper.gen.WhisperLanguageParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class EvalVisitor extends WhisperLanguageBaseVisitor<TLValue> {
    private static ReturnValue returnValue = new ReturnValue();
    private Scope scope;
    private Map<String, Function> functions;
    //内建函数函数调用
    private Map<String, BuildInFunction> buildFunction = new HashMap<>();

    public EvalVisitor(Scope scope, Map<String, Function> functions) {
        this.scope = scope;
        this.functions = new HashMap<>(functions);
    }

    public EvalVisitor(Scope scope, Map<String, Function> functions, Map<String, BuildInFunction> buildinFunction) {
        this.scope = scope;
        this.functions = new HashMap<>(functions);
        this.buildFunction = buildinFunction;
    }
    


    // functionDecl
    @Override
    public TLValue visitFunctionDecl(WhisperLanguageParser.FunctionDeclContext ctx) {
        List<TerminalNode> params = ctx.idList() != null ? ctx.idList().Identifier() : new ArrayList<TerminalNode>();
        ParseTree block = ctx.block();
        String id = ctx.Identifier().getText() + params.size();
        // TODO: throw exception if function is already defined?
        functions.put(id, new Function(scope, params, block));
        return TLValue.VOID;
    }

    // list: '[' exprList? ']'
    @Override
    public TLValue visitList_Alias(WhisperLanguageParser.List_AliasContext ctx) {
        List<TLValue> list = new ArrayList<>();
        if (ctx.exprList() != null) {
            for (WhisperLanguageParser.ExpressionContext ex : ctx.exprList().expression()) {
                list.add(this.visit(ex));
            }
        }
        return new TLValue(list);
    }


    // '-' expression                           #unaryMinusExpression
    @Override
    public TLValue visitUnaryMinusExpression(WhisperLanguageParser.UnaryMinusExpressionContext ctx) {
        TLValue v = this.visit(ctx.expression());
        if (!v.isNumber()) {
            throw new EvalException(ctx);
        }
        return new TLValue(-1 * v.asDouble());
    }

    // '!' expression                           #notExpression
    @Override
    public TLValue visitNotExpression(WhisperLanguageParser.NotExpressionContext ctx) {
        TLValue v = this.visit(ctx.expression());
        if (!v.isBoolean()) {
            throw new EvalException(ctx);
        }
        return new TLValue(!v.asBoolean());
    }

    // expression '^' expression                #powerExpression
    @Override
    public TLValue visitPowerExpression(WhisperLanguageParser.PowerExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        if (lhs.isNumber() && rhs.isNumber()) {
            return new TLValue(Math.pow(lhs.asDouble(), rhs.asDouble()));
        }
        throw new EvalException(ctx);
    }

    // expression op=( '*' | '/' | '%' ) expression         #multExpression
    @Override
    public TLValue visitMultExpression(WhisperLanguageParser.MultExpressionContext ctx) {
        switch (ctx.op.getType()) {
            case WhisperLanguageLexer.Multiply:
                return multiply(ctx);
            case WhisperLanguageLexer.Divide:
                return divide(ctx);
            case WhisperLanguageLexer.Modulus:
                return modulus(ctx);
            default:
                throw new RuntimeException("unknown operator type: " + ctx.op.getType());
        }
    }

    // expression op=( '+' | '-' ) expression               #addExpression
    @Override
    public TLValue visitAddExpression(WhisperLanguageParser.AddExpressionContext ctx) {
        switch (ctx.op.getType()) {
            case WhisperLanguageLexer.Add:
                return add(ctx);
            case WhisperLanguageLexer.Subtract:
                return subtract(ctx);
            default:
                throw new RuntimeException("unknown operator type: " + ctx.op.getType());
        }
    }

    // expression op=( '>=' | '<=' | '>' | '<' ) expression #compExpression
    @Override
    public TLValue visitCompExpression(WhisperLanguageParser.CompExpressionContext ctx) {
        switch (ctx.op.getType()) {
            case WhisperLanguageLexer.LT:
                return lt(ctx);
            case WhisperLanguageLexer.LTEquals:
                return ltEq(ctx);
            case WhisperLanguageLexer.GT:
                return gt(ctx);
            case WhisperLanguageLexer.GTEquals:
                return gtEq(ctx);
            default:
                throw new RuntimeException("unknown operator type: " + ctx.op.getType());
        }
    }

    // expression op=( '==' | '!=' ) expression             #eqExpression
    @Override
    public TLValue visitEqExpression(WhisperLanguageParser.EqExpressionContext ctx) {
        switch (ctx.op.getType()) {
            case WhisperLanguageLexer.Equals:
                return eq(ctx);
            case WhisperLanguageLexer.NEquals:
                return nEq(ctx);
            default:
                throw new RuntimeException("unknown operator type: " + ctx.op.getType());
        }
    }

    public TLValue multiply(WhisperLanguageParser.MultExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        if (lhs == null || rhs == null) {
            System.err.println("lhs " + lhs + " rhs " + rhs);
            throw new EvalException(ctx);
        }

        // number * number
        if (lhs.isNumber() && rhs.isNumber()) {
            return new TLValue(lhs.asDouble() * rhs.asDouble());
        }

        // string * number
        if (lhs.isString() && rhs.isNumber()) {
            StringBuilder str = new StringBuilder();
            int stop = rhs.asDouble().intValue();
            for (int i = 0; i < stop; i++) {
                str.append(lhs.asString());
            }
            return new TLValue(str.toString());
        }

        // list * number
        if (lhs.isList() && rhs.isNumber()) {
            List<TLValue> total = new ArrayList<>();
            int stop = rhs.asDouble().intValue();
            for (int i = 0; i < stop; i++) {
                total.addAll(lhs.asList());
            }
            return new TLValue(total);
        }

        throw new EvalException(ctx);
    }

    private TLValue divide(WhisperLanguageParser.MultExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        if (lhs.isNumber() && rhs.isNumber()) {
            return new TLValue(lhs.asDouble() / rhs.asDouble());
        }
        throw new EvalException(ctx);
    }

    private TLValue modulus(WhisperLanguageParser.MultExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        if (lhs.isNumber() && rhs.isNumber()) {
            return new TLValue(lhs.asDouble() % rhs.asDouble());
        }
        throw new EvalException(ctx);
    }

    private TLValue add(WhisperLanguageParser.AddExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));

        if (lhs == null || rhs == null) {
            throw new EvalException(ctx);
        }

        // number + number
        if (lhs.isNumber() && rhs.isNumber()) {
            return new TLValue(lhs.asDouble() + rhs.asDouble());
        }

        // list + any
        if (lhs.isList()) {
            List<TLValue> list = lhs.asList();
            list.add(rhs);
            return new TLValue(list);
        }

        // string + any
        if (lhs.isString()) {
            return new TLValue(lhs.asString() + "" + rhs.toString());
        }

        // any + string
        if (rhs.isString()) {
            return new TLValue(lhs.toString() + "" + rhs.asString());
        }

        return new TLValue(lhs.toString() + rhs.toString());
    }

    private TLValue subtract(WhisperLanguageParser.AddExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        if (lhs.isNumber() && rhs.isNumber()) {
            return new TLValue(lhs.asDouble() - rhs.asDouble());
        }
        if (lhs.isList()) {
            List<TLValue> list = lhs.asList();
            list.remove(rhs);
            return new TLValue(list);
        }
        throw new EvalException(ctx);
    }

    private TLValue gtEq(WhisperLanguageParser.CompExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        if (lhs.isNumber() && rhs.isNumber()) {
            return new TLValue(lhs.asDouble() >= rhs.asDouble());
        }
        if (lhs.isString() && rhs.isString()) {
            return new TLValue(lhs.asString().compareTo(rhs.asString()) >= 0);
        }
        throw new EvalException(ctx);
    }

    private TLValue ltEq(WhisperLanguageParser.CompExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        if (lhs.isNumber() && rhs.isNumber()) {
            return new TLValue(lhs.asDouble() <= rhs.asDouble());
        }
        if (lhs.isString() && rhs.isString()) {
            return new TLValue(lhs.asString().compareTo(rhs.asString()) <= 0);
        }
        throw new EvalException(ctx);
    }

    private TLValue gt(WhisperLanguageParser.CompExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        if (lhs.isNumber() && rhs.isNumber()) {
            return new TLValue(lhs.asDouble() > rhs.asDouble());
        }
        if (lhs.isString() && rhs.isString()) {
            return new TLValue(lhs.asString().compareTo(rhs.asString()) > 0);
        }
        throw new EvalException(ctx);
    }

    private TLValue lt(WhisperLanguageParser.CompExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        if (lhs.isNumber() && rhs.isNumber()) {
            return new TLValue(lhs.asDouble() < rhs.asDouble());
        }
        if (lhs.isString() && rhs.isString()) {
            return new TLValue(lhs.asString().compareTo(rhs.asString()) < 0);
        }
        throw new EvalException(ctx);
    }

    private TLValue eq(WhisperLanguageParser.EqExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        if (lhs == null) {
            throw new EvalException(ctx);
        }
        return new TLValue(lhs.equals(rhs));
    }

    private TLValue nEq(WhisperLanguageParser.EqExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));
        return new TLValue(!lhs.equals(rhs));
    }

    // expression '&&' expression               #andExpression
    @Override
    public TLValue visitAndExpression(WhisperLanguageParser.AndExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));

        if (!lhs.isBoolean() || !rhs.isBoolean()) {
            throw new EvalException(ctx);
        }
        return new TLValue(lhs.asBoolean() && rhs.asBoolean());
    }

    // expression '||' expression               #orExpression
    @Override
    public TLValue visitOrExpression(WhisperLanguageParser.OrExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));

        if (!lhs.isBoolean() || !rhs.isBoolean()) {
            throw new EvalException(ctx);
        }
        return new TLValue(lhs.asBoolean() || rhs.asBoolean());
    }

    // expression '?' expression ':' expression #ternaryExpression
    @Override
    public TLValue visitTernaryExpression(WhisperLanguageParser.TernaryExpressionContext ctx) {
        TLValue condition = this.visit(ctx.expression(0));
        if (condition.asBoolean()) {
            return this.visit(ctx.expression(1));
        } else {
            return this.visit(ctx.expression(2));
        }
    }

    // expression In expression                 #inExpression
    @Override
    public TLValue visitInExpression(WhisperLanguageParser.InExpressionContext ctx) {
        TLValue lhs = this.visit(ctx.expression(0));
        TLValue rhs = this.visit(ctx.expression(1));

        if (rhs.isList()) {
            for (TLValue val : rhs.asList()) {
                if (val.equals(lhs)) {
                    return new TLValue(true);
                }
            }
            return new TLValue(false);
        }
        throw new EvalException(ctx);
    }

    // Number                                   #numberExpression
    @Override
    public TLValue visitNumberExpression(WhisperLanguageParser.NumberExpressionContext ctx) {
        return new TLValue(Double.valueOf(ctx.getText()));
    }

    // Bool                                     #boolExpression
    @Override
    public TLValue visitBoolExpression(WhisperLanguageParser.BoolExpressionContext ctx) {
        return new TLValue(Boolean.valueOf(ctx.getText()));
    }

    // Null                                     #nullExpression
    @Override
    public TLValue visitNullExpression(WhisperLanguageParser.NullExpressionContext ctx) {
        return TLValue.NULL;
    }

    private TLValue resolveIndexes(TLValue val, List<WhisperLanguageParser.ExpressionContext> indexes) {
        for (WhisperLanguageParser.ExpressionContext ec : indexes) {
            TLValue idx = this.visit(ec);
            if (!idx.isNumber() || (!val.isList() && !val.isString())) {
                throw new EvalException("Problem resolving indexes on " + val + " at " + idx, ec);
            }
            int i = idx.asDouble().intValue();
            if (val.isString()) {
                val = new TLValue(val.asString().substring(i, i + 1));
            } else {
                val = val.asList().get(i);
            }
        }
        return val;
    }

    private void setAtIndex(ParserRuleContext ctx, List<WhisperLanguageParser.ExpressionContext> indexes, TLValue val, TLValue newVal) {
        if (!val.isList()) {
            throw new EvalException(ctx);
        }
        for (int i = 0; i < indexes.size() - 1; i++) {
            TLValue idx = this.visit(indexes.get(i));
            if (!idx.isNumber()) {
                throw new EvalException(ctx);
            }
            val = val.asList().get(idx.asDouble().intValue());
        }
        TLValue idx = this.visit(indexes.get(indexes.size() - 1));
        if (!idx.isNumber()) {
            throw new EvalException(ctx);
        }
        val.asList().set(idx.asDouble().intValue(), newVal);
    }

    // functionCall indexes?                    #functionCallExpression
    @Override
    public TLValue visitFunctionCallExpression(WhisperLanguageParser.FunctionCallExpressionContext ctx) {


        TLValue val = this.visit(ctx.functionCall());
        if (ctx.indexes() != null) {
            List<WhisperLanguageParser.ExpressionContext> exps = ctx.indexes().expression();
            val = resolveIndexes(val, exps);
        }
        return val;
    }

    // list indexes?                            #listExpression
    @Override
    public TLValue visitListExpression(WhisperLanguageParser.ListExpressionContext ctx) {
        TLValue val = this.visit(ctx.list_Alias());
        if (ctx.indexes() != null) {
            List<WhisperLanguageParser.ExpressionContext> exps = ctx.indexes().expression();
            val = resolveIndexes(val, exps);
        }
        return val;
    }

    // Identifier indexes?                      #identifierExpression
    @Override
    public TLValue visitIdentifierExpression(WhisperLanguageParser.IdentifierExpressionContext ctx) {
        String id = ctx.Identifier().getText();
        TLValue val = scope.resolve(id);

        if (ctx.indexes() != null) {
            List<WhisperLanguageParser.ExpressionContext> exps = ctx.indexes().expression();
            val = resolveIndexes(val, exps);
        }
        return val;
    }

    // String indexes?                          #stringExpression
    @Override
    public TLValue visitStringExpression(WhisperLanguageParser.StringExpressionContext ctx) {
        String text = ctx.getText();
        text = text.substring(1, text.length() - 1).replaceAll("\\\\(.)", "$1");
        TLValue val = new TLValue(text);
        if (ctx.indexes() != null) {
            List<WhisperLanguageParser.ExpressionContext> exps = ctx.indexes().expression();
            val = resolveIndexes(val, exps);
        }
        return val;
    }

    // '(' expression ')' indexes?              #expressionExpression
    @Override
    public TLValue visitExpressionExpression(WhisperLanguageParser.ExpressionExpressionContext ctx) {
        TLValue val = this.visit(ctx.expression());
        if (ctx.indexes() != null) {
            List<WhisperLanguageParser.ExpressionContext> exps = ctx.indexes().expression();
            val = resolveIndexes(val, exps);
        }
        return val;
    }

    // Input '(' String? ')'                    #inputExpression
    @Override
    public TLValue visitInputExpression(WhisperLanguageParser.InputExpressionContext ctx) {
        TerminalNode inputString = ctx.String_Alias();
        try {
            if (inputString != null) {
                String text = inputString.getText();
                text = text.substring(1, text.length() - 1).replaceAll("\\\\(.)", "$1");
                return new TLValue(new String(Files.readAllBytes(Paths.get(text))));
            } else {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
                return new TLValue(buffer.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // assignment
    // : Identifier indexes? '=' expression
    // ;
    @Override
    public TLValue visitAssignment(WhisperLanguageParser.AssignmentContext ctx) {
        TLValue newVal = this.visit(ctx.expression());
        if (ctx.indexes() != null) {
            TLValue val = scope.resolve(ctx.Identifier().getText());
            List<WhisperLanguageParser.ExpressionContext> exps = ctx.indexes().expression();
            setAtIndex(ctx, exps, val, newVal);
        } else {
            String id = ctx.Identifier().getText();
            scope.assign(id, newVal);
        }
        return TLValue.VOID;
    }

    @Override
    public TLValue visitBuildInIdentifierFunctionCall(WhisperLanguageParser.BuildInIdentifierFunctionCallContext ctx) {
        List<WhisperLanguageParser.ExpressionContext> params = ctx.exprList() != null ? ctx.exprList().expression() : new ArrayList<WhisperLanguageParser.ExpressionContext>();
        String id = ctx.BuildIdentifier().getText();
        BuildInFunction function;
        if ((function = buildFunction.get(id)) != null) {
            List<TLValue> args = new ArrayList<>(params.size());
            for (WhisperLanguageParser.ExpressionContext param : params) {
                args.add(this.visit(param));
            }
            return function.invoke(args);
        }
        throw new EvalException(ctx);
    }

    // Identifier '(' exprList? ')' #identifierFunctionCall
    @Override
    public TLValue visitIdentifierFunctionCall(WhisperLanguageParser.IdentifierFunctionCallContext ctx) {

        List<WhisperLanguageParser.ExpressionContext> params = ctx.exprList() != null ? ctx.exprList().expression() : new ArrayList<WhisperLanguageParser.ExpressionContext>();
        String id = ctx.Identifier().getText() + params.size();
        Function function;

        if ((function = functions.get(id)) != null) {
            List<TLValue> args = new ArrayList<>(params.size());
            for (WhisperLanguageParser.ExpressionContext param : params) {
                args.add(this.visit(param));
            }
            return function.invoke(args, functions, buildFunction);
        } else {
            throw new EvalException(ctx);
        }

    }

    // Println '(' expression? ')'  #printlnFunctionCall
    @Override
    public TLValue visitPrintlnFunctionCall(WhisperLanguageParser.PrintlnFunctionCallContext ctx) {
        if (ctx.expression() != null) {
            System.out.println(this.visit(ctx.expression()));
        } else {
            System.out.println();
        }
        return TLValue.VOID;
    }

    // Print '(' expression ')'     #printFunctionCall
    @Override
    public TLValue visitPrintFunctionCall(WhisperLanguageParser.PrintFunctionCallContext ctx) {
        System.out.print(this.visit(ctx.expression()));
        return TLValue.VOID;
    }

    // Assert '(' expression ')'    #assertFunctionCall
    @Override
    public TLValue visitAssertFunctionCall(WhisperLanguageParser.AssertFunctionCallContext ctx) {
        TLValue value = this.visit(ctx.expression());

        if (!value.isBoolean()) {
            throw new EvalException(ctx);
        }

        if (!value.asBoolean()) {
            File f=new File(ctx.start.getInputStream().getSourceName());
            throw new AssertionError("Assert failed:" + ctx.expression().getText() + " ("+f.getName()+":" + ctx.start.getLine()+")");
        }

        return TLValue.VOID;
    }

    // Size '(' expression ')'      #sizeFunctionCall
    @Override
    public TLValue visitSizeFunctionCall(WhisperLanguageParser.SizeFunctionCallContext ctx) {
        TLValue value = this.visit(ctx.expression());

        if (value.isString()) {
            return new TLValue(value.asString().length());
        }

        if (value.isList()) {
            return new TLValue(value.asList().size());
        }

        throw new EvalException(ctx);
    }

    // ifStatement
    //  : ifStat elseIfStat* elseStat? End
    //  ;
    //
    // ifStat
    //  : If expression Do block
    //  ;
    //
    // elseIfStat
    //  : Else If expression Do block
    //  ;
    //
    // elseStat
    //  : Else Do block
    //  ;
    @Override
    public TLValue visitIfStatement(WhisperLanguageParser.IfStatementContext ctx) {

        // if ...
        if (this.visit(ctx.ifStat().expression()).asBoolean()) {
            return this.visit(ctx.ifStat().block());
        }

        // else if ...
        for (int i = 0; i < ctx.elseIfStat().size(); i++) {
            if (this.visit(ctx.elseIfStat(i).expression()).asBoolean()) {
                return this.visit(ctx.elseIfStat(i).block());
            }
        }

        // else ...
        if (ctx.elseStat() != null) {
            return this.visit(ctx.elseStat().block());
        }

        return TLValue.VOID;
    }

    // block
    // : (statement | functionDecl)* (Return expression)?
    // ;
    @Override
    public TLValue visitBlock(WhisperLanguageParser.BlockContext ctx) {

        scope = new Scope(scope, false); // create new local scope
        for (WhisperLanguageParser.FunctionDeclContext fdx : ctx.functionDecl()) {
            this.visit(fdx);
        }
        for (WhisperLanguageParser.StatementContext sx : ctx.statement()) {
            this.visit(sx);
        }
        WhisperLanguageParser.ExpressionContext ex;
        if ((ex = ctx.expression()) != null) {
            returnValue.value = this.visit(ex);
            scope = scope.parent();
            throw returnValue;
        }
        scope = scope.parent();
        return TLValue.VOID;
    }

    // forStatement
    // : For Identifier '=' expression To expression OBrace block CBrace
    // ;
    @Override
    public TLValue visitForStatement(WhisperLanguageParser.ForStatementContext ctx) {
        int start = this.visit(ctx.expression(0)).asDouble().intValue();
        int stop = this.visit(ctx.expression(1)).asDouble().intValue();
        for (int i = start; i <= stop; i++) {
            scope.assign(ctx.Identifier().getText(), new TLValue(i));
            TLValue returnValue = this.visit(ctx.block());
            if (returnValue != TLValue.VOID) {
                return returnValue;
            }
        }
        return TLValue.VOID;
    }

    // whileStatement
    // : While expression OBrace block CBrace
    // ;
    @Override
    public TLValue visitWhileStatement(WhisperLanguageParser.WhileStatementContext ctx) {
        while (this.visit(ctx.expression()).asBoolean()) {
            TLValue returnValue = this.visit(ctx.block());
            if (returnValue != TLValue.VOID) {
                return returnValue;
            }
        }
        return TLValue.VOID;
    }

}
