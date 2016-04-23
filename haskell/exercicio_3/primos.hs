verificaPrimo :: Int -> Bool
verificaPrimo p | p < 2 = False
                | otherwise = verificaPrimoAux p 2 

verificaPrimoAux :: Int -> Int -> Bool
verificaPrimoAux p q  | q*q > p         = True
                      | p `mod` q == 0  = False
                      | otherwise       = verificaPrimoAux p (q+1)

verificaProximo :: Int -> Int
verificaProximo n | verificaPrimo n == True = n
                  | otherwise = verificaProximo (n + 1)

proximoPrimo :: IO Int
proximoPrimo = do
                  entrada <- getLine
                  return (verificaProximo (read entrada :: Int))

main :: IO ()
main = do
        entrada <- proximoPrimo
        print $ entrada
