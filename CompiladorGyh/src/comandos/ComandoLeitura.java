
package comandos;

import analisadores.Simbolo;

/**
 * Classe que contém os métodos associados a conversão dos comandos de leitura
 * para a linguagem C.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public class ComandoLeitura extends Comando{

    /**
     * Nome da variável que armazenará o valor informado pelo usuário.
     */
    private final String id;
    /**
     * Tipo da variável que será usada para leitura,indicando o tipo de dado esperado.
     */
    private int tipo = -1;

    /**
     * Método que instancia um objeto que representa um comando de leitura em linguagem gyh.
     * @param id O nome da variável que armazenará o valor informado pelo usuário.
     * @param tipo O tipo da variável que será usada para leitura,indicando o tipo de dado esperado.
     */
    public ComandoLeitura(String id,int tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    
    /**
     * Gera o código equivalente ao comando na linguagem C.
     * @return String contendo o código equivalente em linguagem C.
     */
    @Override
    public String generateCode() {
        String str = "";
        if(tipo == Simbolo.INT){
            str = "\n\tscanf(\"%d\",&" + this.id  + ");";
        }else if(tipo == Simbolo.REAL){
            str = "\n\tscanf(\"%f\",&" + this.id  + ");";
        }else{
            System.err.println("Erro Semântico - Tentativa de atribuição para símbolo de tipo não suportado!");
        }
        
        return str;
    }
    
    
}
