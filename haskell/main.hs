--main = putStrLn "hello world"

{-diga_oi :: IO ()

diga_oi = do

  putStrLn "Qual eh o seu nomezinho?"
  name <- getLine
  putStrLn $ "Oi, " ++ name

main = diga_oi
-}

zeroToN :: Int -> [Int]
zeroToN x = [0..x]

add1ToFst :: [Int] -> [Int]
add1ToFst [] = []
add1ToFst (x:xs) = (x+1:xs)

add1ToLst :: [Int] -> [Int]
add1ToLst [] = []
add1ToLst (x:[]) = (x+1:[])
add1ToLst (x:xs) = (x:add1ToLst xs)

len :: [a] -> Int
len [] = 0
len (x:xs) = 1 + len xs

maior :: [Int] -> Int
maior [] = 0
maior (x:[]) = x
maior (x:xs) | x > (maior xs) = x
             | otherwise = (maior xs)
             
maiorPosicao :: [Int] -> Int -> (Int, Int)
maiorPosicao (x:[]) p = (x, p)
maiorPosicao (x:xs) p | (x, p) > (maiorPosicao xs (p+1)) = (x, p)
maiorPosicao (x:xs) p | x > (fst maiorPosicao xs (p+1)) = (x, p)
                    | otherwise = (maiorPosicao xs (p+1))

--main = print $ zeroToN 10
--main = print $ add1ToFst [8,5,4,9,7]
--main = print $ len ['A','R','I','E','L', 'A']
--main = print $ maior [4,2,9,8,10]
main = print $ maiorPosicao [4,2,9,8,10,7,20] 1
