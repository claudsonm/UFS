# Sintaxe da lógica sentencial (CP)

## Alfabeto

* Símbolo de Pontuação: `(`,`)`
* Símbolo de verdade: `true` , `false`
* Símbolo proposicionais: `P`, `Q`, `R`, `S`, `P1`, `Q1`, `R1`, `S1`, `P2`, ...
* Conectivos proposicionais: `not`, `and`, `or`, `->`, `<->`

## Fórmulas

* Todo símbolo de verdade é uma fórmula;
* Todo símbolo proposicional é uma fórmula;
* Se ``H`` é uma fórmula, então, `(not H)` é uma fórmula;
* Se ``H`` e ``G`` são fórmulas, então, `(H and G)` é uma fórmula;
* Se ``H`` e ``G`` são fórmulas, então, `(H or G)` é uma fórmula;
* Se ``H`` e ``G`` são fórmulas, então, `(H -> G)` é uma fórmula;
* Se ``H`` e ``G`` são fórmulas, então, `(H <-> G)` é uma fórmula;

## BNF (Backus-Naur Form)

```
Program     ::=     Expression

Expression  ::=     primary-Expression
                    | Expression Operator primary-Expression

primary-Expression  ::=     V-name
                            | TF-symbol
                            | (Expression)
                            | not (Expression)

V-name      ::=     Identifier

TF-symbol   ::=     true | false

Identifier  ::=     [PQRS][[:digit:]]*

Operator    ::=     and | or | -> | <->
```

## Convenções

### Token.String

```
and     ---->   *
not     ---->   (+1) % 2
true    ---->   1
false   ---->   0
or
```

### Conjunção (AND)

```
1 x 1   = 1
1 x 0   = 0
0 x 1   = 0
0 x 0   = 0
```