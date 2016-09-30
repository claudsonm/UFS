package analisador_lexico;

import java_cup.runtime.Symbol;

/**
 * Os tokens retornados pelo analizador léxico
 *
 */
public class Token extends Symbol {
    
    public int linha;
    public int coluna;
    
    public Token(int tipo, int linha, int coluna, Object valor) {
        this(tipo, linha, coluna, -1, -1, valor);
    }
  
    public Token(int tipo, int linha, int coluna, int esq, int dir, Object valor) {
        super(tipo, esq, dir, valor);
        this.linha = linha;
        this.coluna = coluna;
    }

    /*public String toString() {
        String msg;
        switch (sym) {
     // Palavras-chave
        case 8:
            msg = "VAR | ";
            break;
        case 2:
            msg = "BOOLEAN | ";
            break;
        case 101:
            msg = "CONS | ";
            break;
        case 5:
            msg = "INT | ";
            break;
        case 9:
            msg = "REAL | ";
            break;
        case 41:
            msg = "IF | ";
            break;
        case 42:
            msg = "THEN | ";
            break;
        case 43:
            msg = "ELSE | ";
            break;
        case 48:
            msg = "WHILE | ";
            break;
        case 66:
            msg = "PROCEDURE | ";
            break;
        case 67:
            msg = "FUNCTION | ";
            break;

        // Literais booleanos
        case 95:
            msg = "BOOLEAN_LITERAL | ";
            break;

        // Separadores
        case 19:
            msg = "LPAREN | ";
            break;
        case 20:
            msg = "RPAREN | ";
            break;
        case 16:
            msg = "LBRACE | ";
            break;
        case 17:
            msg = "RBRACE | ";
            break;
        case 10:
            msg = "LBRACK | ";
            break;
        case 11:
            msg = "RBRACK | ";
            break;
        case 13:
            msg = "SEMICOLON | ";
            break;
        case 15:
            msg = "COMMA | ";
            break;
        case 12:
            msg = "DOT | ";
            break;

        // Operadores
        case 18:
            msg = "EQ | ";
            break;
        case 70:
            msg = "GT | ";
            break;
        case 69:
            msg = "LT | ";
            break;
        case 63:
            msg = "NOT | ";
            break;
        case 74:
            msg = "EQEQ | ";
            break;
        case 60:
            msg = "PLUS | ";
            break;
        case 61:
            msg = "MINUS | ";
            break;
        case 14:
            msg = "MULT | ";
            break;
        case 64:
            msg = "DIV | ";
            break;
        case 76:
            msg = "AND | ";
            break;
        case 78:
            msg = "OR | ";
            break;
        case 65:
            msg = "MOD | ";
            break;

        // Literais numéricos
        case 93:
            msg = "INTEGER_LITERAL | ";
            break;
        case 94:
            msg = "FLOATING_POINT_LITERAL | ";
            break;

        case 98:
            msg = "IDENTIFIER | ";
            break;

        case 97:
            msg = "STRING_LITERAL | ";
            break;
        case 96:
            msg = "CHARACTER_LITERAL | ";
            break;
        case 0:
            msg = "EOF | ";
            break;
        case 1:
            msg = "error | ";
            break;        

        default:
            msg = "";
            break;
        }
        return (value == null ? msg : value + " | ") + "index: " + sym + " [" + linha + " : " + coluna + "]";
    }
    */
    
    public String toString() {
        return "line "+linha+", column "+coluna+", sym: "+sym+(value == null ? "" : (", value: '"+value+"'"));
    }
}