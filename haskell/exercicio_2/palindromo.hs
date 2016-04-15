import Data.Char (isSpace)

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
             

removeItem :: Int -> [Int] -> [Int]
removeItem _ []                 = []
removeItem x (y:ys) | x == y    = removeItem x ys
                    | otherwise = y : removeItem x ys

-- Ler lista

lerlista :: IO [Int]
lerlista = do 
              linha <- getLine
              return (read linha :: [Int])
           
lerstring :: IO String
lerstring = do
              linha1 <- getLine
              if linha1 == []
                 then return []
                 else do
                        linha2 <- lerstring
                        if linha2 == []
                           then return linha1
                           else return (linha1 ++ "," ++ linha2)

lerlistaint :: IO [Int]
lerlistaint = do
                linha <- lerstring
                return (read ("[" ++ linha ++ "]") :: [Int])








palindromo :: IO Bool
palindromo = do
                entrada <- getLine
                return ((rev (removeSpaces entrada)) == (removeSpaces entrada))

rev xs =    if null xs
            then xs
            else rev (tail xs) ++ [head xs]

-- Remove espaços
removeSpaces :: String -> String
removeSpaces "" = ""
removeSpaces x = if (head x) /= ' ' then (head x) : removeSpaces (tail x) else removeSpaces (tail x) -- se encontrar espaço, não insere na nova lista


trim xs = dropSpaceTail "" $ dropWhile isSpace xs

dropSpaceTail maybeStuff "" = ""
dropSpaceTail maybeStuff (x:xs)
        | isSpace x = dropSpaceTail (x:maybeStuff) xs
        | null maybeStuff = x : dropSpaceTail "" xs
        | otherwise       = reverse maybeStuff ++ x : dropSpaceTail "" xs

--main = print $ zeroToN 10
--main = print $ add1ToFst [8,5,4,9,7]
--main = print $ len ['A','R','I','E','L', 'A']
--main = print $ maior [4,2,9,8,10]
--main = print $ exercicioRemoveElemento
main :: IO ()
main = do
        entrada <- palindromo
        --valor <- rev entrada
        print $ entrada


--reverse = foldl (flip (:)) []

--main = print $ removeSpaces "amor e                            roma"
