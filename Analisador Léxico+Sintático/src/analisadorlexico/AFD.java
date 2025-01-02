
package analisadorlexico;

/**
 * Representa um Autômato Finito Determinístico, configurado para a análise léxica
 * da linguagem GYH.
 * @author Gabriel Finger Conte
 * @author João Vitor Garcia Carvalho
 */
public class AFD{
   
    /**
     * O estado atual do autômato.
     */
    private String estadoAtual;

    /**
     * Cria um novo autômato no seu estado inicial, "NULL".
     */
    public AFD() {
        estadoAtual = "NULL";
    }

    /**
     * Verifica o estado atual do automato.
     * @return Retorna uma string que descreve o atual estado do autômato.
     */
    public String verificaEstadoAtual() {
        return estadoAtual;
    }

    /**
     * Reseta o autômato, colocando-o novamente em seu estado inicial, "NULL".
     */
    public void resetaAFD(){
        estadoAtual = "NULL";
    }
    
    /**
     * Converte o estado atual do autômato em um tipo de Token da linguagem GYH.
     * @return retorna o tipo de token equivalente ao estado atual do autômato.
     */
    public TipoToken obtemPadrao() {
        switch (estadoAtual) {
            case "PcDec":
                return TipoToken.PcDec;
            case "PcProg":
                return TipoToken.PcProg;
            case "PcInt":
                return TipoToken.PcInt;
            case "PcReal":
                return TipoToken.PcReal;
            case "PcLer":
                return TipoToken.PcLer;
            case "PcImprimir":
                return TipoToken.PcImprimir;
            case "PcSe":
                return TipoToken.PcSe;
            case "PcSenao":
                return TipoToken.PcSenao;
            case "PcEntao":
                return TipoToken.PcEntao;
            case "PcEnqto":
                return TipoToken.PcEnqto;
            case "PcIni":
                return TipoToken.PcIni;
            case "PcFim":
                return TipoToken.PcFim;
            case "OpAritMult":
                return TipoToken.OpAritMult;
            case "OpAritDiv":
                return TipoToken.OpAritDiv;
            case "OpAritSoma":
                return TipoToken.OpAritSoma;
            case "OpAritSub":
                return TipoToken.OpAritSub;
            case "OpRelMaior":
                return TipoToken.OpRelMaior;
            case "OpRelMenor":
                return TipoToken.OpRelMenor;
            case "OpRelMaiorIgual":
                return TipoToken.OpRelMaiorIgual;
            case "OpRelMenorIgual":
                return TipoToken.OpRelMenorIgual;
            case "OpRelIgual":
                return TipoToken.OpRelIgual;
            case "OpRelDif":
                return TipoToken.OpRelDif;
            case "OpBoolE":
                return TipoToken.OpBoolE;
            case "OpBoolOu":
                return TipoToken.OpBoolOu;
            case "Delim":
                return TipoToken.Delim;
            case "Atrib":
                return TipoToken.Atrib;
            case "AbrePar":
                return TipoToken.AbrePar;
            case "FechaPar":
                return TipoToken.FechaPar;
            case "Var":
                return TipoToken.Var;
            case "NumInt":
                return TipoToken.NumInt;
            case "NumReal":
                return TipoToken.NumReal;
            case "Cadeia":
                return TipoToken.Cadeia;
            case "EOF":
                return TipoToken.EOF;
            default:
                return TipoToken.ERROR;
        }
    }

    /**
     * Muda o estado atual do autômato para um novo estado passado.
     * @param novoEstado O novo estado que o autômato passará a ter.
     */
    public void mudaEstadoAtual(String novoEstado) {
        this.estadoAtual = novoEstado;
    }

    /**
     * Verifica o caracter inicial de um token/lexema, sabendo o caracter seguinte
     * a fim de controlar a transição de estados inicial do AFD.
     * @param caractere O caracter atual que está sendo analisado pelo analisador.
     * @param proxCaracter O próximo caracter que seria analisado na sequência pelo analisador.
     * @return Retorna true caso o próximo caracter deva ser incluso no lexema, caso contrário
     * retorna false, indicando que o próximo caracter não deve ser incluso no lexema.
     */
    public boolean verificaCaracterInicial(char caractere, char proxCaracter) {
        // Verifica como o novo token/lexema começa, segundo as regras da linguagem GYH
        if (Character.isLowerCase(caractere)) {// Caso seja minúsculo
            // Temos uma variável
            mudaEstadoAtual("Var");
            //Caso o próximo caracter seja uma letra ou dígito, faz parte da variável e deve ser inlcuso no lexema
            if(Character.isLetterOrDigit(proxCaracter)) return true;
            
        } else if (Character.isUpperCase(caractere)) {// Caso seja maiúsculo
            // Verifica qual palavra-chave / operador é
            switch (caractere) {
                case 'D':
                    mudaEstadoAtual("PC_D1");
                    break;
                case 'E':
                    if(Character.isSpaceChar(proxCaracter)){
                        mudaEstadoAtual("OpBoolE");
                    }else{
                        mudaEstadoAtual("PC_E1");
                    }
                    break;
                case 'F':
                    mudaEstadoAtual("PC_F1");
                    break;
                case 'I':
                    mudaEstadoAtual("PC_I1");
                    break;
                case 'L':
                    mudaEstadoAtual("PC_L1");
                    break;
                case 'O':
                    if(proxCaracter == 'U'){
                        mudaEstadoAtual("OpBoolOu");
                        return true;
                    }else erroLexico("Operador OU escrito incorretamente");
                    break;
                case 'P':
                    mudaEstadoAtual("PC_P1");
                    break;
                case 'R':
                    mudaEstadoAtual("PC_R1");
                    break;
                case 'S':
                    if(proxCaracter == 'E'){
                        mudaEstadoAtual("PcSe");
                        return true;
                    }else erroLexico("Palavra reservada SE/SENAO escrita incorretamente");
                    break;
                default:
                    erroLexico("Não existe operador começando com " + caractere);
            }// switch
            
        } else if (Character.isDigit(caractere)) {// Caso seja um número
            // Inicialmente considera o mesmo como um inteiro
            mudaEstadoAtual("NumInt");
            // Caso o próximo caracter também seja um número, é parte do lexema
            if(Character.isDigit(proxCaracter)){
                return true;
                // Caso seja um ponto ou vírgula, trata-se de um número real
            }else if(proxCaracter == '.' || proxCaracter == ','){
                mudaEstadoAtual("NumReal");
                // A virgula/ponto é parte do lexema do número real
                return true;
            }// if-else
            
        } else {// Caso seja um símbolo
            // Verifica o típo de símbolo que é
            switch (caractere) {
                case '"':
                    mudaEstadoAtual("CAD_1");
                    break;
                case ':':
                    if(proxCaracter == '='){
                        mudaEstadoAtual("Atrib");
                        return true;
                    }else mudaEstadoAtual("Delim");
                    break;
                case '>':
                    if(proxCaracter == '='){
                        mudaEstadoAtual("OpRelMaiorIgual");
                        return true;
                    }else mudaEstadoAtual("OpRelMaior");
                    break;
                case '<':
                    if(proxCaracter == '='){
                        mudaEstadoAtual("OpRelMenorIgual");
                        return true;
                    }else mudaEstadoAtual("OpRelMenor");
                    break;
                case '!':
                    if(proxCaracter == '='){
                        mudaEstadoAtual("OpRelDif");
                        return true;
                    }else erroLexico("Operador de Diferença (!=) escrito incorretamente");
                    break;
                case '=':
                    if(proxCaracter == '='){
                        mudaEstadoAtual("OpRelIgual");
                        return true;
                    }else erroLexico("Operador de Igualdade (==) escrito incorretamente");
                    break;
                case '(':
                    mudaEstadoAtual("AbrePar");
                    break;
                case ')':
                    mudaEstadoAtual("FechaPar");
                    break;
                case '+':
                    mudaEstadoAtual("OpAritSoma");
                    break;
                case '-':
                    mudaEstadoAtual("OpAritSub");
                    break;
                case '/':
                    mudaEstadoAtual("OpAritDiv");
                    break;
                case '*':
                    mudaEstadoAtual("OpAritMult");
                    break;
                case ',':
                    mudaEstadoAtual("Virgula");
                    break;
                default:
                    erroLexico("Símbolo " + caractere + " não reconhecido!");
            }// switch
            
        }// if-else
        
        // Caso o próximo caracter não faça parte do lexema
        return false;
    }

    /**
     * Analisa um caracter que não faz parte do início do lexema, verificando
     * se o mesmo pertence a linguagem, alterando o estado do AFD.
     * @param caracter O caracter que deve ser analisado.
     */
    public void verificaNovoCaracter(char caracter) {
        // Dependendendo do estado atual
        switch (estadoAtual) {
            case "Var":
                // Se não for mais letra ou dígito, marca o fim da variável
                if (!Character.isLetterOrDigit(caracter)) {
                    mudaEstadoAtual("FimVar");
                }// if
                break;
                
            case "NumReal":
                if (!Character.isDigit(caracter)) {
                    mudaEstadoAtual("FimReal");
                }// if
                break;
            case "NumInt":
                // Verifica se o número é Real ou se marca o fim do número inteiro
                if (caracter == '.' || caracter == ',') {
                    mudaEstadoAtual("NumReal");
                } else if (!Character.isDigit(caracter)) {
                    mudaEstadoAtual("FimInt");
                }// if-else
                break;
                
            case "CAD_1":
                // Verifica se a cadeia de caracteres chegou ao fim
                if (caracter == '"') {
                    mudaEstadoAtual("Cadeia");
                }// if
                break;
            case "PcSe":
                if (caracter == 'N') {
                    mudaEstadoAtual("PC_SEN1");
                }// if
                break;
            case "PC_D1":
                if (caracter == 'E') {
                    mudaEstadoAtual("PC_D2");
                } else {
                    erroLexico("Palavra reservada DEC escrita incorretamente");
                }// if-else
                break;
                
            case "PC_D2":
                if (caracter == 'C') {
                    mudaEstadoAtual("PcDec");
                } else {
                    erroLexico("Palavra reservada DEC escrita incorretamente");
                }// if-else
                break;
                
            case "PC_E1":
                if(caracter == 'N'){
                    mudaEstadoAtual("PC_E2");
                }else erroLexico("Palavra reservada EN* escrita incorretamente");
                break;
                
            case "PC_E2":
                switch (caracter) {
                    case 'Q':
                        mudaEstadoAtual("PC_ENQ1");
                        break;
                    case 'T':
                        mudaEstadoAtual("PC_ENT1");
                        break;
                    default:
                        erroLexico("Palavra reservada ËN* escrita incorretamente");
                }// switch
                break;
                
            case "PC_ENQ1":
                if (caracter == 'T') {
                    mudaEstadoAtual("PC_ENQ2");
                } else {
                    erroLexico("Palavra reservada ENQTO escrita incorretamente");
                }// if-else
                break;
                
            case "PC_ENQ2":
                if (caracter == 'O') {
                    mudaEstadoAtual("PcEnqto");
                } else {
                    erroLexico("Palavra reservada ENQTO escrita incorretamente");
                }// if-else
                break;
                
            case "PC_ENT1":
                if (caracter == 'A') {
                    mudaEstadoAtual("PC_ENT2");
                } else {
                    erroLexico("Palavra reservada ENTAO escrita incorretamente");
                }// if-else
                break;
                
            case "PC_ENT2":
                if (caracter == 'O') {
                    mudaEstadoAtual("PcEntao");
                } else {
                    erroLexico("Palavra reservada ENTAO escrita incorretamente");
                }// if-else
                break;
                
            case "PC_F1":
                if (caracter == 'I') {
                    mudaEstadoAtual("PC_F2");
                } else {
                    erroLexico("Palavra reservada FIM escrita incorretamente");
                }// if-else
                break;
                
            case "PC_F2":
                if (caracter == 'M') {
                    mudaEstadoAtual("PcFim");
                } else {
                    erroLexico("Palavra reservada FIM escrita incorretamente");
                }// if-else
                break;
                
            case "PC_I1":
                switch (caracter) {
                    case 'M':
                        mudaEstadoAtual("PC_IMP1");
                        break;
                    case 'N':
                        mudaEstadoAtual("PC_IN");
                        break;
                    default:
                        erroLexico("Palavra reservada I* escrita incorretamente");
                }// switch
                break;
                
            case "PC_IMP1":
                if (caracter == 'P') {
                    mudaEstadoAtual("PC_IMP2");
                } else {
                    erroLexico("Palavra reservada IMPRIMIR escrita incorretamente");
                }// if-else
                break;
                
            case "PC_IMP2":
                if (caracter == 'R') {
                    mudaEstadoAtual("PC_IMP3");
                } else {
                    erroLexico("Palavra reservada IMPRIMIR escrita incorretamente");
                }// if-else
                break;
                
            case "PC_IMP3":
                if (caracter == 'I') {
                    mudaEstadoAtual("PC_IMP4");
                } else {
                    erroLexico("Palavra reservada IMPRIMIR escrita incorretamente");
                }// if-else
                break;
                
            case "PC_IMP4":
                if (caracter == 'M') {
                    mudaEstadoAtual("PC_IMP5");
                } else {
                    erroLexico("Palavra reservada IMPRIMIR escrita incorretamente");
                }// if-else
                break;
                
            case "PC_IMP5":
                if (caracter == 'I') {
                    mudaEstadoAtual("PC_IMP6");
                } else {
                    erroLexico("Palavra reservada IMPRIMIR escrita incorretamente");
                }// if-else
                break;
                
            case "PC_IMP6":
                if (caracter == 'R') {
                    mudaEstadoAtual("PcImprimir");
                } else {
                    erroLexico("Palavra reservada IMPRIMIR escrita incorretamente");
                }// if-else
                break;
                
            case "PC_IN":
                switch (caracter) {
                    case 'I':
                        mudaEstadoAtual("PcIni");
                        break;
                    case 'T':
                        mudaEstadoAtual("PcInt");
                        break;
                    default:
                        erroLexico("Palavra reservada INT/INI escrita incorretamente");
                }// switch
                break;
                
            case "PC_L1":
                if (caracter == 'E') {
                    mudaEstadoAtual("PC_L2");
                } else {
                    erroLexico("Palavra reservada LER escrita incorretamente");
                }// if-else
                break;
                
            case "PC_L2":
                if (caracter == 'R') {
                    mudaEstadoAtual("PcLer");
                } else {
                    erroLexico("Palavra reservada LER escrita incorretamente");
                }// if-else
                break;
                
            case "PC_P1":
                
                if (caracter == 'R') {
                    mudaEstadoAtual("PC_P2");
                } else {
                    erroLexico("Palavra reservada PROG escrita incorretamente");
                }// if-else
                break;
                
            case "PC_P2":
                if (caracter == 'O') {
                    mudaEstadoAtual("PC_P3");
                } else {
                    erroLexico("Palavra reservada PROG escrita incorretamente");
                }// if-else
                break;
                
            case "PC_P3":
                if (caracter == 'G') {
                    mudaEstadoAtual("PcProg");
                } else {
                    erroLexico("Palavra reservada PROG escrita incorretamente");
                }// if-else
                break;
                
            case "PC_R1":
                if (caracter == 'E') {
                    mudaEstadoAtual("PC_R2");
                } else {
                    erroLexico("Palavra reservada REAL escrita incorretamente");
                }// if-else
                break;
                
            case "PC_R2":
                if (caracter == 'A') {
                    mudaEstadoAtual("PC_R3");
                } else {
                    erroLexico("Palavra reservada REAL escrita incorretamente");
                }// if-else
                break;
                
            case "PC_R3":
                if (caracter == 'L') {
                    mudaEstadoAtual("PcReal");
                } else {
                    erroLexico("Palavra reservada REAL escrita incorretamente");
                }// if-else
                break; 
            case "PC_SEN1":
                if (caracter == 'A') {
                    mudaEstadoAtual("PC_SEN2");
                } else {
                    erroLexico("Palavra reservada SENAO escrita incorretamente");
                }// if-else
                break;
            case "PC_SEN2":
                if (caracter == 'O') {
                    mudaEstadoAtual("PcSenao");
                } else {
                    erroLexico("Palavra reservada SENAO escrita incorretamente");
                }// if-else
                break; 
            default:// Caso o estado atual não apresente novas transições de estado
                erroLexico("Transição do estado \"" + estadoAtual + "\" inválida!");
        }// switch

    }

    /**
     * Altera o estado do autômato, indicando a ocorrência de um erro léxico e 
     * apresentando no terminal o motivo da ocorrência de tal erro.
     * @param motivo A descrição do tipo de erro léxico que ocorreu.
     */
    private void erroLexico(String motivo) {
        estadoAtual = "ErroLEXICO";
        System.err.println("Erro Léxico - " + motivo);
    }
    
}
