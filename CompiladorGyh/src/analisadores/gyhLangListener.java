// Generated from .\gyhLang.g4 by ANTLR 4.7.2

    // Pacote onde serão salvos os analisadores
    package analisadores;
    // Bibliotecas necessárias
    import java.util.ArrayList;
    import comandos.*;
    import main.GyhProgram;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link gyhLangParser}.
 */
public interface gyhLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(gyhLangParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(gyhLangParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#declaracao}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao(gyhLangParser.DeclaracaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#declaracao}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao(gyhLangParser.DeclaracaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#tipoVar}.
	 * @param ctx the parse tree
	 */
	void enterTipoVar(gyhLangParser.TipoVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#tipoVar}.
	 * @param ctx the parse tree
	 */
	void exitTipoVar(gyhLangParser.TipoVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#expressaoAritmetica}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoAritmetica(gyhLangParser.ExpressaoAritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#expressaoAritmetica}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoAritmetica(gyhLangParser.ExpressaoAritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#sentencaAritmetica}.
	 * @param ctx the parse tree
	 */
	void enterSentencaAritmetica(gyhLangParser.SentencaAritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#sentencaAritmetica}.
	 * @param ctx the parse tree
	 */
	void exitSentencaAritmetica(gyhLangParser.SentencaAritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#termoAritmetico}.
	 * @param ctx the parse tree
	 */
	void enterTermoAritmetico(gyhLangParser.TermoAritmeticoContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#termoAritmetico}.
	 * @param ctx the parse tree
	 */
	void exitTermoAritmetico(gyhLangParser.TermoAritmeticoContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#proposicaoAritmetica}.
	 * @param ctx the parse tree
	 */
	void enterProposicaoAritmetica(gyhLangParser.ProposicaoAritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#proposicaoAritmetica}.
	 * @param ctx the parse tree
	 */
	void exitProposicaoAritmetica(gyhLangParser.ProposicaoAritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#fatorAritmetico}.
	 * @param ctx the parse tree
	 */
	void enterFatorAritmetico(gyhLangParser.FatorAritmeticoContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#fatorAritmetico}.
	 * @param ctx the parse tree
	 */
	void exitFatorAritmetico(gyhLangParser.FatorAritmeticoContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#expressaoRelacional}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoRelacional(gyhLangParser.ExpressaoRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#expressaoRelacional}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoRelacional(gyhLangParser.ExpressaoRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#termoRelacional}.
	 * @param ctx the parse tree
	 */
	void enterTermoRelacional(gyhLangParser.TermoRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#termoRelacional}.
	 * @param ctx the parse tree
	 */
	void exitTermoRelacional(gyhLangParser.TermoRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterComando(gyhLangParser.ComandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitComando(gyhLangParser.ComandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#comandoAtribuicao}.
	 * @param ctx the parse tree
	 */
	void enterComandoAtribuicao(gyhLangParser.ComandoAtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#comandoAtribuicao}.
	 * @param ctx the parse tree
	 */
	void exitComandoAtribuicao(gyhLangParser.ComandoAtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#comandoEntrada}.
	 * @param ctx the parse tree
	 */
	void enterComandoEntrada(gyhLangParser.ComandoEntradaContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#comandoEntrada}.
	 * @param ctx the parse tree
	 */
	void exitComandoEntrada(gyhLangParser.ComandoEntradaContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#comandoSaida}.
	 * @param ctx the parse tree
	 */
	void enterComandoSaida(gyhLangParser.ComandoSaidaContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#comandoSaida}.
	 * @param ctx the parse tree
	 */
	void exitComandoSaida(gyhLangParser.ComandoSaidaContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#tipoSaida}.
	 * @param ctx the parse tree
	 */
	void enterTipoSaida(gyhLangParser.TipoSaidaContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#tipoSaida}.
	 * @param ctx the parse tree
	 */
	void exitTipoSaida(gyhLangParser.TipoSaidaContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#comandoCondicao}.
	 * @param ctx the parse tree
	 */
	void enterComandoCondicao(gyhLangParser.ComandoCondicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#comandoCondicao}.
	 * @param ctx the parse tree
	 */
	void exitComandoCondicao(gyhLangParser.ComandoCondicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#comandoRepeticao}.
	 * @param ctx the parse tree
	 */
	void enterComandoRepeticao(gyhLangParser.ComandoRepeticaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#comandoRepeticao}.
	 * @param ctx the parse tree
	 */
	void exitComandoRepeticao(gyhLangParser.ComandoRepeticaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link gyhLangParser#subAlgoritmo}.
	 * @param ctx the parse tree
	 */
	void enterSubAlgoritmo(gyhLangParser.SubAlgoritmoContext ctx);
	/**
	 * Exit a parse tree produced by {@link gyhLangParser#subAlgoritmo}.
	 * @param ctx the parse tree
	 */
	void exitSubAlgoritmo(gyhLangParser.SubAlgoritmoContext ctx);
}