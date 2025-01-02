/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadores;

/**
 * Classe que representa um símbolo ou variável presente no código fonte.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public class Simbolo {
    
    /**
     * Nome associado ao símbolo.
     */
    private String nome;
    /**
     * Tipo do símbolo.
     */
    private int tipo; 
    
    /**
     * Tipo de símbolo, números Reais.
     */
    public static final int REAL = 0;
    /**
     * Tipo de símbolo, números inteiros.
     */
    public static final int INT = 1;

    /**
     * Construtor de uma instancia da classe símbolo.
     * @param nome Nome do símbolo que está sendo instanciado.
     * @param tipo Tipo do símbolo que está sendo instanciado.
     */
    public Simbolo(String nome, String tipo) {
        
        this.nome = nome;
        
        // Verifica o tipo do símbolo que está sendo criado.
        if(tipo.equals("INT"))
            setTipo(INT);
        else if(tipo.equals("REAL"))
            setTipo(REAL);
        else
            System.exit(1);
    }
    
    /**
     * Método para obter uma String com o nome do símbolo.
     * @return Uma string contendo o nome dos símbolo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para alterar o nome do símbolo.
     * @param nome Novo nome do símbolo.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Método para obter o tipo do símbolo.
     * @return uma referência para o tipo do símbolo.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Método para alterar o típo do símbolo.
     * @param tipo O novo tipo do símbolo.
     */
    public final void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Método para gerar o código em linguagem C da declaração de um símbolo.
     * @return Uma string contendo o código em C da declaração do símbolo.
     */
    public String generateCode(){
        String str;
        
        if(this.tipo == REAL){
            str = "\n\tdouble " + this.nome + ";";
        }else{
            str = "\n\tint " + this.nome + ";";
        }
        
        return str;
    }
    
    @Override
    public String toString() {
        return "[nome= " + this.nome + "\t tipo=" + this.tipo + "]";
    }
    
}
