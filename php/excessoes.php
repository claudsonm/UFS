<?php
function inverso($x) {
    if (!$x) throw new Exception('Divisão por zero.');
    return 1/$x;
}

try {
    echo inverso(5) . "\n";
    echo inverso(0) . "\n";
} catch (Exception $e) {
    echo 'Exceção capturada: ',  $e->getMessage(), "\n";
}

// Execução continua
echo "Olá mundo\n";











/**
 * Define uma classe de exceção
 */
class MyException extends Exception
{
    // Redefine a exceção de forma que a mensagem não seja opcional
    public function __construct($message, $code = 0, Exception $previous = null) {
        // código
    
        // garante que tudo está corretamente inicializado
        parent::__construct($message, $code, $previous);
    }

    // personaliza a apresentação do objeto como string
    public function __toString() {
        return __CLASS__ . ": [{$this->code}]: {$this->message}\n";
    }

    public function customFunction() {
        echo "Uma função específica desse tipo de exceção\n";
    }
}


/**
 * Cria uma classe para testar a exceção
 */
class TestException
{
    public $var;

    const THROW_NONE    = 0;
    const THROW_CUSTOM  = 1;
    const THROW_DEFAULT = 2;

    function __construct($avalue = self::THROW_NONE) {

        switch ($avalue) {
            case self::THROW_CUSTOM:
                // lança a exeção customizada
                throw new MyException('1 é um parâmetro inválido', 5);
                break;

            case self::THROW_DEFAULT:
                // throw default one.
                throw new Exception('2 não é um parâmetro permitido', 6);
                break;

            default: 
                // Sem exceção, o objeto será criado
                $this->var = $avalue;
                break;
        }
    }
}


// Exemplo 1
try {
    $o = new TestException(TestException::THROW_CUSTOM);
} catch (MyException $e) {      // Entrará aqui ...
    echo "Pegou MyException\n", $e;
    $e->customFunction();
} catch (Exception $e) {        // ... mas não aqui.
    echo "Pegou Exception padrão\n", $e;
}

// Execução continua
var_dump($o); // Null
echo "\n\n";


// Examplo 2
try {
    $o = new TestException(TestException::THROW_DEFAULT);
} catch (MyException $e) {      // Não entrará aqui ...
    echo "Pegou MyException\n", $e;
    $e->customFunction();
} catch (Exception $e) {        // ... porque entrará aqui.
    echo "Pegou Exception padrão\n", $e;
}

// Execução continua
var_dump($o); // Null
echo "\n\n";


// Examplo 3
try {
    $o = new TestException(TestException::THROW_CUSTOM);
} catch (Exception $e) {        // Entrará aqui
    echo "Pegou exceção padrão\n", $e;
}

// Execução continua
var_dump($o); // Null
echo "\n\n";


// Examplo 4
try {
    $o = new TestException();
} catch (Exception $e) {        // Não entrará, sem exceção
    echo "Pegou Exception padrão\n", $e;
}

// Execução continua
var_dump($o); // TestException
echo "\n\n";
?>