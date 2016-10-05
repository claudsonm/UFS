package analisador;

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

    public String toString() {
        String msg;
        
        switch (sym) {
     // Palavras-chave
        case 2:
            msg = "VAR | ";
            break;
        case 4:
            msg = "BOOLEAN | ";
            break;
        case 3:
            msg = "CONS | ";
            break;
        case 5:
            msg = "INT | ";
            break;
        case 6:
            msg = "REAL | ";
            break;
        case 9:
            msg = "IF | ";
            break;
        case 10:
            msg = "THEN | ";
            break;
        case 11:
            msg = "ELSE | ";
            break;
        case 12:
            msg = "WHILE | ";
            break;
        case 8:
            msg = "PROCEDURE | ";
            break;
        case 7:
            msg = "FUNCTION | ";
            break;

        // Literais booleanos
        case 36:
            msg = "BOOLEAN_LITERAL | ";
            break;

        // Separadores
        case 17:
            msg = "LPAREN | ";
            break;
        case 18:
            msg = "RPAREN | ";
            break;
        case 15:
            msg = "LBRACE | ";
            break;
        case 16:
            msg = "RBRACE | ";
            break;
        case 13:
            msg = "LBRACK | ";
            break;
        case 14:
            msg = "RBRACK | ";
            break;
        case 19:
            msg = "SEMICOLON | ";
            break;
        case 20:
            msg = "COMMA | ";
            break;

        // Operadores
        case 21:
            msg = "EQ | ";
            break;
        case 30:
            msg = "GT | ";
            break;
        case 29:
            msg = "LT | ";
            break;
        case 33:
            msg = "NOT | ";
            break;
        case 22:
            msg = "EQEQ | ";
            break;
        case 23:
            msg = "PLUS | ";
            break;
        case 24:
            msg = "MINUS | ";
            break;
        case 28:
            msg = "UMINUS | ";
            break;
        case 25:
            msg = "MULT | ";
            break;
        case 26:
            msg = "DIV | ";
            break;
        case 31:
            msg = "AND | ";
            break;
        case 32:
            msg = "OR | ";
            break;
        case 27:
            msg = "MOD | ";
            break;

        // Literais numéricos
        case 34:
            msg = "INTEGER_LITERAL | ";
            break;
        case 35:
            msg = "REAL_LITERAL | ";
            break;

        case 37:
            msg = "IDENTIFIER | ";
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
        return (value == null ? msg : value + " | ") + "Token: " + sym + " [" + linha + " : " + coluna + "]";
    }
}