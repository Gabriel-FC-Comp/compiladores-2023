
package comandos;

/**
 * Classe abstrata que descreve o formato padrão de uma classe que represente os
 * comandos suportados na linguagem Gyh.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public abstract class Comando {
    
    /**
     * Método abstrato que deve ser implementado, com o intuito de converter
     * o código em linguagem gyh para a linguagem C.
     * @return Uma string com o código equivalente em linguagem C.
     */
    public abstract String generateCode();
}
