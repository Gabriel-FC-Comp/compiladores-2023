
package comandos;

/**
 * Classe que contém os métodos associados a conversão dos comandos de escrita
 * para a linguagem C.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public class ComandoEscrita extends Comando{

    /**
     * Nome da variável que será escrita no console.
     */
    private final String id;
    /**
     * Tipo da variável que será usada para escrita.
     */
    private int tipo = -1;
    
    /**
     * Método que instancia um objeto que representa um comando de escrita em linguagem gyh.
     * @param id O nome da variável que será escrita no console.
     * @param tipo O tipo da variável que será usada para escrita.
     */
    public ComandoEscrita(String id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    
    /**
     * Gera o código equivalente ao comando na linguagem C.
     * @return String contendo o código equivalente em linguagem C.
     */
    @Override
    public String generateCode() {
        String str;
                
        if(this.tipo == 0){
            str = "\n\tprintf(\"%f.8\\n\"," + this.id  + ");";
        }else if(this.tipo == 1){
            str = "\n\tprintf(\"%d\\n\"," + this.id  + ");";
        }else{
            str = "\n\tprintf(" + this.id  + ");";
        }
                
        return str;
    }
    
    
}
