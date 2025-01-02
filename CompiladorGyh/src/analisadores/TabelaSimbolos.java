
package analisadores;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe representativa da tabela de símbolos, utilizada para armazenar informaçÕes
 * das variáveis e símbolos presentes no código fonte, a fim de preservar informações
 * sobre o contexto do programa.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public class TabelaSimbolos {
    
    /**
     * Tabela Hash que irá armazenas as informações, tendo como chave uma string
     * e como dados a classe representativa dos símbolos.
     */
    private HashMap<String,Simbolo> tabela;
    
    /**
     * COnstrutor da classe, que inicia a tabela de símbolos vazia.
     */
    public TabelaSimbolos() {
        this.tabela = new HashMap<>();
    }
    
    /**
     * Método para alterar a tabela de símbolo atual, substituindo-a por outra
     * passada ao método.
     * @param tabela HashMap no mesmo formato da tabela.
     */
    public void setTabela(HashMap<String, Simbolo> tabela) {
        this.tabela = tabela;
    }
    
    /**
     * Método para obter uma referência para a tabela de símbolos e suas informações.
     * @return uma referência para a tabela de símbolos da instancia atual.
     */
    public HashMap<String, Simbolo> getTabela() {
        return tabela;
    }
    
    /**
     * Método que adiciona um símbolo à tabela de símbolos, utilizando o nome do
     * mesmo como chave.
     * @param simbolo Símbolo e suas informações presente no programa fonte.
     */
    public void add(Simbolo simbolo){
        this.tabela.put(simbolo.getNome(), simbolo);
    }
    
    /** 
     * Método que verifica se existe um símbolo já armazenado na tabela, a partir
     * da chave/nome do mesmo.
     * @param key Chave a ser procurada na tabela de símbolos.
     * @return Verdadeiro caso um símbolo com a chave já esteja armazenado, e
     * Falso caso contrário.
     */
    public boolean contemChave(String key){
        return this.tabela.containsKey(key);
    }
    
    /**
     * Método utilizado para obter uma referência para as informações de um símbolo 
     * armazenado na tabela de símbolos.
     * @param key Chave a ser procurada na tabela de símbolos.
     * @return uma referência para o símbolo desejado.
     */
    public Simbolo getSimbolo(String key){
        return this.tabela.get(key);
    }
    
    /**
     * Método que converte a tabela de símbolos em um array de símbolos.
     * @return Retorna um arrayList de símbolos, contendo todos os símbolos da tabela.
     */
    public ArrayList<Simbolo> getAll(){
        ArrayList<Simbolo> list = new ArrayList<>(tabela.values());
        return list;
    }
    
}
