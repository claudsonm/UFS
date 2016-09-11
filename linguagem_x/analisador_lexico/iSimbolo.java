package analisador_lexico;

/**
* Interface que contém todos os símbolos terminais
*
*/
public interface iSimbolo {
    // Palavras-chave
    public static final int VAR = 8;
    public static final int BOOLEAN = 2;
    public static final int CONS = 101;
    public static final int INT = 5;
    public static final int REAL = 9;
    public static final int IF = 41;
    public static final int THEN = 42;
    public static final int ELSE = 43;
    public static final int WHILE = 48;
    public static final int PROCEDURE = 66;
    public static final int FUNCTION = 67;

    // Literais booleanos
    public static final int BOOLEAN_LITERAL = 95;

    // Separadores
    public static final int LPAREN = 19;
    public static final int RPAREN = 20;
    public static final int LBRACE = 16;
    public static final int RBRACE = 17;
    public static final int LBRACK = 10;
    public static final int RBRACK = 11;
    public static final int SEMICOLON = 13;
    public static final int COMMA = 15;
    public static final int DOT = 12;

    // Operadores
    public static final int EQ = 18;
    public static final int GT = 70;
    public static final int LT = 69;
    public static final int NOT = 63;
    public static final int EQEQ = 74;
    public static final int PLUS = 60;
    public static final int MINUS = 61;
    public static final int MULT = 14;
    public static final int DIV = 64;
    public static final int AND = 76;
    public static final int OR = 78;
    public static final int MOD = 65;
    
    // Literais numéricos
    public static final int INTEGER_LITERAL = 93;
    public static final int FLOATING_POINT_LITERAL = 94;

    public static final int IDENTIFIER = 98;

    public static final int STRING_LITERAL = 97;
    public static final int CHARACTER_LITERAL = 96;
    public static final int EOF = 0;
    public static final int error = 1;
}