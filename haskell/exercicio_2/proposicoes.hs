-- (P1 and not Q) or (not P1 and not (Q or S2))
-- ["P1","Q","S2"]
import Data.Char
import Data.List

removeCaracteres :: String -> String -> String
removeCaracteres = filter . flip notElem

proposicoes :: IO [String]
proposicoes = do
                entrada <- getLine
                return (nub (words (removeCaracteres "ANDORT()" (map toUpper entrada))))

main = do
        valor <- proposicoes
        print $ valor