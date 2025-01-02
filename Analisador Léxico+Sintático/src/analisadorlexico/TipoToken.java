
package analisadorlexico;

/**
 * Representa os possíveis tipos de token que a linguagem suporta + estado de erro.
 * @author Gabriel Finger Conte
 * @author João Vitor Garcia Carvalho
 */
public enum TipoToken {

    PcDec, PcProg, PcInt, PcReal, PcLer, PcImprimir, PcSe, PcSenao, PcEntao, PcEnqto,
    PcIni, PcFim, OpAritMult, OpAritDiv, OpAritSoma, OpAritSub, OpRelMaior,
    OpRelMenor, OpRelMaiorIgual, OpRelMenorIgual, OpRelIgual, OpRelDif, OpBoolE, 
    OpBoolOu, Delim, Atrib, AbrePar, FechaPar, Var, NumInt, NumReal, Cadeia, EOF,
    ERROR;
    
}
