
package comandos;

/**
 * Classe que contém os métodos associados a conversão dos comandos de atribuição
 * para a linguagem C.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public class ComandoAtribuicao extends Comando{
    
    /**
     * Nome da variável que será atribuido o valor.
     */
    private final String id;
    /**
     * Expressão aritmética que será atribuida a variável.
     */
    private final String expressao;

    /**
     * Método que instancia um objeto que representa um comando de atribuição,
     * conhecendo a variável e o valor que deseja-se atribuir à mesma.
     * @param id Nome da variável que se deseja atribuir o valor.
     * @param valor Expressão aritmética a ser atribuída à variável.
     */
    public ComandoAtribuicao(String id, String valor) {
        this.id = id;
        this.expressao = valor;
    }
    
    /**
     * Gera o código equivalente ao comando na linguagem C.
     * @return String contendo o código equivalente em linguagem C.
     */
    @Override
    public String generateCode() {
        return "\n\t" + this.id + " = " + this.expressao + ";";
    }
    
}
