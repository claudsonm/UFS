/**
 * Especificação do analisador léxico para a Linguaguem X
 */

package analisador;

import java_cup.runtime.*;
%%
%public
%class AnalisadorLexico
%cup
%implements iSimbolo
%type Token
%line
%column
%unicode

%{
    private Token token(int tipo) {
        return new Token(tipo, yyline+1, yycolumn+1, null);
    }

    private Token token(int tipo, Object valor) {
        return new Token(tipo, yyline+1, yycolumn+1, valor);
    }
%}

/* caracteres principais */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comentários */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* identificadores */
Identifier = [:jletter:][:jletterdigit:]*

/* literais inteiros */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

/* literais reais */        
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+

%%

<YYINITIAL> {

  /* palavras-chave */
  "var"                          { return token(VAR); }
  "bool"                         { return token(BOOLEAN); }
  "cons"                         { return token(CONS); }
  "int"                          { return token(INT); }
  "real"                         { return token(REAL); }
  "if"                           { return token(IF); }
  "then"                         { return token(THEN); }
  "else"                         { return token(ELSE); }
  "while"                        { return token(WHILE); }
  "procedure"                    { return token(PROCEDURE); }
  "function"                     { return token(FUNCTION); }
  
  /* literais booleanos */
  "true"                         { return token(BOOLEAN_LITERAL, true); }
  "false"                        { return token(BOOLEAN_LITERAL, false); }
  
  /* separadores */
  "("                            { return token(LPAREN); }
  ")"                            { return token(RPAREN); }
  "{"                            { return token(LBRACE); }
  "}"                            { return token(RBRACE); }
  "["                            { return token(LBRACK); }
  "]"                            { return token(RBRACK); }
  ";"                            { return token(SEMICOLON); }
  ","                            { return token(COMMA); }
  
  /* operadores */
  ":="                           { return token(EQ); }
  ">"                            { return token(GT); }
  "<"                            { return token(LT); }
  "!"                            { return token(NOT); }
  "="                            { return token(EQEQ); }
  "+"                            { return token(PLUS); }
  "-"                            { return token(MINUS); }
  "*"                            { return token(MULT); }
  "/"                            { return token(DIV); }
  "and"                          { return token(AND); }
  "or"                           { return token(OR); }
  "%"                            { return token(MOD); }
  
  /* literais numéricos */

  /* Casado juntamente com o menos porque o numero é muito grande para ser 
     representado por um inteiro positivo. */
  "-2147483648"                  { return token(INTEGER_LITERAL, new Integer(Integer.MIN_VALUE)); }
  
  {DecIntegerLiteral}            { return token(INTEGER_LITERAL, new Integer(yytext())); }
  {DecLongLiteral}               { return token(INTEGER_LITERAL, new Long(yytext().substring(0,yylength()-1))); }
  
  {FloatLiteral}                 { return token(REAL_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return token(REAL_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return token(REAL_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  
  /* comentários */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identificadores */ 
  {Identifier}                   { return token(IDENTIFIER, yytext()); }  
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return token(EOF); }