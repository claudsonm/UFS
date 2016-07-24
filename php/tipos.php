<?php
$a_bool = TRUE;   // um booleano
$a_str  = "foo";  // uma string
$a_str2 = 'foo';  // uma string
$an_int = 12;     // um inteiro

echo gettype($a_bool); // mostra:  boolean
echo gettype($a_str);  // mostra:  string

// Se ele é um inteiro, incrementa-o com quatro
if (is_int($an_int)) {
    $an_int += 4;
}

// Se $bool é uma string, mostre-a
// (não imprime nada)
if (is_string($a_bool)) {
    echo "String: $a_bool";
}

// Um array simples
$array = array(
    "foo" => "bar",
    "bar" => "bar",
);

// a partir do PHP 5.4
$array = [
    "foo" => "bar",
    "bar" => "foo",
];

$array = array(
    "foo" => "bar",
    "bar" => "foo",
    100   => -100,
    -100  => 100,
);
var_dump($array);
?>