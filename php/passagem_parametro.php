<?php
function metodoCopia($var) {
    $var++;
}

function metodoReferencia(&$var) {
    $var++;
}

$var = 0;

metodoCopia($var);
echo "Valor: $var\n";

metodoReferencia($var);
echo "Valor: $var\n";

function passagemCopia($local) {
    echo "ID da variável local: ". uniqid($local) ."\n";
    echo "Valor da variável local: $local\n";
    $local++;
    echo "Valor da variável local: $local\n\n";
}

$global = 2;

echo "ID da variável global: ". uniqid($global) ."\n";
echo "Valor da variável global: $global\n\n";

passagemCopia($global);

echo "Valor da variável global após a função: $global\n";
?>