
package comandos;

import java.util.ArrayList;

/**
 * Classe que contém os métodos associados a conversão dos comandos para declaração
 * escopos de subAlgoritmos para a linguagem C.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public class ComandoSubAlgoritmo extends Comando{
    
    /**
     * A lista de comandos decarados dentro do escopo do subalgoritmo.
     */
    private ArrayList<Comando> listComandos;

    /**
     * Método que instancia um objeto que representa um subAlgoritmo em linguagem gyh.
     * @param listComandos A lista de comandos decarados dentro do escopo do subalgoritmo.
     */
    public ComandoSubAlgoritmo(ArrayList<Comando> listComandos) {
        this.listComandos = listComandos;
    }

    /**
     * Gera o código equivalente ao comando na linguagem C.
     * @return String contendo o código equivalente em linguagem C.
     */
    @Override
    public String generateCode() {
        String str = "{";
        
        if(listComandos != null){
            for(Comando cmd : listComandos){
                str += cmd.generateCode();
            }
        }
        str += "\n\t}";
        
        return str;
    }
    
}
