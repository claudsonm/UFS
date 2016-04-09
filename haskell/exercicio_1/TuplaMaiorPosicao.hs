{-  Exercício 1
    Questão 1
    Retorna uma tupla com o maior elemento e sua posição
-}
module TuplaMaiorPosicao where

maiorPos :: [Int] -> (Int, Int)
maiorPos [] = (-1, -1)
maiorPos x = maior x 1

maior :: [Int] -> Int -> (Int, Int)
maior (x:[]) p = (x, p)
maior (x:resto) p
    | (x, p) > (maior resto (p+1)) = (x, p)
    | otherwise = (maior resto (p+1))