-- Retorna a quantidade de divisores entre 2 numeros
quantidadeDivisores :: Int -> Int -> Int
quantidadeDivisores n 1 = 1
quantidadeDivisores n m = if  mod n m == 0
              then 1 + quantidadeDivisores n (m-1)
              else quantidadeDivisores n (m -1)

verificaPrimo ::Int -> Bool
verificaPrimo n = (quantidadeDivisores n n) == 2

import Data.List
troco t l@(x:xs) = map (\xs -> length xs) . group $ ft t l

--[25,25,10,10,1]
ft p [] = []
ft p m@(x:xs) | p >= x = x:ft (p-x) m
                          | otherwise = ft p xs
                          
decrescente [] = []
decrescente (s:xs) = decrescente [x|x <- xs,x > s] ++ [s] ++ decrescente [x|x <- xs,x <= s]

crescente [] = []
crescente (s:xs) = crescente [x|x <- xs,x < s] ++ [s] ++ crescente [x|x <- xs,x >= s]

teste a b c = a

main = print $ crescente (troco 27 [5,2,1])

--main :: IO ()
--main = do
--        entrada <- tudo
--        print $ entrada

                
tudo = do
    lista <- fmap words getLine
    a <- primeiroEle lista
    b <- segundoEle lista
    c <- terceiroEle lista
    return (troco 5 [5,1])

primeiroEle (x:_) = return x
segundoEle (x:y:_) = return y
terceiroEle (x:y:z:_) = return z