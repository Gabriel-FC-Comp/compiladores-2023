// Generated from .\gyhLang.g4 by ANTLR 4.7.2

    // Pacote onde serão salvos os analisadores
    package analisadores;
    // Bibliotecas necessárias
    import java.util.ArrayList;
    import comandos.*;
    import main.GyhProgram;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class gyhLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, COMENTARIO=2, PCDEC=3, PCPROG=4, PCINT=5, PCLER=6, PCREAL=7, PCIMPRIMIR=8, 
		PCSE=9, PCENTAO=10, PCENQTO=11, PCINI=12, PCFIM=13, PCSENAO=14, CADEIA=15, 
		VAR=16, OPARITSOMA=17, OPARITSUB=18, OPARITMULT=19, OPARITDIV=20, OPBOOLE=21, 
		OPBOOLOU=22, OPRELIGUAL=23, OPRELDIF=24, OPRELMAIORIGUAL=25, OPRELMENORIGUAL=26, 
		OPRELMAIOR=27, OPRELMENOR=28, ATRIB=29, DELIM=30, ABREPAR=31, FECHAPAR=32, 
		NUMREAL=33, NUMINT=34;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "COMENTARIO", "PCDEC", "PCPROG", "PCINT", "PCLER", "PCREAL", "PCIMPRIMIR", 
			"PCSE", "PCENTAO", "PCENQTO", "PCINI", "PCFIM", "PCSENAO", "CADEIA", 
			"VAR", "OPARITSOMA", "OPARITSUB", "OPARITMULT", "OPARITDIV", "OPBOOLE", 
			"OPBOOLOU", "OPRELIGUAL", "OPRELDIF", "OPRELMAIORIGUAL", "OPRELMENORIGUAL", 
			"OPRELMAIOR", "OPRELMENOR", "ATRIB", "DELIM", "ABREPAR", "FECHAPAR", 
			"NUMREAL", "NUMINT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'DEC'", "'PROG'", "'INT'", "'LER'", "'REAL'", "'IMPRIMIR'", 
			"'SE'", "'ENTAO'", "'ENQTO'", "'INI'", "'FIM'", "'SENAO'", null, null, 
			"'+'", "'-'", "'*'", "'/'", "'E'", "'OU'", "'=='", "'!='", "'>='", "'<='", 
			"'>'", "'<'", "':='", "':'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "COMENTARIO", "PCDEC", "PCPROG", "PCINT", "PCLER", "PCREAL", 
			"PCIMPRIMIR", "PCSE", "PCENTAO", "PCENQTO", "PCINI", "PCFIM", "PCSENAO", 
			"CADEIA", "VAR", "OPARITSOMA", "OPARITSUB", "OPARITMULT", "OPARITDIV", 
			"OPBOOLE", "OPBOOLOU", "OPRELIGUAL", "OPRELDIF", "OPRELMAIORIGUAL", "OPRELMENORIGUAL", 
			"OPRELMAIOR", "OPRELMENOR", "ATRIB", "DELIM", "ABREPAR", "FECHAPAR", 
			"NUMREAL", "NUMINT"
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


	    
	    /**
	    *   Tabela de símbolos para manter as variáveis declaradas no programa fonte.
	    */
	    private TabelaSimbolos varTabela = new TabelaSimbolos();
	    /**
	    *   Strings auxiliares para salvar o nome das variáveis, expressões aritméticas
	    *   e booleanas, respectivamente.
	    */
	    private String varAtrib,varExp,varCond;
	    /**
	    *   Array de inteiros usados para auxiliar na identificação dos comandos pertencentes
	    *   a diferentes escopos de subalgoritmos.
	    */
	    private ArrayList<Integer> indices = new ArrayList<>();
	    /**
	    *   Array de comandos usados para concatenar os comandos pertencente a um escopo de
	    *   subalgoritmo.
	    */
	    private ArrayList<Comando> listSub;
	    /**
	    *   Instancias de comando auxiliares, para armazenar os comandos a serem executados
	    *   em caminhos condicionais e de repetição.
	    */
	    private Comando cmdTrue, cmdFalse;
	    /**
	    * Variavel para geração de código.
	    */
	    private GyhProgram program = new GyhProgram();
	    /**
	    * Array de comandos que manterá os comandos na ordem em que aparecem no programa fonte.  
	    */
	    private ArrayList<Comando> listCmd = new ArrayList<>();
	    /**
	    * Variavel para verificação semântica, se o tipo de dado que uma variável está recebendo é correto.
	    */
	    private int eVarInt = -1;
	    //============================================//
	    
	    // Delcação de funções
	    
	    /** 
	    *   Método utilizado para adicionar um novo símbolo à tabela de símbolos.
	    *   @param nome O nome da variável.
	    *   @param tipo O tipo da variável.
	    */
	    public void addTabela(String nome, String tipo){
	        if(varTabela.contemChave(nome)){
	            System.err.println("Erro Semântico - Variável já declarada com esse nome: " + nome);
	        }else{
	            varTabela.add(new Simbolo(nome,tipo));
	        }// if-else
	    }// addTabela



	public gyhLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "gyhLang.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u00d5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\2\3\3\3\3\7\3N\n\3\f\3\16\3Q\13\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\6\20\u0093"+
		"\n\20\r\20\16\20\u0094\3\20\3\20\3\21\3\21\7\21\u009b\n\21\f\21\16\21"+
		"\u009e\13\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3"+
		"\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\6\"\u00c7"+
		"\n\"\r\"\16\"\u00c8\3\"\3\"\6\"\u00cd\n\"\r\"\16\"\u00ce\3#\6#\u00d2\n"+
		"#\r#\16#\u00d3\2\2$\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$\3\2\b\5\2\13\f\17\17\"\"\4\2\f\f\17\17"+
		"\n\2\13\13\"\",-//\61=??C\\c|\3\2c|\5\2\62;C\\c|\3\2\62;\2\u00da\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\3G\3\2\2\2\5K"+
		"\3\2\2\2\7T\3\2\2\2\tX\3\2\2\2\13]\3\2\2\2\ra\3\2\2\2\17e\3\2\2\2\21j"+
		"\3\2\2\2\23s\3\2\2\2\25v\3\2\2\2\27|\3\2\2\2\31\u0082\3\2\2\2\33\u0086"+
		"\3\2\2\2\35\u008a\3\2\2\2\37\u0090\3\2\2\2!\u0098\3\2\2\2#\u009f\3\2\2"+
		"\2%\u00a1\3\2\2\2\'\u00a3\3\2\2\2)\u00a5\3\2\2\2+\u00a7\3\2\2\2-\u00a9"+
		"\3\2\2\2/\u00ac\3\2\2\2\61\u00af\3\2\2\2\63\u00b2\3\2\2\2\65\u00b5\3\2"+
		"\2\2\67\u00b8\3\2\2\29\u00ba\3\2\2\2;\u00bc\3\2\2\2=\u00bf\3\2\2\2?\u00c1"+
		"\3\2\2\2A\u00c3\3\2\2\2C\u00c6\3\2\2\2E\u00d1\3\2\2\2GH\t\2\2\2HI\3\2"+
		"\2\2IJ\b\2\2\2J\4\3\2\2\2KO\7%\2\2LN\n\3\2\2ML\3\2\2\2NQ\3\2\2\2OM\3\2"+
		"\2\2OP\3\2\2\2PR\3\2\2\2QO\3\2\2\2RS\b\3\2\2S\6\3\2\2\2TU\7F\2\2UV\7G"+
		"\2\2VW\7E\2\2W\b\3\2\2\2XY\7R\2\2YZ\7T\2\2Z[\7Q\2\2[\\\7I\2\2\\\n\3\2"+
		"\2\2]^\7K\2\2^_\7P\2\2_`\7V\2\2`\f\3\2\2\2ab\7N\2\2bc\7G\2\2cd\7T\2\2"+
		"d\16\3\2\2\2ef\7T\2\2fg\7G\2\2gh\7C\2\2hi\7N\2\2i\20\3\2\2\2jk\7K\2\2"+
		"kl\7O\2\2lm\7R\2\2mn\7T\2\2no\7K\2\2op\7O\2\2pq\7K\2\2qr\7T\2\2r\22\3"+
		"\2\2\2st\7U\2\2tu\7G\2\2u\24\3\2\2\2vw\7G\2\2wx\7P\2\2xy\7V\2\2yz\7C\2"+
		"\2z{\7Q\2\2{\26\3\2\2\2|}\7G\2\2}~\7P\2\2~\177\7S\2\2\177\u0080\7V\2\2"+
		"\u0080\u0081\7Q\2\2\u0081\30\3\2\2\2\u0082\u0083\7K\2\2\u0083\u0084\7"+
		"P\2\2\u0084\u0085\7K\2\2\u0085\32\3\2\2\2\u0086\u0087\7H\2\2\u0087\u0088"+
		"\7K\2\2\u0088\u0089\7O\2\2\u0089\34\3\2\2\2\u008a\u008b\7U\2\2\u008b\u008c"+
		"\7G\2\2\u008c\u008d\7P\2\2\u008d\u008e\7C\2\2\u008e\u008f\7Q\2\2\u008f"+
		"\36\3\2\2\2\u0090\u0092\7$\2\2\u0091\u0093\t\4\2\2\u0092\u0091\3\2\2\2"+
		"\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096"+
		"\3\2\2\2\u0096\u0097\7$\2\2\u0097 \3\2\2\2\u0098\u009c\t\5\2\2\u0099\u009b"+
		"\t\6\2\2\u009a\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\"\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a0\7-\2\2"+
		"\u00a0$\3\2\2\2\u00a1\u00a2\7/\2\2\u00a2&\3\2\2\2\u00a3\u00a4\7,\2\2\u00a4"+
		"(\3\2\2\2\u00a5\u00a6\7\61\2\2\u00a6*\3\2\2\2\u00a7\u00a8\7G\2\2\u00a8"+
		",\3\2\2\2\u00a9\u00aa\7Q\2\2\u00aa\u00ab\7W\2\2\u00ab.\3\2\2\2\u00ac\u00ad"+
		"\7?\2\2\u00ad\u00ae\7?\2\2\u00ae\60\3\2\2\2\u00af\u00b0\7#\2\2\u00b0\u00b1"+
		"\7?\2\2\u00b1\62\3\2\2\2\u00b2\u00b3\7@\2\2\u00b3\u00b4\7?\2\2\u00b4\64"+
		"\3\2\2\2\u00b5\u00b6\7>\2\2\u00b6\u00b7\7?\2\2\u00b7\66\3\2\2\2\u00b8"+
		"\u00b9\7@\2\2\u00b98\3\2\2\2\u00ba\u00bb\7>\2\2\u00bb:\3\2\2\2\u00bc\u00bd"+
		"\7<\2\2\u00bd\u00be\7?\2\2\u00be<\3\2\2\2\u00bf\u00c0\7<\2\2\u00c0>\3"+
		"\2\2\2\u00c1\u00c2\7*\2\2\u00c2@\3\2\2\2\u00c3\u00c4\7+\2\2\u00c4B\3\2"+
		"\2\2\u00c5\u00c7\t\7\2\2\u00c6\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\7\60"+
		"\2\2\u00cb\u00cd\t\7\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce"+
		"\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cfD\3\2\2\2\u00d0\u00d2\t\7\2\2"+
		"\u00d1\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4"+
		"\3\2\2\2\u00d4F\3\2\2\2\13\2O\u0092\u0094\u009a\u009c\u00c8\u00ce\u00d3"+
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