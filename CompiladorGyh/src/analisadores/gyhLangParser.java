// Generated from .\gyhLang.g4 by ANTLR 4.7.2

    // Pacote onde serão salvos os analisadores
    package analisadores;
    // Bibliotecas necessárias
    import java.util.ArrayList;
    import comandos.*;
    import main.GyhProgram;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class gyhLangParser extends Parser {
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
	public static final int
		RULE_programa = 0, RULE_declaracao = 1, RULE_tipoVar = 2, RULE_expressaoAritmetica = 3, 
		RULE_sentencaAritmetica = 4, RULE_termoAritmetico = 5, RULE_proposicaoAritmetica = 6, 
		RULE_fatorAritmetico = 7, RULE_expressaoRelacional = 8, RULE_termoRelacional = 9, 
		RULE_comando = 10, RULE_comandoAtribuicao = 11, RULE_comandoEntrada = 12, 
		RULE_comandoSaida = 13, RULE_tipoSaida = 14, RULE_comandoCondicao = 15, 
		RULE_comandoRepeticao = 16, RULE_subAlgoritmo = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declaracao", "tipoVar", "expressaoAritmetica", "sentencaAritmetica", 
			"termoAritmetico", "proposicaoAritmetica", "fatorAritmetico", "expressaoRelacional", 
			"termoRelacional", "comando", "comandoAtribuicao", "comandoEntrada", 
			"comandoSaida", "tipoSaida", "comandoCondicao", "comandoRepeticao", "subAlgoritmo"
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

	@Override
	public String getGrammarFileName() { return "gyhLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    
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


	public gyhLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramaContext extends ParserRuleContext {
		public List<TerminalNode> DELIM() { return getTokens(gyhLangParser.DELIM); }
		public TerminalNode DELIM(int i) {
			return getToken(gyhLangParser.DELIM, i);
		}
		public TerminalNode PCDEC() { return getToken(gyhLangParser.PCDEC, 0); }
		public TerminalNode PCPROG() { return getToken(gyhLangParser.PCPROG, 0); }
		public List<DeclaracaoContext> declaracao() {
			return getRuleContexts(DeclaracaoContext.class);
		}
		public DeclaracaoContext declaracao(int i) {
			return getRuleContext(DeclaracaoContext.class,i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(DELIM);
			setState(37);
			match(PCDEC);
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				declaracao();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VAR );
			setState(43);
			match(DELIM);
			setState(44);
			match(PCPROG);
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				comando();
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PCLER) | (1L << PCIMPRIMIR) | (1L << PCSE) | (1L << PCENQTO) | (1L << PCINI) | (1L << VAR))) != 0) );

			    // Como terminamos de analisar o código, vamos gerar o código
			    program.setVarTabela(varTabela);
			    program.setListCmd(listCmd);
			    program.generateTarget();

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaracaoContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(gyhLangParser.VAR, 0); }
		public TerminalNode DELIM() { return getToken(gyhLangParser.DELIM, 0); }
		public TipoVarContext tipoVar() {
			return getRuleContext(TipoVarContext.class,0);
		}
		public DeclaracaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterDeclaracao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitDeclaracao(this);
		}
	}

	public final DeclaracaoContext declaracao() throws RecognitionException {
		DeclaracaoContext _localctx = new DeclaracaoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaracao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(VAR);
			setState(53);
			match(DELIM);
			setState(54);
			tipoVar();
			 
			    addTabela(_input.LT(-3).getText(), _input.LT(-1).getText());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoVarContext extends ParserRuleContext {
		public TerminalNode PCINT() { return getToken(gyhLangParser.PCINT, 0); }
		public TerminalNode PCREAL() { return getToken(gyhLangParser.PCREAL, 0); }
		public TipoVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipoVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterTipoVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitTipoVar(this);
		}
	}

	public final TipoVarContext tipoVar() throws RecognitionException {
		TipoVarContext _localctx = new TipoVarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_tipoVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			_la = _input.LA(1);
			if ( !(_la==PCINT || _la==PCREAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressaoAritmeticaContext extends ParserRuleContext {
		public TermoAritmeticoContext termoAritmetico() {
			return getRuleContext(TermoAritmeticoContext.class,0);
		}
		public SentencaAritmeticaContext sentencaAritmetica() {
			return getRuleContext(SentencaAritmeticaContext.class,0);
		}
		public ExpressaoAritmeticaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoAritmetica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterExpressaoAritmetica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitExpressaoAritmetica(this);
		}
	}

	public final ExpressaoAritmeticaContext expressaoAritmetica() throws RecognitionException {
		ExpressaoAritmeticaContext _localctx = new ExpressaoAritmeticaContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expressaoAritmetica);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			termoAritmetico();
			setState(60);
			sentencaAritmetica();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SentencaAritmeticaContext extends ParserRuleContext {
		public TermoAritmeticoContext termoAritmetico() {
			return getRuleContext(TermoAritmeticoContext.class,0);
		}
		public ProposicaoAritmeticaContext proposicaoAritmetica() {
			return getRuleContext(ProposicaoAritmeticaContext.class,0);
		}
		public TerminalNode OPARITSOMA() { return getToken(gyhLangParser.OPARITSOMA, 0); }
		public TerminalNode OPARITSUB() { return getToken(gyhLangParser.OPARITSUB, 0); }
		public SentencaAritmeticaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentencaAritmetica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterSentencaAritmetica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitSentencaAritmetica(this);
		}
	}

	public final SentencaAritmeticaContext sentencaAritmetica() throws RecognitionException {
		SentencaAritmeticaContext _localctx = new SentencaAritmeticaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_sentencaAritmetica);
		try {
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPARITSOMA:
			case OPARITSUB:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPARITSOMA:
					{
					setState(62);
					match(OPARITSOMA);
					varExp += "+";
					}
					break;
				case OPARITSUB:
					{
					setState(64);
					match(OPARITSUB);
					varExp += "-";
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(68);
				termoAritmetico();
				setState(69);
				proposicaoAritmetica();
				}
				break;
			case EOF:
			case PCLER:
			case PCIMPRIMIR:
			case PCSE:
			case PCENTAO:
			case PCENQTO:
			case PCINI:
			case PCFIM:
			case PCSENAO:
			case VAR:
			case OPBOOLE:
			case OPBOOLOU:
			case OPRELIGUAL:
			case OPRELDIF:
			case OPRELMAIORIGUAL:
			case OPRELMENORIGUAL:
			case OPRELMAIOR:
			case OPRELMENOR:
			case FECHAPAR:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoAritmeticoContext extends ParserRuleContext {
		public FatorAritmeticoContext fatorAritmetico() {
			return getRuleContext(FatorAritmeticoContext.class,0);
		}
		public ProposicaoAritmeticaContext proposicaoAritmetica() {
			return getRuleContext(ProposicaoAritmeticaContext.class,0);
		}
		public TermoAritmeticoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoAritmetico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterTermoAritmetico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitTermoAritmetico(this);
		}
	}

	public final TermoAritmeticoContext termoAritmetico() throws RecognitionException {
		TermoAritmeticoContext _localctx = new TermoAritmeticoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_termoAritmetico);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			fatorAritmetico();
			setState(75);
			proposicaoAritmetica();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProposicaoAritmeticaContext extends ParserRuleContext {
		public FatorAritmeticoContext fatorAritmetico() {
			return getRuleContext(FatorAritmeticoContext.class,0);
		}
		public ProposicaoAritmeticaContext proposicaoAritmetica() {
			return getRuleContext(ProposicaoAritmeticaContext.class,0);
		}
		public TerminalNode OPARITMULT() { return getToken(gyhLangParser.OPARITMULT, 0); }
		public TerminalNode OPARITDIV() { return getToken(gyhLangParser.OPARITDIV, 0); }
		public ProposicaoAritmeticaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposicaoAritmetica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterProposicaoAritmetica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitProposicaoAritmetica(this);
		}
	}

	public final ProposicaoAritmeticaContext proposicaoAritmetica() throws RecognitionException {
		ProposicaoAritmeticaContext _localctx = new ProposicaoAritmeticaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_proposicaoAritmetica);
		try {
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPARITMULT:
					{
					setState(77);
					match(OPARITMULT);
					varExp += "*";
					}
					break;
				case OPARITDIV:
					{
					setState(79);
					match(OPARITDIV);
					varExp += "/";
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(83);
				fatorAritmetico();
				setState(84);
				proposicaoAritmetica();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FatorAritmeticoContext extends ParserRuleContext {
		public TerminalNode NUMINT() { return getToken(gyhLangParser.NUMINT, 0); }
		public TerminalNode NUMREAL() { return getToken(gyhLangParser.NUMREAL, 0); }
		public TerminalNode VAR() { return getToken(gyhLangParser.VAR, 0); }
		public TerminalNode ABREPAR() { return getToken(gyhLangParser.ABREPAR, 0); }
		public ExpressaoAritmeticaContext expressaoAritmetica() {
			return getRuleContext(ExpressaoAritmeticaContext.class,0);
		}
		public TerminalNode FECHAPAR() { return getToken(gyhLangParser.FECHAPAR, 0); }
		public FatorAritmeticoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fatorAritmetico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterFatorAritmetico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitFatorAritmetico(this);
		}
	}

	public final FatorAritmeticoContext fatorAritmetico() throws RecognitionException {
		FatorAritmeticoContext _localctx = new FatorAritmeticoContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fatorAritmetico);
		try {
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				match(NUMINT);
				varExp += _input.LT(-1).getText();
				}
				break;
			case NUMREAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				match(NUMREAL);

				    String numReal = _input.LT(-1).getText();
				    if(numReal.indexOf(".")+9 < numReal.length()){
				        System.err.println("\nErro Semântico - Número real com mais de 8 casas decimais.\n" +
				                            "O excedente de casas decimais foi desconsiderado!\n"+
				                            "Num: " + numReal + "\n");
				        numReal = numReal.substring(0,numReal.indexOf(".")+9);
				    }// if
				    
				    if(eVarInt == 1) System.err.println("\nErro Semântico - Tentativa de atribuição de dado real " + 
				                                numReal + " para variável inteira " + varAtrib + ".\n"+
				                                "É possível que o resultado seja diferente do esperado!\n");
				    varExp += numReal;

				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				match(VAR);

				    if(varTabela.contemChave(_input.LT(-1).getText())){
				        varExp += _input.LT(-1).getText();
				        if(varTabela.getSimbolo(_input.LT(-1).getText()).getTipo() == Simbolo.INT && eVarInt == 0)
				            System.err.println("\nErro Semântico - Tentativa de atribuição de dado inteiro de " + 
				                                _input.LT(-1).getText() + " para variável real " + varAtrib + ".\n"+
				                                "O resultado pode ser diferente do esperado!\n");
				        else if(varTabela.getSimbolo(_input.LT(-1).getText()).getTipo() == Simbolo.REAL && eVarInt == 1)
				            System.err.println("\nErro Semântico - Tentativa de atribuição de dado real de " + 
				                                _input.LT(-1).getText() + " para variável inteira " + varAtrib + ".\n"+
				                                "O resultado pode ser diferente do esperado!\n");
				    }else{
				        System.err.println("Erro Semântico - Variável \"" + _input.LT(-1).getText()  + "\" não declarada!\n");
				        System.exit(1);
				    }
				    

				}
				break;
			case ABREPAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(95);
				match(ABREPAR);
				varExp += "(";
				setState(97);
				expressaoAritmetica();
				setState(98);
				match(FECHAPAR);
				varExp += ")";
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressaoRelacionalContext extends ParserRuleContext {
		public List<TermoRelacionalContext> termoRelacional() {
			return getRuleContexts(TermoRelacionalContext.class);
		}
		public TermoRelacionalContext termoRelacional(int i) {
			return getRuleContext(TermoRelacionalContext.class,i);
		}
		public List<TerminalNode> OPBOOLE() { return getTokens(gyhLangParser.OPBOOLE); }
		public TerminalNode OPBOOLE(int i) {
			return getToken(gyhLangParser.OPBOOLE, i);
		}
		public List<TerminalNode> OPBOOLOU() { return getTokens(gyhLangParser.OPBOOLOU); }
		public TerminalNode OPBOOLOU(int i) {
			return getToken(gyhLangParser.OPBOOLOU, i);
		}
		public ExpressaoRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoRelacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterExpressaoRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitExpressaoRelacional(this);
		}
	}

	public final ExpressaoRelacionalContext expressaoRelacional() throws RecognitionException {
		ExpressaoRelacionalContext _localctx = new ExpressaoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expressaoRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			termoRelacional();
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPBOOLE || _la==OPBOOLOU) {
				{
				{
				setState(108);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPBOOLE:
					{
					setState(104);
					match(OPBOOLE);
					varExp += " E ";
					}
					break;
				case OPBOOLOU:
					{
					setState(106);
					match(OPBOOLOU);
					varExp += " OU ";
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(110);
				termoRelacional();
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoRelacionalContext extends ParserRuleContext {
		public List<ExpressaoAritmeticaContext> expressaoAritmetica() {
			return getRuleContexts(ExpressaoAritmeticaContext.class);
		}
		public ExpressaoAritmeticaContext expressaoAritmetica(int i) {
			return getRuleContext(ExpressaoAritmeticaContext.class,i);
		}
		public TerminalNode OPRELIGUAL() { return getToken(gyhLangParser.OPRELIGUAL, 0); }
		public TerminalNode OPRELDIF() { return getToken(gyhLangParser.OPRELDIF, 0); }
		public TerminalNode OPRELMAIORIGUAL() { return getToken(gyhLangParser.OPRELMAIORIGUAL, 0); }
		public TerminalNode OPRELMENORIGUAL() { return getToken(gyhLangParser.OPRELMENORIGUAL, 0); }
		public TerminalNode OPRELMAIOR() { return getToken(gyhLangParser.OPRELMAIOR, 0); }
		public TerminalNode OPRELMENOR() { return getToken(gyhLangParser.OPRELMENOR, 0); }
		public TerminalNode ABREPAR() { return getToken(gyhLangParser.ABREPAR, 0); }
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public TerminalNode FECHAPAR() { return getToken(gyhLangParser.FECHAPAR, 0); }
		public TermoRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoRelacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterTermoRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitTermoRelacional(this);
		}
	}

	public final TermoRelacionalContext termoRelacional() throws RecognitionException {
		TermoRelacionalContext _localctx = new TermoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_termoRelacional);
		try {
			setState(139);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				expressaoAritmetica();
				setState(129);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPRELIGUAL:
					{
					setState(117);
					match(OPRELIGUAL);
					varExp += " == ";
					}
					break;
				case OPRELDIF:
					{
					setState(119);
					match(OPRELDIF);
					varExp += " != ";
					}
					break;
				case OPRELMAIORIGUAL:
					{
					setState(121);
					match(OPRELMAIORIGUAL);
					varExp += " >= ";
					}
					break;
				case OPRELMENORIGUAL:
					{
					setState(123);
					match(OPRELMENORIGUAL);
					varExp += " <= ";
					}
					break;
				case OPRELMAIOR:
					{
					setState(125);
					match(OPRELMAIOR);
					varExp += " > ";
					}
					break;
				case OPRELMENOR:
					{
					setState(127);
					match(OPRELMENOR);
					varExp += " < ";
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(131);
				expressaoAritmetica();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				match(ABREPAR);
				varExp += "(";
				setState(135);
				expressaoRelacional();
				setState(136);
				match(FECHAPAR);
				varExp += ")";
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoContext extends ParserRuleContext {
		public ComandoAtribuicaoContext comandoAtribuicao() {
			return getRuleContext(ComandoAtribuicaoContext.class,0);
		}
		public ComandoEntradaContext comandoEntrada() {
			return getRuleContext(ComandoEntradaContext.class,0);
		}
		public ComandoSaidaContext comandoSaida() {
			return getRuleContext(ComandoSaidaContext.class,0);
		}
		public ComandoCondicaoContext comandoCondicao() {
			return getRuleContext(ComandoCondicaoContext.class,0);
		}
		public ComandoRepeticaoContext comandoRepeticao() {
			return getRuleContext(ComandoRepeticaoContext.class,0);
		}
		public SubAlgoritmoContext subAlgoritmo() {
			return getRuleContext(SubAlgoritmoContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_comando);
		try {
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				comandoAtribuicao();
				}
				break;
			case PCLER:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				comandoEntrada();
				}
				break;
			case PCIMPRIMIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				comandoSaida();
				}
				break;
			case PCSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(144);
				comandoCondicao();
				}
				break;
			case PCENQTO:
				enterOuterAlt(_localctx, 5);
				{
				setState(145);
				comandoRepeticao();
				}
				break;
			case PCINI:
				enterOuterAlt(_localctx, 6);
				{
				setState(146);
				subAlgoritmo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoAtribuicaoContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(gyhLangParser.VAR, 0); }
		public TerminalNode ATRIB() { return getToken(gyhLangParser.ATRIB, 0); }
		public ExpressaoAritmeticaContext expressaoAritmetica() {
			return getRuleContext(ExpressaoAritmeticaContext.class,0);
		}
		public ComandoAtribuicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoAtribuicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterComandoAtribuicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitComandoAtribuicao(this);
		}
	}

	public final ComandoAtribuicaoContext comandoAtribuicao() throws RecognitionException {
		ComandoAtribuicaoContext _localctx = new ComandoAtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_comandoAtribuicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(VAR);

			    if( varTabela.contemChave(_input.LT(-1).getText()) ){

			        varAtrib = _input.LT(-1).getText();
			        if(varTabela.getSimbolo(varAtrib).getTipo() == Simbolo.INT) eVarInt = 1;
			        else eVarInt = 0;

			    }else {
			        System.err.println("Erro semântico - Variável " + _input.LT(-1).getText() + " não existe para atribuição!\n");
			        System.exit(1);
			    }
			    varExp = "";

			setState(151);
			match(ATRIB);
			setState(152);
			expressaoAritmetica();

			    eVarInt = -1;
			    if(!varAtrib.equals("")){
			        listCmd.add(new ComandoAtribuicao(varAtrib,varExp));
			    }

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoEntradaContext extends ParserRuleContext {
		public TerminalNode PCLER() { return getToken(gyhLangParser.PCLER, 0); }
		public TerminalNode VAR() { return getToken(gyhLangParser.VAR, 0); }
		public ComandoEntradaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoEntrada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterComandoEntrada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitComandoEntrada(this);
		}
	}

	public final ComandoEntradaContext comandoEntrada() throws RecognitionException {
		ComandoEntradaContext _localctx = new ComandoEntradaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_comandoEntrada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(PCLER);
			setState(156);
			match(VAR);

			    if(varTabela.contemChave(_input.LT(-1).getText())){
			        listCmd.add(new ComandoLeitura(_input.LT(-1).getText(),varTabela.getSimbolo(_input.LT(-1).getText()).getTipo()));
			    } else{
			        System.err.println("Erro semântico - Variável " + _input.LT(-1).getText() + " não existe para salvar os dados a serem lidos.");
			    }

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoSaidaContext extends ParserRuleContext {
		public TerminalNode PCIMPRIMIR() { return getToken(gyhLangParser.PCIMPRIMIR, 0); }
		public TipoSaidaContext tipoSaida() {
			return getRuleContext(TipoSaidaContext.class,0);
		}
		public ComandoSaidaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoSaida; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterComandoSaida(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitComandoSaida(this);
		}
	}

	public final ComandoSaidaContext comandoSaida() throws RecognitionException {
		ComandoSaidaContext _localctx = new ComandoSaidaContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comandoSaida);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(PCIMPRIMIR);
			setState(160);
			tipoSaida();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoSaidaContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(gyhLangParser.VAR, 0); }
		public TerminalNode CADEIA() { return getToken(gyhLangParser.CADEIA, 0); }
		public TipoSaidaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipoSaida; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterTipoSaida(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitTipoSaida(this);
		}
	}

	public final TipoSaidaContext tipoSaida() throws RecognitionException {
		TipoSaidaContext _localctx = new TipoSaidaContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_tipoSaida);
		try {
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(VAR);

				    if(varTabela.contemChave(_input.LT(-1).getText())){
				        Simbolo simbAux = varTabela.getSimbolo(_input.LT(-1).getText());
				        listCmd.add(new ComandoEscrita(simbAux.getNome(),simbAux.getTipo()));
				    }else{
				        System.err.println("Erro Semantico - Variavel" + _input.LT(-1).getText() + " não declarada!");
				    }

				}
				break;
			case CADEIA:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				match(CADEIA);

				    listCmd.add(new ComandoEscrita(_input.LT(-1).getText(),-1));

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoCondicaoContext extends ParserRuleContext {
		public TerminalNode PCSE() { return getToken(gyhLangParser.PCSE, 0); }
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public TerminalNode PCENTAO() { return getToken(gyhLangParser.PCENTAO, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public TerminalNode PCSENAO() { return getToken(gyhLangParser.PCSENAO, 0); }
		public ComandoCondicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoCondicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterComandoCondicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitComandoCondicao(this);
		}
	}

	public final ComandoCondicaoContext comandoCondicao() throws RecognitionException {
		ComandoCondicaoContext _localctx = new ComandoCondicaoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_comandoCondicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(PCSE);
			varExp = ""; int indice = -1;
			setState(170);
			expressaoRelacional();
			varCond = varExp;
			setState(172);
			match(PCENTAO);

			    indices.add(listCmd.size());
			    cmdFalse = null;

			setState(174);
			comando();

			    indice = indices.get(indices.size()-1);
			    cmdTrue = listCmd.remove(indice);
			    indices.remove(indices.size()-1);

			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(176);
				match(PCSENAO);
				indices.add(listCmd.size());
				setState(178);
				comando();

				    indice = indices.get(indices.size()-1);
				    cmdFalse = listCmd.remove(indice);
				    indices.remove(indices.size()-1);

				}
				break;
			}

			    listCmd.add(new ComandoCondicao(varCond,cmdTrue,cmdFalse));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoRepeticaoContext extends ParserRuleContext {
		public TerminalNode PCENQTO() { return getToken(gyhLangParser.PCENQTO, 0); }
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public ComandoContext comando() {
			return getRuleContext(ComandoContext.class,0);
		}
		public ComandoRepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoRepeticao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterComandoRepeticao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitComandoRepeticao(this);
		}
	}

	public final ComandoRepeticaoContext comandoRepeticao() throws RecognitionException {
		ComandoRepeticaoContext _localctx = new ComandoRepeticaoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_comandoRepeticao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(PCENQTO);

			    varExp = ""; 
			    int indice = -1;

			setState(187);
			expressaoRelacional();

			    varCond = varExp;
			    indices.add(listCmd.size());

			setState(189);
			comando();

			    indice = indices.get(indices.size()-1);
			    cmdTrue = listCmd.remove(indice);
			    indices.remove(indices.size()-1);
			    listCmd.add(new ComandoRepeticao(varCond,cmdTrue));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubAlgoritmoContext extends ParserRuleContext {
		public TerminalNode PCINI() { return getToken(gyhLangParser.PCINI, 0); }
		public TerminalNode PCFIM() { return getToken(gyhLangParser.PCFIM, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public SubAlgoritmoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subAlgoritmo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).enterSubAlgoritmo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gyhLangListener ) ((gyhLangListener)listener).exitSubAlgoritmo(this);
		}
	}

	public final SubAlgoritmoContext subAlgoritmo() throws RecognitionException {
		SubAlgoritmoContext _localctx = new SubAlgoritmoContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_subAlgoritmo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(PCINI);
			indices.add(listCmd.size()); int indice = -1;
			setState(195); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(194);
				comando();
				}
				}
				setState(197); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PCLER) | (1L << PCIMPRIMIR) | (1L << PCSE) | (1L << PCENQTO) | (1L << PCINI) | (1L << VAR))) != 0) );
			setState(199);
			match(PCFIM);

			    listSub = new ArrayList<>();
			    indice = indices.get(indices.size()-1);
			    while(indice < listCmd.size()){
			        listSub.add(listCmd.remove(indice));
			    }
			    indices.remove(indices.size()-1);
			    listCmd.add(new ComandoSubAlgoritmo(listSub));

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$\u00cd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\6\2*\n\2\r\2\16\2+\3\2\3\2\3\2\6\2\61\n\2\r\2\16"+
		"\2\62\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\5\6E\n\6\3\6\3\6\3\6\3\6\5\6K\n\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\5\bT\n"+
		"\b\3\b\3\b\3\b\3\b\5\bZ\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\th\n\t\3\n\3\n\3\n\3\n\3\n\5\no\n\n\3\n\7\nr\n\n\f\n\16\nu\13"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5"+
		"\13\u0084\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u008e\n\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0096\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\5\20\u00a9\n\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00b8"+
		"\n\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\6\23"+
		"\u00c6\n\23\r\23\16\23\u00c7\3\23\3\23\3\23\3\23\2\2\24\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$\2\3\4\2\7\7\t\t\2\u00d3\2&\3\2\2\2\4\66\3"+
		"\2\2\2\6;\3\2\2\2\b=\3\2\2\2\nJ\3\2\2\2\fL\3\2\2\2\16Y\3\2\2\2\20g\3\2"+
		"\2\2\22i\3\2\2\2\24\u008d\3\2\2\2\26\u0095\3\2\2\2\30\u0097\3\2\2\2\32"+
		"\u009d\3\2\2\2\34\u00a1\3\2\2\2\36\u00a8\3\2\2\2 \u00aa\3\2\2\2\"\u00bb"+
		"\3\2\2\2$\u00c2\3\2\2\2&\'\7 \2\2\')\7\5\2\2(*\5\4\3\2)(\3\2\2\2*+\3\2"+
		"\2\2+)\3\2\2\2+,\3\2\2\2,-\3\2\2\2-.\7 \2\2.\60\7\6\2\2/\61\5\26\f\2\60"+
		"/\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65"+
		"\b\2\1\2\65\3\3\2\2\2\66\67\7\22\2\2\678\7 \2\289\5\6\4\29:\b\3\1\2:\5"+
		"\3\2\2\2;<\t\2\2\2<\7\3\2\2\2=>\5\f\7\2>?\5\n\6\2?\t\3\2\2\2@A\7\23\2"+
		"\2AE\b\6\1\2BC\7\24\2\2CE\b\6\1\2D@\3\2\2\2DB\3\2\2\2EF\3\2\2\2FG\5\f"+
		"\7\2GH\5\16\b\2HK\3\2\2\2IK\3\2\2\2JD\3\2\2\2JI\3\2\2\2K\13\3\2\2\2LM"+
		"\5\20\t\2MN\5\16\b\2N\r\3\2\2\2OP\7\25\2\2PT\b\b\1\2QR\7\26\2\2RT\b\b"+
		"\1\2SO\3\2\2\2SQ\3\2\2\2TU\3\2\2\2UV\5\20\t\2VW\5\16\b\2WZ\3\2\2\2XZ\3"+
		"\2\2\2YS\3\2\2\2YX\3\2\2\2Z\17\3\2\2\2[\\\7$\2\2\\h\b\t\1\2]^\7#\2\2^"+
		"h\b\t\1\2_`\7\22\2\2`h\b\t\1\2ab\7!\2\2bc\b\t\1\2cd\5\b\5\2de\7\"\2\2"+
		"ef\b\t\1\2fh\3\2\2\2g[\3\2\2\2g]\3\2\2\2g_\3\2\2\2ga\3\2\2\2h\21\3\2\2"+
		"\2is\5\24\13\2jk\7\27\2\2ko\b\n\1\2lm\7\30\2\2mo\b\n\1\2nj\3\2\2\2nl\3"+
		"\2\2\2op\3\2\2\2pr\5\24\13\2qn\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t"+
		"\23\3\2\2\2us\3\2\2\2v\u0083\5\b\5\2wx\7\31\2\2x\u0084\b\13\1\2yz\7\32"+
		"\2\2z\u0084\b\13\1\2{|\7\33\2\2|\u0084\b\13\1\2}~\7\34\2\2~\u0084\b\13"+
		"\1\2\177\u0080\7\35\2\2\u0080\u0084\b\13\1\2\u0081\u0082\7\36\2\2\u0082"+
		"\u0084\b\13\1\2\u0083w\3\2\2\2\u0083y\3\2\2\2\u0083{\3\2\2\2\u0083}\3"+
		"\2\2\2\u0083\177\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0086\5\b\5\2\u0086\u008e\3\2\2\2\u0087\u0088\7!\2\2\u0088\u0089\b\13"+
		"\1\2\u0089\u008a\5\22\n\2\u008a\u008b\7\"\2\2\u008b\u008c\b\13\1\2\u008c"+
		"\u008e\3\2\2\2\u008dv\3\2\2\2\u008d\u0087\3\2\2\2\u008e\25\3\2\2\2\u008f"+
		"\u0096\5\30\r\2\u0090\u0096\5\32\16\2\u0091\u0096\5\34\17\2\u0092\u0096"+
		"\5 \21\2\u0093\u0096\5\"\22\2\u0094\u0096\5$\23\2\u0095\u008f\3\2\2\2"+
		"\u0095\u0090\3\2\2\2\u0095\u0091\3\2\2\2\u0095\u0092\3\2\2\2\u0095\u0093"+
		"\3\2\2\2\u0095\u0094\3\2\2\2\u0096\27\3\2\2\2\u0097\u0098\7\22\2\2\u0098"+
		"\u0099\b\r\1\2\u0099\u009a\7\37\2\2\u009a\u009b\5\b\5\2\u009b\u009c\b"+
		"\r\1\2\u009c\31\3\2\2\2\u009d\u009e\7\b\2\2\u009e\u009f\7\22\2\2\u009f"+
		"\u00a0\b\16\1\2\u00a0\33\3\2\2\2\u00a1\u00a2\7\n\2\2\u00a2\u00a3\5\36"+
		"\20\2\u00a3\35\3\2\2\2\u00a4\u00a5\7\22\2\2\u00a5\u00a9\b\20\1\2\u00a6"+
		"\u00a7\7\21\2\2\u00a7\u00a9\b\20\1\2\u00a8\u00a4\3\2\2\2\u00a8\u00a6\3"+
		"\2\2\2\u00a9\37\3\2\2\2\u00aa\u00ab\7\13\2\2\u00ab\u00ac\b\21\1\2\u00ac"+
		"\u00ad\5\22\n\2\u00ad\u00ae\b\21\1\2\u00ae\u00af\7\f\2\2\u00af\u00b0\b"+
		"\21\1\2\u00b0\u00b1\5\26\f\2\u00b1\u00b7\b\21\1\2\u00b2\u00b3\7\20\2\2"+
		"\u00b3\u00b4\b\21\1\2\u00b4\u00b5\5\26\f\2\u00b5\u00b6\b\21\1\2\u00b6"+
		"\u00b8\3\2\2\2\u00b7\u00b2\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2"+
		"\2\2\u00b9\u00ba\b\21\1\2\u00ba!\3\2\2\2\u00bb\u00bc\7\r\2\2\u00bc\u00bd"+
		"\b\22\1\2\u00bd\u00be\5\22\n\2\u00be\u00bf\b\22\1\2\u00bf\u00c0\5\26\f"+
		"\2\u00c0\u00c1\b\22\1\2\u00c1#\3\2\2\2\u00c2\u00c3\7\16\2\2\u00c3\u00c5"+
		"\b\23\1\2\u00c4\u00c6\5\26\f\2\u00c5\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2"+
		"\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca"+
		"\7\17\2\2\u00ca\u00cb\b\23\1\2\u00cb%\3\2\2\2\21+\62DJSYgns\u0083\u008d"+
		"\u0095\u00a8\u00b7\u00c7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}