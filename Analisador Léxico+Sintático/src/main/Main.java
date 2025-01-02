
package main;

import analisadorlexico.AnalisadorLexico;
import analisadorlexico.TipoToken;
import analisadorsintatico.AnalisadorSintatico;
import java.util.ArrayList;

/**
 * Abstrai o controle do fluxo de execução do algoritmo.
 * @author Gabriel Finger Conte
 * @author João Vitor Garcia Carvalho
 */
public class Main {
    
    /**
     * Controla a criação do Analisador Léxico e a análise do arquivo de código
     * fonte, caso seja considerado possível.
     * @param args parâmetros passados para o programa, devendo incluir o caminho 
     * do arquivo .gyh com o código fonte a ser analisado.
     */
    public static void main(String[] args) {
                
        // Verifica se o caminho foi passado via argumento
        if(args.length >= 2){
            // Cria um analisador léxico para o código no caminho passado por argumento
            AnalisadorLexico analisadorLexico = new AnalisadorLexico(args[1]);
            // Verifica se o arquivo com o código fonte foi aberto com sucesso
            if(analisadorLexico.temCodigo()){
                // Analisa o código
                analisadorLexico.analisaCodigo();
                // Salva o fluxo de tokens gerado pela análise léxica, o mapa de linhas
                ArrayList<TipoToken> fluxoTokens = analisadorLexico.getFluxoTokens();
                ArrayList<Integer> mapaTokenLinha = analisadorLexico.getMapaTokenLinha();
                // Libera a memória usada pelo analisador léxico
                analisadorLexico = null;
                
                // Cria um analisador sintático para prosseguir com a compilação do código
                AnalisadorSintatico analisadorSintatico;
                // Após a análise léxica, passa o fluxo de tokens para a análise sintática
                analisadorSintatico = new AnalisadorSintatico(fluxoTokens, mapaTokenLinha);
                
            }else{// Caso haja problema na abertura do arquivo
                System.err.println("File Error - Erro na abertura do arquivo!");
                System.err.println("Confira o caminho:\n"+args[1]);
            }// if-else
            
        }else{// Caso nenhum caminho tenha sido passado
            System.err.println("Input Error - Nenhum caminho de arquivo foi informado!");
        }// if-else
        
    }
    
}
