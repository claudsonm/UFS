import Data.Char
import Data.List

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
