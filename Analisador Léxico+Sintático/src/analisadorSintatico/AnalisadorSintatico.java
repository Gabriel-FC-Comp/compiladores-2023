/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorsintatico;

import analisadorlexico.TipoToken;
import java.util.ArrayList;

/**
 * Representa o analisador sintático, contendo toda a lógica para a análise sintática
 * de um arquivo na linguagem GYH.
 * @author Gabriel Finger Conte
 * @author João Vitor Garcia Carvalho
 */
public class AnalisadorSintatico {

    /**
     * Índice utilizado no controle da análise sintática.
     */
    private int indiceTokens = -1;
    /**
     * Array contendo o fluxo de tokens gerado pela análise léxica.
     */
    private ArrayList<TipoToken> fluxoTokens = null;
    
    /**
     * Array contendo o mapa de tokens por linha pela análise léxica.
     */
    private ArrayList<Integer> mapaTokenLinha = null;
    
    /**
     * Inicia o analisador sintático, instanciando os objetos necessários para a 
     * análise sintática e definindo a lógica utilizada para a mesma.
     * @param fluxoTokens Array contendo o fluxo de tokens gerado na análise léxica.
     * @param mapaTokenLinha Array contendo qual linha cada token pertence.
     */
    public AnalisadorSintatico(ArrayList<TipoToken> fluxoTokens, ArrayList<Integer> mapaTokenLinha) {
        // Inicia o fluxo de tokens e o mapeamento do mesmo
        this.fluxoTokens = fluxoTokens;
        this.mapaTokenLinha = mapaTokenLinha;
        // Inicia o índice de análise no início do fluxo
        this.indiceTokens = 0;
        
        // Verifica a gramática a partir do símbolo inicial
        
        // Caso não tenha apresentado nenhum erro, a análise sintática foi concluída
        System.out.println("Anáise sintática concluída sem erros!");
    }
    
    /**
     * Analisa as produções gramaticais do símbolo não terminal ExpressaoAritmetica
     */
    private void expressaoAritmetica(){
        termoAritmetico();
        sentencaAritmetica();
    }
    
    /**
     * Analisa as produções gramaticais do símbolo não terminal SentencaAritmetica
     */
    private void sentencaAritmetica(){
        if(verificaFimFluxo()){
            
        }else if(fluxoTokens.get(indiceTokens) == TipoToken.OpAritSoma){
            match(TipoToken.OpAritSoma);
            termoAritmetico();
            sentencaAritmetica();
        }else if(fluxoTokens.get(indiceTokens) == TipoToken.OpAritSub){
            match(TipoToken.OpAritSub);
            termoAritmetico();
            sentencaAritmetica();
        }
    }
    
    /**
     * Analisa as produções gramaticais do símbolo não terminal TermoAritmetico
     */
    private void termoAritmetico(){
        fatorAritmetico();
        proposicaoAritmetica();
    }
    
    /**
     * Analisa as produções gramaticais do símbolo não terminal ProposicaoAritmetica
     */
    private void proposicaoAritmetica(){
        if(verificaFimFluxo()){
            
        }else if(fluxoTokens.get(indiceTokens) == TipoToken.OpAritMult){
            match(TipoToken.OpAritMult);
            termoAritmetico();
            sentencaAritmetica();
        }else if(fluxoTokens.get(indiceTokens) == TipoToken.OpAritDiv){
            match(TipoToken.OpAritDiv);
            termoAritmetico();
            sentencaAritmetica();
        }
    }
    
    /**
     * Analisa as produções gramaticais do símbolo não terminal FatorAritmetico
     */
    private void fatorAritmetico(){
        if(verificaFimFluxo()){
            System.err.println("Erro Sintático - Linha " + mapaTokenLinha.get(indiceTokens-1) + " - FatorAritmético Esperado");
            System.exit(1);
        }else if(fluxoTokens.get(indiceTokens) == TipoToken.NumInt){
            match(TipoToken.NumInt);
        }else if(fluxoTokens.get(indiceTokens) == TipoToken.NumReal){
            match(TipoToken.NumReal);
        }else if(fluxoTokens.get(indiceTokens) == TipoToken.Var){
            match(TipoToken.Var);
        }else{
            match(TipoToken.AbrePar);
            expressaoAritmetica();
            match(TipoToken.FechaPar);
        }
    }
    
    /**
     * Analisa as produções gramaticais do símbolo não terminal ExpressaoRelacional
     */
    private void expressaoRelacional(){
        termoRelacional();
        sentencaRelacional();
    }
    
    /**
     * Analisa as produções gramaticais do símbolo não terminal SentencaRelacional
     */
    private void sentencaRelacional(){
        if(verificaFimFluxo()){
            
        }else if(fluxoTokens.get(indiceTokens) == TipoToken.OpBoolE){
            match(TipoToken.OpBoolE);
            termoRelacional();
            sentencaRelacional();
        }else if(fluxoTokens.get(indiceTokens) == TipoToken.OpBoolOu){
            match(TipoToken.OpBoolOu);
            termoRelacional();
            sentencaRelacional();
        }
    }
    
    /**
     * Analisa as produções gramaticais do símbolo não terminal TermoRelacional
     */
    private void termoRelacional(){
        
        if(verificaFimFluxo()){
            System.err.println("Erro Sintático - Linha" + mapaTokenLinha.get(indiceTokens-1) + " - TermoRelacional Esperado");
            System.exit(1);
        }else if(fluxoTokens.get(indiceTokens) == TipoToken.AbrePar){
            if(tryExpressaoAritmetica()){
                expressaoAritmetica();
                matchOpRel();
                expressaoAritmetica();
            }else{
                match(TipoToken.AbrePar);
                expressaoRelacional();
                match(TipoToken.FechaPar);
            }
        }else{
            expressaoAritmetica();
            matchOpRel();
            expressaoAritmetica();
        }
        
    }
    
    /**
     * Função que verifica se o tipo de token esperado na posição atual do fluxo de
     * tokens corresponde com o que se tem, onde caso verdadeiro, passa-se para a análise
     * do próximo token, caso contrário tem-se um erro sintático.
     * @param tipoEsperado Fornece o tipo esperado de token para a posição atual do fluxo,
     *                     determinado pela regra de produção em que é chamado.
     */
    private void match(TipoToken tipoEsperado){
        // Verifica se existem mais tokens do fluxo que podem ser analisados
        if(verificaFimFluxo()){
            // Caso não haja, uma regra de produção não foi respeitada, sendo um erro sintático
            System.err.println("Erro Sintático - Linha" + mapaTokenLinha.get(indiceTokens-1) + " - " + tipoEsperado + " Esperado");
            System.exit(1);
        }
        
        // Obtém o token que está sendo analisado pela regra de produção atual
        TipoToken tokenAtual = fluxoTokens.get(indiceTokens);
        // Verifica o token é do tipo esperado
        if(tokenAtual == tipoEsperado){
            System.out.println("Match " + tipoEsperado.toString());
        }else{// Caso contrário, temos um erro sintático
            System.err.println("Erro Sintático - Linha" + mapaTokenLinha.get(indiceTokens) + " - " + tipoEsperado + " Esperado");
            System.exit(1);
        }
        // Atualiza o ponteio da posição no fluxo de tokens que está sendo analisada
        indiceTokens++;
    }
    
    /**
     * Versão adaptada da função de match para os tipos de tokens que são operadores
     * relacionais.
     */
    private void matchOpRel(){
        // Verifica se existem mais tokens do fluxo que podem ser analisados
        if(verificaFimFluxo()){
            // Caso não haja, uma regra de produção não foi respeitada, sendo um erro sintático
            System.err.println("Erro Sintático Linha " + mapaTokenLinha.get(indiceTokens-1) + " - OpRel Esperado");
            System.exit(1);
        }
        // Obtém o token que está sendo analisado pela regra de produção atual
        TipoToken tokenAtual = fluxoTokens.get(indiceTokens);
        // Verifica o token é um operador relacional
        if(tryOpRel(tokenAtual)){
            System.out.println("Match OpRel");
        }else{// Caso contrário, temos um erro sintático
            System.err.println("Erro Sintático " + mapaTokenLinha.get(indiceTokens) + " - Operador Relacional Esperado");
            System.exit(1);
        }
        indiceTokens++;
    }
    
    /**
     * Verifica se já foram analisados todos os tokens presentes no fluxo de tokens
     * gerado pelo analisador léxico.
     * @return Retorna true caso todos os tokens tenham sido analisados, caso contrário
     *          retorna false.
     */
    private boolean verificaFimFluxo(){
        return indiceTokens == fluxoTokens.size();
    }
    
    /**
     * Verifica se a sequência de tokens entre parênteses refere-se ao não terminal
     * ExpressãoAritmética.
     * @return Retorna true caso a sequência de tokens represente uma expressão aritmética,
     *          retornando false caso contrário.
     */
    private boolean tryExpressaoAritmetica(){
        // Salva o index atual, para verificar o fluxo sem alterá-lo
        int index = indiceTokens;
        // Cria uma instancia para analisar o tipo de token em cada índice
        TipoToken tokenAtual;
        // Até que se encontre um Fecha Parênteses, percorre o fluxo de tokens
        for(;fluxoTokens.get(index) != TipoToken.FechaPar;index++){
            // Obtém o token no índice a ser analisado
            tokenAtual = fluxoTokens.get(index);
            // Verifica se existe um operador relacional antes do fechamento dos parênteses
            if(tryOpRel(tokenAtual)){
                // Caso haja, trata-se de uma expressão relacional e não aritmética, logo
                // retorna false
                return false;
            }
        }
        // Caso enontre o fecha parenteses sem problema nenhum, trata-se de uma expressão
        // aritmética.
        return true;
    }
    
    /**
     * Verifica se o tipo de token informado é um tipo de token referente a um
     * operador relacional.
     * @param token Refere-se ao token que será verificado.
     * @return Retorna true caso o token seja um operador relacional, retornando
     *          false em caso contrário.
     */
    private boolean tryOpRel(TipoToken token){
        return (token == TipoToken.OpRelDif || token == TipoToken.OpRelIgual ||
                token == TipoToken.OpRelMaior || token == TipoToken.OpRelMaiorIgual ||
                token == TipoToken.OpRelMenor || token == TipoToken.OpRelMenorIgual);
    }
    
}