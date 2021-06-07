// Generated from ../whisper-grammar/WhisperLanguage.g4 by ANTLR 4.9.2
package net.crtrpt.whisper.gen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WhisperLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, Println=2, Print=3, Input=4, Assert=5, Size=6, Def=7, If=8, Else=9, 
		Return=10, For=11, While=12, To=13, Do=14, End=15, In=16, Null=17, LABEL_Alias=18, 
		GOTO_Alias=19, JUMP_Alias=20, IMPORT_Alias=21, INCLUDE_Alias=22, Modulus_Alias=23, 
		Class_Alias=24, INTERFACE_Alias=25, EXTEND_Alias=26, BitwiseAnd=27, BitwiseOr=28, 
		BitwiseNot=29, BitwiseNegationOperator=30, LeftShiftOperator=31, RightShiftOperator=32, 
		Or=33, And=34, Equals=35, NEquals=36, GTEquals=37, LTEquals=38, Excl=39, 
		GT=40, LT=41, Add=42, Subtract=43, Multiply=44, Divide=45, Modulus=46, 
		OBrace=47, CBrace=48, OBracket=49, CBracket=50, OParen=51, CParen=52, 
		SColon=53, Assign=54, Comma=55, QMark=56, Colon=57, Bool=58, Number=59, 
		BuildIdentifier=60, Identifier=61, String_Alias=62, Comment=63, Space=64;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "Println", "Print", "Input", "Assert", "Size", "Def", "If", "Else", 
			"Return", "For", "While", "To", "Do", "End", "In", "Null", "LABEL_Alias", 
			"GOTO_Alias", "JUMP_Alias", "IMPORT_Alias", "INCLUDE_Alias", "Modulus_Alias", 
			"Class_Alias", "INTERFACE_Alias", "EXTEND_Alias", "BitwiseAnd", "BitwiseOr", 
			"BitwiseNot", "BitwiseNegationOperator", "LeftShiftOperator", "RightShiftOperator", 
			"Or", "And", "Equals", "NEquals", "GTEquals", "LTEquals", "Excl", "GT", 
			"LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", 
			"OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", 
			"QMark", "Colon", "Bool", "Number", "BuildIdentifier", "Identifier", 
			"String_Alias", "Comment", "Space", "Int", "Digit"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'**'", "'println'", "'print'", "'input'", "'assert'", "'size'", 
			"'func'", "'if'", "'else'", "'return'", "'for'", "'while'", "'to'", "'do'", 
			"'end'", "'in'", "'null'", "'label'", "'goto'", "'jump'", "'import'", 
			"'include'", "'modulue'", "'class'", "'interface'", "'extend'", "'&'", 
			"'|'", "'^'", "'~'", "'>>'", "'<<'", "'||'", "'&&'", "'=='", "'!='", 
			"'>='", "'<='", "'!'", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'{'", "'}'", "'['", "']'", "'('", "')'", "';'", "'='", "','", "'?'", 
			"':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "Println", "Print", "Input", "Assert", "Size", "Def", "If", 
			"Else", "Return", "For", "While", "To", "Do", "End", "In", "Null", "LABEL_Alias", 
			"GOTO_Alias", "JUMP_Alias", "IMPORT_Alias", "INCLUDE_Alias", "Modulus_Alias", 
			"Class_Alias", "INTERFACE_Alias", "EXTEND_Alias", "BitwiseAnd", "BitwiseOr", 
			"BitwiseNot", "BitwiseNegationOperator", "LeftShiftOperator", "RightShiftOperator", 
			"Or", "And", "Equals", "NEquals", "GTEquals", "LTEquals", "Excl", "GT", 
			"LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OBrace", "CBrace", 
			"OBracket", "CBracket", "OParen", "CParen", "SColon", "Assign", "Comma", 
			"QMark", "Colon", "Bool", "Number", "BuildIdentifier", "Identifier", 
			"String_Alias", "Comment", "Space"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public WhisperLanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "WhisperLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2B\u01bf\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3"+
		"\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3*"+
		"\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3"+
		"\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3"+
		";\3;\3;\3;\3;\3;\3;\5;\u0168\n;\3<\3<\3<\7<\u016d\n<\f<\16<\u0170\13<"+
		"\5<\u0172\n<\3=\3=\7=\u0176\n=\f=\16=\u0179\13=\3>\3>\7>\u017d\n>\f>\16"+
		">\u0180\13>\3?\3?\3?\3?\7?\u0186\n?\f?\16?\u0189\13?\3?\3?\3?\3?\3?\7"+
		"?\u0190\n?\f?\16?\u0193\13?\3?\5?\u0196\n?\3@\3@\3@\3@\7@\u019c\n@\f@"+
		"\16@\u019f\13@\3@\3@\3@\3@\7@\u01a5\n@\f@\16@\u01a8\13@\3@\3@\5@\u01ac"+
		"\n@\3@\3@\3A\3A\3A\3A\3B\3B\7B\u01b6\nB\fB\16B\u01b9\13B\3B\5B\u01bc\n"+
		"B\3C\3C\3\u01a6\2D\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63"+
		"e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083\2\u0085\2\3\2\r\3"+
		"\2BB\6\2\62;C\\aac|\5\2C\\aac|\3\2$$\6\2\f\f\17\17$$^^\4\2\f\f\17\17\3"+
		"\2))\6\2\f\f\17\17))^^\5\2\13\f\16\17\"\"\3\2\63;\3\2\62;\2\u01cb\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2"+
		"I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3"+
		"\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2"+
		"\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2"+
		"o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3"+
		"\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\3\u0087\3\2\2\2\5\u008a"+
		"\3\2\2\2\7\u0092\3\2\2\2\t\u0098\3\2\2\2\13\u009e\3\2\2\2\r\u00a5\3\2"+
		"\2\2\17\u00aa\3\2\2\2\21\u00af\3\2\2\2\23\u00b2\3\2\2\2\25\u00b7\3\2\2"+
		"\2\27\u00be\3\2\2\2\31\u00c2\3\2\2\2\33\u00c8\3\2\2\2\35\u00cb\3\2\2\2"+
		"\37\u00ce\3\2\2\2!\u00d2\3\2\2\2#\u00d5\3\2\2\2%\u00da\3\2\2\2\'\u00e0"+
		"\3\2\2\2)\u00e5\3\2\2\2+\u00ea\3\2\2\2-\u00f1\3\2\2\2/\u00f9\3\2\2\2\61"+
		"\u0101\3\2\2\2\63\u0107\3\2\2\2\65\u0111\3\2\2\2\67\u0118\3\2\2\29\u011a"+
		"\3\2\2\2;\u011c\3\2\2\2=\u011e\3\2\2\2?\u0120\3\2\2\2A\u0123\3\2\2\2C"+
		"\u0126\3\2\2\2E\u0129\3\2\2\2G\u012c\3\2\2\2I\u012f\3\2\2\2K\u0132\3\2"+
		"\2\2M\u0135\3\2\2\2O\u0138\3\2\2\2Q\u013a\3\2\2\2S\u013c\3\2\2\2U\u013e"+
		"\3\2\2\2W\u0140\3\2\2\2Y\u0142\3\2\2\2[\u0144\3\2\2\2]\u0146\3\2\2\2_"+
		"\u0148\3\2\2\2a\u014a\3\2\2\2c\u014c\3\2\2\2e\u014e\3\2\2\2g\u0150\3\2"+
		"\2\2i\u0152\3\2\2\2k\u0154\3\2\2\2m\u0156\3\2\2\2o\u0158\3\2\2\2q\u015a"+
		"\3\2\2\2s\u015c\3\2\2\2u\u0167\3\2\2\2w\u0169\3\2\2\2y\u0173\3\2\2\2{"+
		"\u017a\3\2\2\2}\u0195\3\2\2\2\177\u01ab\3\2\2\2\u0081\u01af\3\2\2\2\u0083"+
		"\u01bb\3\2\2\2\u0085\u01bd\3\2\2\2\u0087\u0088\7,\2\2\u0088\u0089\7,\2"+
		"\2\u0089\4\3\2\2\2\u008a\u008b\7r\2\2\u008b\u008c\7t\2\2\u008c\u008d\7"+
		"k\2\2\u008d\u008e\7p\2\2\u008e\u008f\7v\2\2\u008f\u0090\7n\2\2\u0090\u0091"+
		"\7p\2\2\u0091\6\3\2\2\2\u0092\u0093\7r\2\2\u0093\u0094\7t\2\2\u0094\u0095"+
		"\7k\2\2\u0095\u0096\7p\2\2\u0096\u0097\7v\2\2\u0097\b\3\2\2\2\u0098\u0099"+
		"\7k\2\2\u0099\u009a\7p\2\2\u009a\u009b\7r\2\2\u009b\u009c\7w\2\2\u009c"+
		"\u009d\7v\2\2\u009d\n\3\2\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7u\2\2\u00a0"+
		"\u00a1\7u\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7t\2\2\u00a3\u00a4\7v\2\2"+
		"\u00a4\f\3\2\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7|"+
		"\2\2\u00a8\u00a9\7g\2\2\u00a9\16\3\2\2\2\u00aa\u00ab\7h\2\2\u00ab\u00ac"+
		"\7w\2\2\u00ac\u00ad\7p\2\2\u00ad\u00ae\7e\2\2\u00ae\20\3\2\2\2\u00af\u00b0"+
		"\7k\2\2\u00b0\u00b1\7h\2\2\u00b1\22\3\2\2\2\u00b2\u00b3\7g\2\2\u00b3\u00b4"+
		"\7n\2\2\u00b4\u00b5\7u\2\2\u00b5\u00b6\7g\2\2\u00b6\24\3\2\2\2\u00b7\u00b8"+
		"\7t\2\2\u00b8\u00b9\7g\2\2\u00b9\u00ba\7v\2\2\u00ba\u00bb\7w\2\2\u00bb"+
		"\u00bc\7t\2\2\u00bc\u00bd\7p\2\2\u00bd\26\3\2\2\2\u00be\u00bf\7h\2\2\u00bf"+
		"\u00c0\7q\2\2\u00c0\u00c1\7t\2\2\u00c1\30\3\2\2\2\u00c2\u00c3\7y\2\2\u00c3"+
		"\u00c4\7j\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7n\2\2\u00c6\u00c7\7g\2\2"+
		"\u00c7\32\3\2\2\2\u00c8\u00c9\7v\2\2\u00c9\u00ca\7q\2\2\u00ca\34\3\2\2"+
		"\2\u00cb\u00cc\7f\2\2\u00cc\u00cd\7q\2\2\u00cd\36\3\2\2\2\u00ce\u00cf"+
		"\7g\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7f\2\2\u00d1 \3\2\2\2\u00d2\u00d3"+
		"\7k\2\2\u00d3\u00d4\7p\2\2\u00d4\"\3\2\2\2\u00d5\u00d6\7p\2\2\u00d6\u00d7"+
		"\7w\2\2\u00d7\u00d8\7n\2\2\u00d8\u00d9\7n\2\2\u00d9$\3\2\2\2\u00da\u00db"+
		"\7n\2\2\u00db\u00dc\7c\2\2\u00dc\u00dd\7d\2\2\u00dd\u00de\7g\2\2\u00de"+
		"\u00df\7n\2\2\u00df&\3\2\2\2\u00e0\u00e1\7i\2\2\u00e1\u00e2\7q\2\2\u00e2"+
		"\u00e3\7v\2\2\u00e3\u00e4\7q\2\2\u00e4(\3\2\2\2\u00e5\u00e6\7l\2\2\u00e6"+
		"\u00e7\7w\2\2\u00e7\u00e8\7o\2\2\u00e8\u00e9\7r\2\2\u00e9*\3\2\2\2\u00ea"+
		"\u00eb\7k\2\2\u00eb\u00ec\7o\2\2\u00ec\u00ed\7r\2\2\u00ed\u00ee\7q\2\2"+
		"\u00ee\u00ef\7t\2\2\u00ef\u00f0\7v\2\2\u00f0,\3\2\2\2\u00f1\u00f2\7k\2"+
		"\2\u00f2\u00f3\7p\2\2\u00f3\u00f4\7e\2\2\u00f4\u00f5\7n\2\2\u00f5\u00f6"+
		"\7w\2\2\u00f6\u00f7\7f\2\2\u00f7\u00f8\7g\2\2\u00f8.\3\2\2\2\u00f9\u00fa"+
		"\7o\2\2\u00fa\u00fb\7q\2\2\u00fb\u00fc\7f\2\2\u00fc\u00fd\7w\2\2\u00fd"+
		"\u00fe\7n\2\2\u00fe\u00ff\7w\2\2\u00ff\u0100\7g\2\2\u0100\60\3\2\2\2\u0101"+
		"\u0102\7e\2\2\u0102\u0103\7n\2\2\u0103\u0104\7c\2\2\u0104\u0105\7u\2\2"+
		"\u0105\u0106\7u\2\2\u0106\62\3\2\2\2\u0107\u0108\7k\2\2\u0108\u0109\7"+
		"p\2\2\u0109\u010a\7v\2\2\u010a\u010b\7g\2\2\u010b\u010c\7t\2\2\u010c\u010d"+
		"\7h\2\2\u010d\u010e\7c\2\2\u010e\u010f\7e\2\2\u010f\u0110\7g\2\2\u0110"+
		"\64\3\2\2\2\u0111\u0112\7g\2\2\u0112\u0113\7z\2\2\u0113\u0114\7v\2\2\u0114"+
		"\u0115\7g\2\2\u0115\u0116\7p\2\2\u0116\u0117\7f\2\2\u0117\66\3\2\2\2\u0118"+
		"\u0119\7(\2\2\u01198\3\2\2\2\u011a\u011b\7~\2\2\u011b:\3\2\2\2\u011c\u011d"+
		"\7`\2\2\u011d<\3\2\2\2\u011e\u011f\7\u0080\2\2\u011f>\3\2\2\2\u0120\u0121"+
		"\7@\2\2\u0121\u0122\7@\2\2\u0122@\3\2\2\2\u0123\u0124\7>\2\2\u0124\u0125"+
		"\7>\2\2\u0125B\3\2\2\2\u0126\u0127\7~\2\2\u0127\u0128\7~\2\2\u0128D\3"+
		"\2\2\2\u0129\u012a\7(\2\2\u012a\u012b\7(\2\2\u012bF\3\2\2\2\u012c\u012d"+
		"\7?\2\2\u012d\u012e\7?\2\2\u012eH\3\2\2\2\u012f\u0130\7#\2\2\u0130\u0131"+
		"\7?\2\2\u0131J\3\2\2\2\u0132\u0133\7@\2\2\u0133\u0134\7?\2\2\u0134L\3"+
		"\2\2\2\u0135\u0136\7>\2\2\u0136\u0137\7?\2\2\u0137N\3\2\2\2\u0138\u0139"+
		"\7#\2\2\u0139P\3\2\2\2\u013a\u013b\7@\2\2\u013bR\3\2\2\2\u013c\u013d\7"+
		">\2\2\u013dT\3\2\2\2\u013e\u013f\7-\2\2\u013fV\3\2\2\2\u0140\u0141\7/"+
		"\2\2\u0141X\3\2\2\2\u0142\u0143\7,\2\2\u0143Z\3\2\2\2\u0144\u0145\7\61"+
		"\2\2\u0145\\\3\2\2\2\u0146\u0147\7\'\2\2\u0147^\3\2\2\2\u0148\u0149\7"+
		"}\2\2\u0149`\3\2\2\2\u014a\u014b\7\177\2\2\u014bb\3\2\2\2\u014c\u014d"+
		"\7]\2\2\u014dd\3\2\2\2\u014e\u014f\7_\2\2\u014ff\3\2\2\2\u0150\u0151\7"+
		"*\2\2\u0151h\3\2\2\2\u0152\u0153\7+\2\2\u0153j\3\2\2\2\u0154\u0155\7="+
		"\2\2\u0155l\3\2\2\2\u0156\u0157\7?\2\2\u0157n\3\2\2\2\u0158\u0159\7.\2"+
		"\2\u0159p\3\2\2\2\u015a\u015b\7A\2\2\u015br\3\2\2\2\u015c\u015d\7<\2\2"+
		"\u015dt\3\2\2\2\u015e\u015f\7v\2\2\u015f\u0160\7t\2\2\u0160\u0161\7w\2"+
		"\2\u0161\u0168\7g\2\2\u0162\u0163\7h\2\2\u0163\u0164\7c\2\2\u0164\u0165"+
		"\7n\2\2\u0165\u0166\7u\2\2\u0166\u0168\7g\2\2\u0167\u015e\3\2\2\2\u0167"+
		"\u0162\3\2\2\2\u0168v\3\2\2\2\u0169\u0171\5\u0083B\2\u016a\u016e\7\60"+
		"\2\2\u016b\u016d\5\u0085C\2\u016c\u016b\3\2\2\2\u016d\u0170\3\2\2\2\u016e"+
		"\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0172\3\2\2\2\u0170\u016e\3\2"+
		"\2\2\u0171\u016a\3\2\2\2\u0171\u0172\3\2\2\2\u0172x\3\2\2\2\u0173\u0177"+
		"\t\2\2\2\u0174\u0176\t\3\2\2\u0175\u0174\3\2\2\2\u0176\u0179\3\2\2\2\u0177"+
		"\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178z\3\2\2\2\u0179\u0177\3\2\2\2"+
		"\u017a\u017e\t\4\2\2\u017b\u017d\t\3\2\2\u017c\u017b\3\2\2\2\u017d\u0180"+
		"\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f|\3\2\2\2\u0180"+
		"\u017e\3\2\2\2\u0181\u0187\t\5\2\2\u0182\u0186\n\6\2\2\u0183\u0184\7^"+
		"\2\2\u0184\u0186\n\7\2\2\u0185\u0182\3\2\2\2\u0185\u0183\3\2\2\2\u0186"+
		"\u0189\3\2\2\2\u0187\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u018a\3\2"+
		"\2\2\u0189\u0187\3\2\2\2\u018a\u0196\t\5\2\2\u018b\u0191\t\b\2\2\u018c"+
		"\u0190\n\t\2\2\u018d\u018e\7^\2\2\u018e\u0190\n\7\2\2\u018f\u018c\3\2"+
		"\2\2\u018f\u018d\3\2\2\2\u0190\u0193\3\2\2\2\u0191\u018f\3\2\2\2\u0191"+
		"\u0192\3\2\2\2\u0192\u0194\3\2\2\2\u0193\u0191\3\2\2\2\u0194\u0196\t\b"+
		"\2\2\u0195\u0181\3\2\2\2\u0195\u018b\3\2\2\2\u0196~\3\2\2\2\u0197\u0198"+
		"\7\61\2\2\u0198\u0199\7\61\2\2\u0199\u019d\3\2\2\2\u019a\u019c\n\7\2\2"+
		"\u019b\u019a\3\2\2\2\u019c\u019f\3\2\2\2\u019d\u019b\3\2\2\2\u019d\u019e"+
		"\3\2\2\2\u019e\u01ac\3\2\2\2\u019f\u019d\3\2\2\2\u01a0\u01a1\7\61\2\2"+
		"\u01a1\u01a2\7,\2\2\u01a2\u01a6\3\2\2\2\u01a3\u01a5\13\2\2\2\u01a4\u01a3"+
		"\3\2\2\2\u01a5\u01a8\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a7"+
		"\u01a9\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a9\u01aa\7,\2\2\u01aa\u01ac\7\61"+
		"\2\2\u01ab\u0197\3\2\2\2\u01ab\u01a0\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad"+
		"\u01ae\b@\2\2\u01ae\u0080\3\2\2\2\u01af\u01b0\t\n\2\2\u01b0\u01b1\3\2"+
		"\2\2\u01b1\u01b2\bA\2\2\u01b2\u0082\3\2\2\2\u01b3\u01b7\t\13\2\2\u01b4"+
		"\u01b6\5\u0085C\2\u01b5\u01b4\3\2\2\2\u01b6\u01b9\3\2\2\2\u01b7\u01b5"+
		"\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01bc\3\2\2\2\u01b9\u01b7\3\2\2\2\u01ba"+
		"\u01bc\7\62\2\2\u01bb\u01b3\3\2\2\2\u01bb\u01ba\3\2\2\2\u01bc\u0084\3"+
		"\2\2\2\u01bd\u01be\t\f\2\2\u01be\u0086\3\2\2\2\22\2\u0167\u016e\u0171"+
		"\u0177\u017e\u0185\u0187\u018f\u0191\u0195\u019d\u01a6\u01ab\u01b7\u01bb"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}