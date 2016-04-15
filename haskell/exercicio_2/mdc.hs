lerLista :: IO [Int]
lerLista = do
    entrada <- getLine
    return (map read $ words entrada :: [Int])

primeiroElemento (x:_) = return x
segundoElemento (x:y:_) = return y
terceiroElemento (x:y:z:_) = return z

mdc :: Int -> Int -> Int
mdc a b | a < b = mdc b a
 | b == 0 = a
 | otherwise = mdc b (mod a b)

executa :: IO Int
executa = do
    lista <- lerLista
    a <- primeiroElemento lista
    b <- segundoElemento lista
    c <- terceiroElemento lista
    return (mdc a (mdc b c))

main :: IO ()
main = do
    valor <- executa
    print $ valor
