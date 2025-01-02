
package analisadorlexico;

/**
 * Representa um token gerado pelo analisador lexico.
 * @author Gabriel Finger Conte
 * @author João Vitor Garcia Carvalho
 */
public class Token {
    
    /**
     * O padrão do token.
     */
    private final TipoToken padrao;
    /**
     * O lexema do token.
     */
    private final String lexema;

    /**
     * Cria um novo token, contendo o lexema e o padrão do mesmo.
     * @param padrao o padrão do lexema.
     * @param lexema o lexema.
     */
    public Token(TipoToken padrao, String lexema) {
        this.padrao = padrao;
        this.lexema = lexema;
    }
    
    /**
     * Converte o objeto no formato textual em que o token será salvo.
     * @return Retorna o token no formato de uma String que será salva no arquivo de tokens.
     */
    @Override
    public String toString(){        
        if(padrao.equals(TipoToken.EOF)){
            return "<EOF>"; 
        }else return "<" + this.padrao.name() + ", " + this.lexema + " >";
    }
    
}
