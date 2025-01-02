
package comandos;

/**
 * Classe que contém os métodos associados a conversão dos comandos condicionais
 * para a linguagem C.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public class ComandoCondicao extends Comando{
    
    /**
     * Condição a ser verificada para determinar o curso do programa.
     */
    private final String condicao;
    /**
     * Comando a ser executado caso a condição seja verdadeira.
     */
    private final Comando cmdTrue;
    /**
     * Comando a ser executado caso a condição seja falsa.
     */
    private final Comando cmdFalse;

    /**
     * Método que instancia um objeto que representa um comando condicional em linguagem gyh.
     * @param condicao A condição a ser verificada para determinar o curso do programa.
     * @param cmdTrue O comando a ser executado caso a condição seja verdadeira.
     * @param cmdFalse O comando a ser executado caso a condição seja falsa.
     */
    public ComandoCondicao(String condicao, Comando cmdTrue, Comando cmdFalse) {
        this.condicao = condicao;
        this.cmdTrue = cmdTrue;
        this.cmdFalse = cmdFalse;
    }

    /**
     * Gera o código equivalente ao comando na linguagem C.
     * @return String contendo o código equivalente em linguagem C.
     */
    @Override
    public String generateCode() {
        String str = "\n\tif("+this.condicao+") ";
        
        if(cmdTrue != null){
            str += cmdTrue.generateCode();
        }// fim if
        
        if(cmdFalse != null){
            str += "\n\telse ";
            str += cmdFalse.generateCode();
        }// fim if
        
        return str;
    }

}
