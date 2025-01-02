
package manipulacaoarquivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Serve para abstrair o processo de leitura de um arquivo, podendo abrir o arquivo e ler
 * o condeudo do mesmo.
 * @author Gabriel Finger Conte
 * @author João Vitor Garcia Carvalho
 */
public class LeitorArquivos {
    
    /**
     * O arquivo com o código fonte.
     */
    private FileReader codigoFonte;
    /**
     * A ferramenta para ler o código fonte.
     */
    private BufferedReader leitor;
    /**
     * O número da linha, usado para referenciar a ultima linha lida.
     */
    private int numLinha;
    
    /**
     * Cria uma nova instância de um leitor de arquivos, dado o caminho onde o arquivo
     * que será lido está salvo.
     * @param caminhoArquivo endereço onde o arquivo será salvo.
     */
    public LeitorArquivos(String caminhoArquivo) {
        try {
            codigoFonte = new FileReader(caminhoArquivo);
            leitor = new BufferedReader(codigoFonte);
            numLinha = 0;
        } catch (FileNotFoundException ex) {
            System.err.println("Erro ao abrir o arquivo, verifique o caminho!");
            Logger.getLogger(LeitorArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * A partir do ponteiro no arquivo, lê e salva todos os dados até encontrar uma
     * quebra de linha ou o fim do arquivo.
     * @return uma linha do arquivo como uma string.
     */
    public String getLinha(){
        try {
            String linha = leitor.readLine();
            numLinha++;
            return linha;
        } catch (IOException ex) {
            System.err.println("Erro ao ler o arquivo!");
            Logger.getLogger(LeitorArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Retorna a numeração da ultima leitura de linha no arquivo.
     * @return a numeração da linha.
     */
    public int getNumLinha(){
        return numLinha;
    }
    
    /**
     * Fecha o arquivo que está sendo manipulado, liberando a memória
     * das variáveis usadas para o mesmo.
     */
    public void fechaArquivo(){
        try {
            codigoFonte.close();
            leitor.close();
        } catch (IOException ex) {
            System.err.println("Erro ao fechar o arquivo, verifique o caminho!");
            Logger.getLogger(LeitorArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
