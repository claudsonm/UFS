{-  Exercício 1
    Questão 2
    Função MergeSort
-}
module MergeSort where

primeiraMetadeLista :: [a] -> [a]
primeiraMetadeLista xs = take (length xs `div` 2) xs

segundaMetadeLista :: [a] -> [a]
segundaMetadeLista xs = drop (length xs `div` 2) xs

juntaListas :: Ord a => [a] -> [a] -> [a]
juntaListas xs [] = xs
juntaListas [] ys = ys
juntaListas (x:xs) (y:ys) 
    | (x <= y)  = x:(juntaListas xs (y:ys)) 
    | otherwise = y:(juntaListas (x:xs) ys)

mergesort :: Ord a => [a] -> [a]
mergesort [] = []
mergesort [x] = [x]
mergesort xs = juntaListas
    (mergesort (primeiraMetadeLista xs))
    (mergesort (segundaMetadeLista xs))