import Data.Char
import Data.List

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


rev :: [a] -> [a]
rev []      = [] 
rev (x:xs)  = reverse xs ++ [x]

strLower :: String -> String
strLower str = map toLower str

strLetters :: String -> String
strLetters str = filter isAlpha str

strLowerLetters :: String -> String
strLowerLetters = strLower.strLetters

palindromo = do
                x <- getLine
                return (strLowerLetters x == strLowerLetters (rev x))


main :: IO ()
main = do
        entrada <- palindromo
        print $ entrada

--main = print $ removeSpaces "amor e                            roma"
