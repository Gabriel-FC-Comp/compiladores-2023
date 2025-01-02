
package manipulacaoarquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Serve para abstrair o processo de escrita em um arquivo, podendo apenas abrir um arquivo e
 * adicionar dados no mesmo.
 * @author Gabriel Finger Conte
 * @author João Vitor Garcia Carvalho
 */
public class EscritorArquivos {
    
    /**
     * O arquivo que será escrito.
     */
    private FileWriter arquivo;
    /**
     * A ferramenta para escrever no arquivo.
     */
    private PrintWriter escritor;
    
    /**
     * Cria uma nova instância de um escritor de arquivos, dado o caminho onde o arquivo
     * deve ser salvo.
     * @param caminhoArquivo endereço onde o arquivo será salvo.
     */
    public EscritorArquivos(String caminhoArquivo) {
        
        try {
            this.arquivo = new FileWriter(caminhoArquivo);
            this.escritor = new PrintWriter(this.arquivo);
        } catch (IOException ex) {
            System.err.println("Erro ao abrir o arquivo, verifique o caminho!");
            Logger.getLogger(EscritorArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Escreve uma frase no arquivo, a partir de onde o ponteiro do arquivo parou.
     * @param frase string que deve ser adicionada ao arquivo.
     */
    public void concatenaString(String frase){
        escritor.print(frase);
    }
    
    /**
     * Adiciona uma quebra de linha no arquivo onde o ponteiro está marcando.
     */
    public void breakLine(){
        escritor.print("\n");
    }
    
    /**
     * Fecha o arquivo que está sendo manipulado, salvando-o e liberando a memória
     * das variáveis usadas para o mesmo.
     */
    public void fechaArquivo(){
        try {
            this.arquivo.close();
            this.escritor.close();
        } catch (IOException ex) {
            System.err.println("Erro ao fechar o arquivo, verifique o caminho!");
            Logger.getLogger(EscritorArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
