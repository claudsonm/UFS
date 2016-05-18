<?php
/**
 * O escopo de uma variável é o contexto onde foi definida.
 * A maioria das variáveis do PHP tem somente escopo local.
 * Este escopo local inclui os arquivos incluídos e requeridos.
 */
$a = 1;
$b = 2;

function soma()
{
    global $a, $b;
    $b = $a + $b;
}

soma();
echo $b;

// include 'arquivo.php';
?>