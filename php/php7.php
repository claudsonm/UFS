<?php
function addOne(int $val)
{
    return $val+1;
}

var_dump(addOne('string'));



// Modo coercivo
function sumOfInts(int ...$ints)
{
    return array_sum($ints);
}

var_dump(sumOfInts(2, '3', 4.1));




function arraysSum(array ...$arrays): array
{
    return array_map(function (array $array): int
{
    return array_sum($array);
}, $arrays);
}

print_r(arraysSum([1, 2, 3], [4, 5, 6], [7, 8, 9]));




define('ANIMALS', [
    'dog',
    'cat',
    'bird'
]);

echo ANIMALS[1]; // imprime "cat"
;
