
package comandos;

/**
 * Classe que contém os métodos associados a conversão dos comandos de laços de
 * repetição para a linguagem C.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public class ComandoRepeticao extends Comando{
    
    /**
     * Condição a ser verificada para manutenção do laço de repetição.
     */
    private final String condicao;
    /**
     * Comando a ser executado enquanto a condição for verdadeira.
     */
    private final Comando comando;

    /**
     * Método que instancia um objeto que representa um comando de repetição em linguagem gyh.
     * @param condicao A condição de repetição do laço/comando.
     * @param cmd O comando a ser repetido.
     */
    public ComandoRepeticao(String condicao, Comando cmd) {
        this.condicao = condicao;
        this.comando = cmd;
    }

    /**
     * Gera o código equivalente ao comando na linguagem C.
     * @return String contendo o código equivalente em linguagem C.
     */
    @Override
    public String generateCode() {
        String str = "\n\twhile(" + this.condicao + ") ";
        str += comando.generateCode();
        return str;
    }
    
}
