data ArvoreBinaria a =  Vazio
                        | No a (ArvoreBinaria a) (ArvoreBinaria a)
                        deriving (Eq, Show)

buscaValor x Vazio = "inexistente"
buscaValor x (No (string, valor) arvoreEsquerda arvoreDireita)
    | x == valor = string
    | x < valor = buscaValor x arvoreEsquerda
    | x > valor = buscaValor x arvoreDireita

arv :: ArvoreBinaria (String, Int)
arv =   
    No ("seis", 6)
        (No ("tres", 3)  
            (No ("um", 1)  
                (No ("zero", 0) Vazio Vazio)  
                (No ("dois", 2) Vazio Vazio)  
            )  
            (No ("cinco", 5)
                (No ("quatro", 4) Vazio Vazio)
                (Vazio)
            )  
        )  
        (No ("oito", 8)  
            (No ("sete", 7) Vazio Vazio)  
            (No ("nove", 9) Vazio Vazio)  
        )
        
retornaLiteral = do
    entrada <- getLine
    return (buscaValor (read entrada :: Int) arv)

main :: IO()
main = do
    valor <- retornaLiteral
    print $ valor
