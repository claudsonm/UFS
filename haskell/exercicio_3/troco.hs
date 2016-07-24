{-
    75 [3, 20] crescente        ==>     [15,3]
    27 [5,2,1] crescente        ==>     [0,1,5]

    28 [5,2] crescente          ==>     [5,1,-1]
-}
import Data.List

crescente :: Ord a => [a] -> [a]
crescente [] = []
crescente x = sort x

decrescente :: Ord a => [a] -> [a]
decrescente [] = []
decrescente x = reverse (sort x)

chamaFuncao st x list   | st == "crescente" = crescente (change x list)
                        | otherwise = decrescente (change x list)

change :: Int -> [Int] -> [Int]
change n coins = reverse . snd $ foldl next (n, []) coins
    where next (remaining, cs) coin
            | coin <= remaining = (r', cnt:cs)
            | otherwise         = (remaining, 0:cs)
            where r' = remaining `mod` coin
                  cnt = remaining `div` coin

verificaDiferenca x y = sum (zipWith (*) x y)

passaTroco = do
    x <- getLine
    lista <- getLine
    funcao <- getLine
    if verificaDiferenca (change (read x :: Int) (read lista :: [Int])) (read lista :: [Int]) < (read x :: Int)
        then return (change (read x :: Int) (read lista :: [Int]) ++ [-1])
        else return (chamaFuncao funcao (read x :: Int) (read lista :: [Int]))
    --return (chamaFuncao funcao (read x :: Int) (read lista :: [Int]))
    --return (crescente (troco (read x :: Int) (map read $ words lista :: [Int])))

--main = print $ sum (verificaDiferenca [5, 2] [5, 1])

main :: IO ()
main = do
        entrada <- passaTroco
        print $ entrada
