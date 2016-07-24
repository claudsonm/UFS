-- 3
-- 4 -9 5 12 70
-- [4,-9,12,70]

deleteEnesimo :: Int -> [a] -> [a]
deleteEnesimo n xs | n > 0 = take (n-1) xs ++ drop n xs

removerDaLista = do
                x <- getLine
                lista <- getLine                
                --return (map read $ words lista :: [Int])
                return (deleteEnesimo (read x :: Int) (map read $ words lista :: [Int]))


main :: IO ()
main = do
        entrada <- removerDaLista
        print $ entrada

--main = print $ deleteEnesimo 3 [4,-9,5,12,70]
