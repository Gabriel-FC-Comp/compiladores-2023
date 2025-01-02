/*
    Link para o vídeo da apresentação.
    https://drive.google.com/file/d/1eCxWO8B1lC1UTOEWJH4kezTlxzrPPE8T/view?usp=sharing
*/
package main;

import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import analisadores.*;


/**
 * Classe responsável por controlar a lógica do fluxo de transpilação da linguagem
 * Gyh para a linguagem C.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public class Main {

    /**
     * Função main que controla a lógica do transpilador.
     * @param args Argumentos passados na inicialização do programa
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        // Abre o código fonte em linguagem Gyh, transformando-o em um fluxo de char
        CharStream cs = CharStreams.fromFileName("testeSemErros.gyh");
        
        // Gera o analisador léxico, passando o programa fonte.
        gyhLangLexer lexer = new gyhLangLexer(cs);        
        
        // Gera o fluxo de tokens a partir do analisador léxico
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        // Gera o parser para o fluxo de tokens obtido
        gyhLangParser parser = new gyhLangParser(tokens);
        
        // Inicia a transpilação
        parser.programa();
    }
    
}
