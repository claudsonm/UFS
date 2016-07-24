<?php
function teste($texto) {
    sleep(10);
    echo $texto;
}

$file = fopen('teste.txt', 'w');
fwrite($file, 'DIG-DIG-JOY, DIG-JOY POPOY');
fclose($file);

$file = fopen('teste.txt', 'r');
teste(fread($file, 26));
?>
