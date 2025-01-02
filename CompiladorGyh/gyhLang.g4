grammar gyhLang;

// Cabeçalho que será adicionado às classes geradas
@header {
    // Pacote onde serão salvos os analisadores
    package analisadores;
    // Bibliotecas necessárias
    import java.util.ArrayList;
    import comandos.*;
    import main.GyhProgram;
}// header   

// Variáveis e funções necessárias.
@members{
    
    /**
    *   Tabela de símbolos para manter as variáveis declaradas no programa fonte.
    */
    private TabelaSimbolos varTabela = new TabelaSimbolos();
    /**
    *   Strings auxiliares para salvar o nome das variáveis, expressões aritméticas
    *   e booleanas, respectivamente.
    */
    private String varAtrib,varExp,varCond;
    /**
    *   Array de inteiros usados para auxiliar na identificação dos comandos pertencentes
    *   a diferentes escopos de subalgoritmos.
    */
    private ArrayList<Integer> indices = new ArrayList<>();
    /**
    *   Array de comandos usados para concatenar os comandos pertencente a um escopo de
    *   subalgoritmo.
    */
    private ArrayList<Comando> listSub;
    /**
    *   Instancias de comando auxiliares, para armazenar os comandos a serem executados
    *   em caminhos condicionais e de repetição.
    */
    private Comando cmdTrue, cmdFalse;
    /**
    * Variavel para geração de código.
    */
    private GyhProgram program = new GyhProgram();
    /**
    * Array de comandos que manterá os comandos na ordem em que aparecem no programa fonte.  
    */
    private ArrayList<Comando> listCmd = new ArrayList<>();
    /**
    * Variavel para verificação semântica, se o tipo de dado que uma variável está recebendo é correto.
    */
    private int eVarInt = -1;
    //============================================//
    
    // Delcação de funções
    
    /** 
    *   Método utilizado para adicionar um novo símbolo à tabela de símbolos.
    *   @param nome O nome da variável.
    *   @param tipo O tipo da variável.
    */
    public void addTabela(String nome, String tipo){
        if(varTabela.contemChave(nome)){
            System.err.println("Erro Semântico - Variável já declarada com esse nome: " + nome);
        }else{
            varTabela.add(new Simbolo(nome,tipo));
        }// if-else
    }// addTabela

}// members

// Declaração das regras sintáticas e o tratamento semântico aclopado, segundo as regras gramaticais
// da linguagem gyh.

programa: DELIM PCDEC (declaracao)+ DELIM PCPROG (comando)+{
    // Como terminamos de analisar o código, vamos gerar o código
    program.setVarTabela(varTabela);
    program.setListCmd(listCmd);
    program.generateTarget();
}; 

declaracao: VAR DELIM tipoVar { 
    addTabela(_input.LT(-3).getText(), _input.LT(-1).getText());
};
tipoVar: PCINT|PCREAL;

expressaoAritmetica: termoAritmetico sentencaAritmetica;
sentencaAritmetica: ('+' {varExp += "+";}|'-' {varExp += "-";}) termoAritmetico proposicaoAritmetica | ;
termoAritmetico: fatorAritmetico proposicaoAritmetica;
proposicaoAritmetica: ('*' {varExp += "*";}|'/' {varExp += "/";})fatorAritmetico proposicaoAritmetica | ;
fatorAritmetico: NUMINT {varExp += _input.LT(-1).getText();} 
                | NUMREAL {
    String numReal = _input.LT(-1).getText();
    if(numReal.indexOf(".")+9 < numReal.length()){
        System.err.println("\nErro Semântico - Número real com mais de 8 casas decimais.\n" +
                            "O excedente de casas decimais foi desconsiderado!\n"+
                            "Num: " + numReal + "\n");
        numReal = numReal.substring(0,numReal.indexOf(".")+9);
    }// if
    
    if(eVarInt == 1) System.err.println("\nErro Semântico - Tentativa de atribuição de dado real " + 
                                numReal + " para variável inteira " + varAtrib + ".\n"+
                                "É possível que o resultado seja diferente do esperado!\n");
    varExp += numReal;
} 
                | VAR {
    if(varTabela.contemChave(_input.LT(-1).getText())){
        varExp += _input.LT(-1).getText();
        if(varTabela.getSimbolo(_input.LT(-1).getText()).getTipo() == Simbolo.INT && eVarInt == 0)
            System.err.println("\nErro Semântico - Tentativa de atribuição de dado inteiro de " + 
                                _input.LT(-1).getText() + " para variável real " + varAtrib + ".\n"+
                                "O resultado pode ser diferente do esperado!\n");
        else if(varTabela.getSimbolo(_input.LT(-1).getText()).getTipo() == Simbolo.REAL && eVarInt == 1)
            System.err.println("\nErro Semântico - Tentativa de atribuição de dado real de " + 
                                _input.LT(-1).getText() + " para variável inteira " + varAtrib + ".\n"+
                                "O resultado pode ser diferente do esperado!\n");
    }else{
        System.err.println("Erro Semântico - Variável \"" + _input.LT(-1).getText()  + "\" não declarada!\n");
        System.exit(1);
    }
    
} 
                | '(' {varExp += "(";} expressaoAritmetica ')' {varExp += ")";};

expressaoRelacional: termoRelacional ((OPBOOLE {varExp += " E ";}|OPBOOLOU {varExp += " OU ";}) termoRelacional)*;
termoRelacional: expressaoAritmetica (OPRELIGUAL {varExp += " == ";} 
                | OPRELDIF {varExp += " != ";} | OPRELMAIORIGUAL {varExp += " >= ";}
                |OPRELMENORIGUAL{varExp += " <= ";}|OPRELMAIOR {varExp += " > ";}
                |OPRELMENOR {varExp += " < ";}) expressaoAritmetica 
                | '(' {varExp += "(";} expressaoRelacional ')' {varExp += ")";};

comando: comandoAtribuicao | comandoEntrada | comandoSaida | comandoCondicao | comandoRepeticao | subAlgoritmo;
comandoAtribuicao: VAR {
    if( varTabela.contemChave(_input.LT(-1).getText()) ){

        varAtrib = _input.LT(-1).getText();
        if(varTabela.getSimbolo(varAtrib).getTipo() == Simbolo.INT) eVarInt = 1;
        else eVarInt = 0;

    }else {
        System.err.println("Erro semântico - Variável " + _input.LT(-1).getText() + " não existe para atribuição!\n");
        System.exit(1);
    }
    varExp = "";
} ATRIB expressaoAritmetica {
    eVarInt = -1;
    if(!varAtrib.equals("")){
        listCmd.add(new ComandoAtribuicao(varAtrib,varExp));
    }
};

comandoEntrada: PCLER VAR {
    if(varTabela.contemChave(_input.LT(-1).getText())){
        listCmd.add(new ComandoLeitura(_input.LT(-1).getText(),varTabela.getSimbolo(_input.LT(-1).getText()).getTipo()));
    } else{
        System.err.println("Erro semântico - Variável " + _input.LT(-1).getText() + " não existe para salvar os dados a serem lidos.");
    }
};

comandoSaida: PCIMPRIMIR tipoSaida;
tipoSaida: VAR {
    if(varTabela.contemChave(_input.LT(-1).getText())){
        Simbolo simbAux = varTabela.getSimbolo(_input.LT(-1).getText());
        listCmd.add(new ComandoEscrita(simbAux.getNome(),simbAux.getTipo()));
    }else{
        System.err.println("Erro Semantico - Variavel" + _input.LT(-1).getText() + " não declarada!");
    }
} | CADEIA {
    listCmd.add(new ComandoEscrita(_input.LT(-1).getText(),-1));
};

comandoCondicao: PCSE {varExp = ""; int indice = -1;} expressaoRelacional {varCond = varExp;} PCENTAO {
    indices.add(listCmd.size());
    cmdFalse = null;
} comando {
    indice = indices.get(indices.size()-1);
    cmdTrue = listCmd.remove(indice);
    indices.remove(indices.size()-1);
} (PCSENAO {indices.add(listCmd.size());} comando {
    indice = indices.get(indices.size()-1);
    cmdFalse = listCmd.remove(indice);
    indices.remove(indices.size()-1);
})? {
    listCmd.add(new ComandoCondicao(varCond,cmdTrue,cmdFalse));
};

comandoRepeticao: PCENQTO {
    varExp = ""; 
    int indice = -1;
} expressaoRelacional {
    varCond = varExp;
    indices.add(listCmd.size());
} comando {
    indice = indices.get(indices.size()-1);
    cmdTrue = listCmd.remove(indice);
    indices.remove(indices.size()-1);
    listCmd.add(new ComandoRepeticao(varCond,cmdTrue));
};

subAlgoritmo: PCINI {indices.add(listCmd.size()); int indice = -1;} (comando)+ PCFIM {
    listSub = new ArrayList<>();
    indice = indices.get(indices.size()-1);
    while(indice < listCmd.size()){
        listSub.add(listCmd.remove(indice));
    }
    indices.remove(indices.size()-1);
    listCmd.add(new ComandoSubAlgoritmo(listSub));
};

// Declaração dos padrões de tokens para Análise Léxica

WS: (' '|'\n'|'\t'|'\r') -> skip;
COMENTARIO: '#'~[\n\r]* -> skip;

PCDEC: 'DEC';
PCPROG: 'PROG';
PCINT: 'INT';
PCLER: 'LER';
PCREAL: 'REAL';
PCIMPRIMIR: 'IMPRIMIR';
PCSE: 'SE';
PCENTAO: 'ENTAO';
PCENQTO: 'ENQTO';
PCINI: 'INI';
PCFIM: 'FIM';
PCSENAO: 'SENAO';

CADEIA: '"' ([a-z]|[A-Z]|[0-9]|[\t]|[ ]|':'|';'|'='|'-'|'+'|'/'|'*')+ '"';
VAR: [a-z]([a-z]|[A-Z]|[0-9])*;

OPARITSOMA: '+';
OPARITSUB: '-';
OPARITMULT: '*';
OPARITDIV: '/';

OPBOOLE: 'E';
OPBOOLOU: 'OU';

OPRELIGUAL: '==';
OPRELDIF: '!=';

OPRELMAIORIGUAL: '>=';
OPRELMENORIGUAL: '<=';

OPRELMAIOR: '>';
OPRELMENOR: '<';

ATRIB: ':=';
DELIM: ':';

ABREPAR: '(';
FECHAPAR: ')';

NUMREAL: ([0-9])+'.'([0-9])+;
NUMINT: ([0-9])+;
