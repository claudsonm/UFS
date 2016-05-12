<?php
/**
 * O PHP não obriga (ou suporta) a definição de tipo explícita na
 * declaração de variáveis: o tipo de uma variável é determinado
 * pelo contexto em que a variável é utilizada.
 */

$foo = "0";  // $foo é string (ASCII 48)
$foo += 2;   // $foo é agora um interio (2)
$foo = $foo + 1.3;  // $foo é agora um float (3.3)
$foo = 5 + "10 pequenos porcos";   // $foo é inteiro (15)
$foo = 5 + "10 minúsculos porcos"; // $foo é inteiro (15)

$a    = 'car'; // $a é uma string
$a[0] = 'b';   // $a é ainda uma string
echo $a;       // bar

$foo = 10;             // $foo é um inteiro
$bar = (boolean) $foo; // $bar é um booleano
?>