
package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import analisadores.*;
import comandos.*;

/**
 * Classe responsável por controlar a lógica da geração de código.
 * @author Gabriel Finger Conte.
 * @author João Vitor Garcia Carvalho.
 */
public class GyhProgram {
 
    /**
     * Tabela de símbolos, contendo as variáveis declaradas no programa.
     */
    private TabelaSimbolos varTabela;
    /**
     * Lista dos comandos descritos no código fonte, na ordem em que aparecem
     * no código.
     */
    private ArrayList<Comando> listCmd;

    /**
     * Método para obter uma referência para a tabela de símbolos.
     * @return Uma cópia da tabela de símbolos.
     */
    public TabelaSimbolos getVarTabela() {
        return varTabela;
    }

    /**
     * Método para trocar a tabela de símbolos por uma nova.
     * @param varTabela Referência para a nova tabela de símbolos.
     */
    public void setVarTabela(TabelaSimbolos varTabela) {
        this.varTabela = varTabela;
    }
    
    /**
     * Método para trocar a lista de comandos por uma nova.
     * @param listCmd Referência para a nova lista de comandos.
     */
    public void setListCmd(ArrayList<Comando> listCmd) {
        this.listCmd = listCmd;
    }
    
    /**
     * Método para gerar o código objeto em linguagem C, concluindo a transpilação.
     */
    public void generateTarget(){
        // Declaração do StringBuilder para construção da string que descreverá o programa.
        StringBuilder str = new StringBuilder();
        
        // Declaração de bibliotecas padrões suportadas
        str.append("#include<stdio.h>\n");
        str.append("#include<stdlib.h>\n");
        str.append("#include<stdbool.h>\n");
        str.append("#include<string.h>\n");
        str.append("\n\n");
        
        // Declaração da main
        str.append("int main(void){\n");
        
        // Declaração das variáveis presentes no código-fonte.
        for(Simbolo symbol : varTabela.getAll()){
            str.append(symbol.generateCode());
        }
        str.append("\n");
        
        // Escrita dos códigos presentes na lista de comandos.
        for(Comando cmd : listCmd){
            str.append(cmd.generateCode());
            str.append("\n");
        }
        
        // Fechando a main
        str.append("\treturn 0;\n");
        str.append("}\n");
        
        // Tentativa de criar o programa-objeto
        try {
            // Cria o programa-objeto com extensão .c
            FileWriter file = new FileWriter(new File("codigo.c"));
            // Escreve o programa no arquivo gerado
            file.write(str.toString());
            // Salva o arquivo fonte, fechando sua instancia.
            file.close();
        } catch (IOException ex) {
            System.err.println("Erro na criação do arquivo objeto");
            Logger.getLogger(GyhProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
