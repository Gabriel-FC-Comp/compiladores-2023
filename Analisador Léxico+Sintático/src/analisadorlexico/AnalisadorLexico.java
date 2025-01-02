
package analisadorlexico;

import java.util.ArrayList;
import manipulacaoarquivos.*;

/**
 * Representa o analisador léxico, contendo toda a lógica para a análise léxica
 * de um arquivo na linguagem GYH.
 * @author Gabriel Finger Conte
 * @author João Vitor Garcia Carvalho
 */
public class AnalisadorLexico{

    /**
     * O arquivo com o código fonte.
     */
    private LeitorArquivos codigo = null;
    /**
     * O arquivo onde serão salvos os tokens.
     */
    private EscritorArquivos arqTokens = null;
    /**
     * O AFD usado para reconhecer as palavras da linguagem GYH.
     */
    private AFD afd = null;
    /**
     * O lexema que será analisado e usado para gerar os tokens.
     */
    private String lexema = "";
    /**
     * A quantidade de erros léxicos encontrados.
     */
    private int qtdeErros;
    /**
     * O fluxo de tokens, que será usado na análise sintática.
     */
    private ArrayList<TipoToken>fluxoTokens;
    /**
     * Array contendo o mapa de tokens por linha.
     */
    private ArrayList<Integer> mapaTokenLinha;
    
    /**
     * Cria um novo Analisador léxico para analisar um arquivo com código fonte
     * na linguagem GYH.
     * @param caminhoArquivo caminho do arquivo com o código fonte.
     */
    public AnalisadorLexico(String caminhoArquivo) {
        // Verifica se o arquivo possui a extensão da linguagem
        if(caminhoArquivo.contains(".gyh")){
            // Abre o arquivo com o código fonte e o que guardará os tokens
            codigo = new LeitorArquivos(caminhoArquivo);
            arqTokens = new EscritorArquivos(caminhoArquivo.replace(".gyh", "-Tokens.txt"));
            // Cria uma instancia de um AFD para analisar o código
            afd = new AFD();
            fluxoTokens = new ArrayList<>();
            mapaTokenLinha = new ArrayList<>();
            qtdeErros = 0;
        }else{
            // Notifica que o arquivo não apresenta a extensão da linguagem GYH
            System.err.println("File Error - Arquivo deve ter extensão .gyh");
        }// if-else
    }
    
    /**
     * Função que indica se o analisador léxico apresenta ou não um código fonte para analisar.
     * @return Retorna true caso o arquivo com o código fonte tenha sido aberto corretamente,
     * retornando false caso contrário.
     */
    public boolean temCodigo() {
        return codigo != null;
    }
    
    /**
     * Gera um Token para o lexema analisado, caso o mesmo apresente um padrão válido.
     * @param lexema o lexema analisado.
     * @return o token gerado para o lexema analisado.
     */
    private Token geraToken(String lexema) {
        // Obtem o padrão do lexema pelo AFD
        TipoToken padrao = afd.obtemPadrao();
        
        // Verifica se o padrão é válido
        if (padrao instanceof TipoToken) {
            // Cria uma nova instancia de token para o par padrão/lexema atual
            Token novoToken = new Token(padrao, lexema);
            fluxoTokens.add(padrao);
            mapaTokenLinha.add(codigo.getNumLinha());
            // Reseta o estado do AFD e o lexema armazenado para a análise do próximo lexema
            afd.resetaAFD();
            this.lexema = "";
            // Retorna o token gerado
            return novoToken;
        } else {
            return null;
        }
    }

    /**
     * Realiza a análise do códgio passado, controlando a lógica e dados
     * necessários para a análise.
     */
    public void analisaCodigo() {
        // Variavel auxiliar para armazenar uma linha do código fonte
        String novaLinha;
        // Enquanto tiverem linhas para serem analisadas
        while ((novaLinha = codigo.getLinha()) != null) {
            // Remove espaçamentos/tabulações irrelevantes
            novaLinha = novaLinha.trim();
            // Verifica se a linha não é um comentário
            if (!novaLinha.startsWith("#")) {
                // Analisa a linha
                analisaLinha(novaLinha.toCharArray());
            }
        }// while
        
        // Força o estado do AFD para indicar o final do arquivo
        afd.mudaEstadoAtual("EOF");
        // Gera o token que marca o final do arquivo
        arqTokens.concatenaString(geraToken("EOF").toString());
        // Fecha os arquivos
        arqTokens.fechaArquivo();
        codigo.fechaArquivo();
        // Indica Finalização
        System.out.println("\nArquivo de tokens gerado");
        System.err.println(qtdeErros + " - erro(s) léxico(s) encontrado(s)!");
    }
    
    /**
     * Analisa uma linha do código fonte, passada como um array de caracteres.
     * @param charLinha uma linha do código fonte como array de caracteres.
     */
    private void analisaLinha(char charLinha[]) {
    
        String estadoPassado;
        // Para cada caracter da linha
        for (int i = 0; i < charLinha.length; i++) {
            // Se o AFD chegou em um estado final
            if(verificaEstadoFinal()){
                // Salva o estado atual para a tomada de decisão
                estadoPassado = afd.verificaEstadoAtual();
                // Dependendo do estado
                switch (estadoPassado) {
                    case "Var":// Caso seja uma variável
                        
                        // Verifica se a variavel acabou
                        afd.verificaNovoCaracter(charLinha[i]);
                        if(verificaMudancaEstado(estadoPassado)){// Caso tenha acabado
                            // Força o estado do AFD para gerar o token da variável
                            afd.mudaEstadoAtual("Var");
                            // Salva o token da variável
                            arqTokens.concatenaString(geraToken(lexema).toString());
                            /* Mantém a análise no caracter atual, visto que o mesmo não faz parte
                            da variável */
                            i--;
                            // Caso contrário, adiciona o caracter ao lexema da variável
                        }else lexema = lexema + charLinha[i]; 
                        break;
                        
                    case "NumReal":// Caso seja um número real
                        
                        // Verifica se o número Real acabou
                        afd.verificaNovoCaracter(charLinha[i]);
                        if(verificaMudancaEstado(estadoPassado)){// Caso tenha acabado
                            // Força o estado do AFD para gerar o token do número real
                            afd.mudaEstadoAtual("NumReal");
                            // Salva o token do número real
                            arqTokens.concatenaString(geraToken(lexema.replace(",", ".")).toString());
                            /* Mantém a análise no caracter atual, visto que o mesmo não faz parte
                            do número real */
                            i--;
                            // Caso contrário, adiciona o caracter ao lexema do número real
                        }else lexema = lexema + charLinha[i];
                        break;
                        
                    case "NumInt":// Caso seja um número inteiro
                        
                        // Verifica se o numInteiro acabou
                        afd.verificaNovoCaracter(charLinha[i]);
                        if(verificaMudancaEstado(estadoPassado)){// Caso tenha acabado
                            if(afd.verificaEstadoAtual().equals("NumReal")){// Se mudou para um numReal
                                // Salva o caracter no lexema do reconhecido número real
                                lexema = lexema + charLinha[i];
                            } else{// Se não
                                
                                // Força o estado do AFD para gerar o token do número inteiro
                                afd.mudaEstadoAtual("NumInt");
                                // Salva o token do número real
                                arqTokens.concatenaString(geraToken(lexema).toString());
                                /* Mantém a análise no caracter atual, visto que o mesmo não faz parte
                            do número inteiro */
                                i--;
                            }
                            // Caso contrário, adiciona o caracter ao lexema do número inteiro
                        }else lexema = lexema + charLinha[i];
                        break;
                    
                    case "PcSe":// Caso seja uma variável
                        
                        // Verifica se a variavel acabou
                        afd.verificaNovoCaracter(charLinha[i]);
                        if(!verificaMudancaEstado(estadoPassado)){// Caso tenha acabado
                            // Salva o token da variável
                            arqTokens.concatenaString(geraToken(lexema).toString());
                            /* Mantém a análise no caracter atual, visto que o mesmo não faz parte
                            do PcSe */
                            i--;
                            // Caso contrário, adiciona o caracter ao lexema da variável
                        }else lexema = lexema + charLinha[i]; 
                        break;
                        
                    default: // Caso seja outro estado final genérico
                        // Salva o token
                        arqTokens.concatenaString(geraToken(lexema).toString());
                        // Mantém a análise no caracter atual, visto que o mesmo não foi analisado
                        i--;
                        break;
                }// switch
                
            }else if(Character.isSpaceChar(charLinha[i])){// Fim de um token ou espaçamento/tabulação
                
                if(!afd.verificaEstadoAtual().equals("NULL")){ // Se não é espaçamento/tabulação
                    if(afd.verificaEstadoAtual().equals("CAD_1")){// Se é uma cadeia de caracteres
                        // Salva o caracter no lexema da cadeia
                        lexema = lexema + charLinha[i];
                    }else if(afd.verificaEstadoAtual().equals("ErroLEXICO") || !verificaEstadoFinal()){
                        // Se for um erro léxico, emite uma Exeção e informa a linha em que o erro ocorreu
                        System.err.println("Linha " + codigo.getNumLinha()+ " - Lexema: " + lexema);
                        arqTokens.concatenaString(geraToken(lexema).toString());
                        qtdeErros++;
                    }else{// Caso contrário é o fim de um token, então gera o token para o lexema atual
                        arqTokens.concatenaString(geraToken(lexema).toString());
                    }// if-else
                }// if-else
                
            }else if(afd.verificaEstadoAtual().equals("NULL")){ // Inicio de um token
                // Adiciona o caracter no lexema
                lexema = lexema + charLinha[i];
                // Analisa o caracter inicial do novo token/lexema
                if(i+1 < charLinha.length){// Controla para não estourar o index do array
                    if(afd.verificaCaracterInicial(charLinha[i], charLinha[i+1])){
                        lexema = lexema + charLinha[i+1];
                        i++;
                    }
                }else{// Caso esteja no fim, considera o próximo caracter como um espaçamento
                    if(afd.verificaCaracterInicial(charLinha[i], ' ')){
                        lexema = lexema + charLinha[i+1];
                        i++;
                    }
                }// if-else
                
            } else{// Caso esteja no meio da análise de um token/lexema
                if(afd.verificaEstadoAtual().equals("ErroLEXICO")){
                    // Se for um erro léxico, emite uma Exeção e informa a linha em que o erro ocorreu
                    System.err.println("Linha " + codigo.getNumLinha()+ " - Lexema: " + lexema);
                    arqTokens.concatenaString(geraToken(lexema).toString());
                    qtdeErros++;
                }else{// Caso não seja um erro 
                    // Realiza a transição de estado, analisando o caracter atual
                    afd.verificaNovoCaracter(charLinha[i]);
                    // Salva o caracter analisado 
                    lexema = lexema + charLinha[i];
                }//if-else
                
            }// if-else
            
        }//for
        
        // Se terminou com um erro léxico
        if(afd.verificaEstadoAtual().equals("ErroLEXICO")){
            System.err.println("Linha " + codigo.getNumLinha() + " - Lexema: " + lexema);
            arqTokens.concatenaString(geraToken(lexema).toString());
            qtdeErros++;
        }else if(verificaEstadoFinal()){// Verifica se a linha terminou em um estado final
            arqTokens.concatenaString(geraToken(lexema).toString());
            // Caso não seja final, verifica se o último token foi gerado
        }else if(!afd.verificaEstadoAtual().equals("NULL")){
            // Caso não tenha sido gerado, temos um erro léxico
            System.err.println("Erro Léxico - Parametro interrompido por quebra de linha");
            System.err.println("Linha " + codigo.getNumLinha() + " - Lexema: " + lexema);
            arqTokens.concatenaString(geraToken(lexema).toString());
            qtdeErros++;
        }// if-else

    }
    
    /**
     * Verifica se houve uma mudança no estado do AFD, considerando o estado de referência.
     * @param estadoPassado estado do AFD anterior ao atual usado de referência.
     * @return Retorna true caso o estado do AFD tenha mudado, caso contrário retorna false.
     */
    private boolean verificaMudancaEstado(String estadoPassado){
        return !estadoPassado.equals(afd.verificaEstadoAtual());
    }
    
    /**
     * Verifica se o estado atual do AFD é final ou não,ou seja, se o mesmo pode ser convertido 
     * para um Tipo de token válido.
     * @return Retorna true se o estado atual do AFD é final, caso contrário retorna false.
     */
    private boolean verificaEstadoFinal(){
        return afd.obtemPadrao() != TipoToken.ERROR;
    }
    
    /**
     * Função que permite obter o fluxo de tokens gerado pelo analisador léxico, permitindo que o
     * mesmo converse com o analisador sintático.
     * @return Retorna o fluxo de tokens gerado pelo analisador até o momento de sua chamada.
     */
    public ArrayList<TipoToken> getFluxoTokens(){
        return fluxoTokens;
    }
    
    /**
     * Função que permite obter o mapeamento da linha que cada token do fluxo pertence,
     * a fim de melhorar o feedback do analisador sintático para o usuário.
     * @return Retorna o array com a relação de qual linha cada token pertence,
     *          correspondendo token e linha no mesmo índice dos arrays.
     */
    public ArrayList<Integer> getMapaTokenLinha(){
        return mapaTokenLinha;
    }
}
