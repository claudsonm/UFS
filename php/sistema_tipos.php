<?php
/**
 * Calculate the sum of n numbers
 * @return int Sum value
 */
function findSum() {
    $sum = 0;
    foreach (func_get_args() as $arg)
        $sum += $arg;
    return $sum;
}

echo findSum(1, 2), "\n"; // 3
echo findSum(10, 2, 100), "\n"; // 112
echo findSum(10, 22, 0.5, 0.75, 12.50), "\n"; // 45.75




/**
 * Add two numbers or concatenate two strings
 */
function add() {
    //cross check for exactly two parameters passed
    //while calling this function
    if (func_num_args() != 2) {
        trigger_error('Expecting two arguments', E_USER_ERROR);
    }

    //getting two arguments
    $args = func_get_args();
    $arg1 = $args[0];
    $arg2 = $args[1];

    //check whether they are integers
    if (is_int($arg1) && is_int($arg2)) {
        //return sum of two numbers
        return $arg1 + $arg2;
    }

    //check whether they are strings
    if (is_string($arg1) && is_string($arg2)) {
        //return concatenated string
        return $arg1 . ' ' . $arg2;
    }

    trigger_error('Incorrect parameters passed', E_USER_ERROR);
}

echo add(10, 15), "\n"; //outputs 25
echo add("Hello", "World"), "\n"; //outputs Hello World




class Foo {
    public function __call($method, $args) {
        if ($method === 'findSum')
            echo 'Sum is calculated to ' . $this->_getSum($args);
        else echo "Called method $method";
    }

    private function _getSum($args) {
        $sum = 0;
        foreach ($args as $arg)
            $sum += $arg;
        return $sum;
    }
}

$foo = new Foo;
$foo->bar1(); // Called method bar1
$foo->bar2(); // Called method bar2
$foo->findSum(10, 50, 30); //Sum is calculated to 90
$foo->findSum(10.75, 101); //Sum is calculated to 111.75



/**
 * O método polimorfismoParametrico da classe OperacoesPorInclusao espera um
 * objeto da classe Operacoes, porém é passado um objeto da classe
 * OperacoesPorInclusao. O que caracteriza o polimorfismo universal paramétrico.
 */
class Operacoes {
    function soma($number1, $number2) {
        return $number1 + $number2;
    }
}

class OperacoesPorInclusao extends Operacoes {
    function polimorfismoParametrico(Operacoes $op) {
        echo $op->soma(1, 2) . "\n";    
    }
}

$obj = new Operacoes();
$obj2 = new OperacoesPorInclusao();
$obj3 = new OperacoesPorInclusao();

$obj2->polimorfismoParametrico($obj3);




class Usuario {
    var $nome;
    var $cpf;
    
    public function __construct($nome, $cpf) {
        $this->nome = $nome;
        $this->cpf = $cpf;
    }
    
    public function getUsuario() {
        return "nome: ".$this->nome."\ncpf : ".$this->cpf;
    }
    
    public function imprime() {
        echo $this->nome."--".$this->cpf."\n";
    }
}
    
class Aluno extends Usuario {
    var $codigo;
    
    public function __construct($nome, $cpf, $codigo) {
        parent::__construct($nome,$cpf);
        $this->codigo = $codigo;
    }
    
    public function getAluno() {
        return parent::getUsuario()."\ncodigo: ".$this->codigo;
    }
    
    public function imprime() {
        echo "funcao imprime pai: ";
        parent::imprime();
        echo "\nfuncao imprime filho: " . $this->nome . "--" . $this->cpf .
            "--" . $this->codigo . "\n"
        ;
    }
}
    
/**
 * Existe o polimorfismo por inclusao, pois é possível chamar o método
 * imprime da classe pai dentro do método imprime da classe filho.
 * 
 * @return  funcao imprime pai: Tiago--123456
 *          funcao imprime filho: Tiago--123456--40356788
 */
$aluno = new Aluno("Tiago",123456,40356788);
$aluno->imprime();




class Operacao {
    var $valor1, $valor2;

    public function setValores($valor1,$valor2) {
        $this->valor1 = $valor1;
        $this->valor2 = $valor2;
    }
    
    public function somaValores() {
        $resultado =  $this->valor1 + $this->valor2;
        return $resultado;
    }
    
    public function verificaValores() {
        if(is_int($this->valor1)) echo 'valor1 é inteiro.';
        else echo 'valor1 não é inteiro.';
        if(is_int($this->valor2)) echo "\nvalor2 é inteiro.";
        else echo "\n$valor2 não é inteiro.";
    }
}

$operacao = new Operacao();
$operacao->setValores(5,4);
$operacao->verificaValores(); // inteiro inteiro
echo "\nresultado da operacao: ".$operacao->somaValores()."\n"; // 9

$operacao->setValores("5",4);
$operacao->verificaValores(); // string inteiro
echo "\nresultado da operação: ".$operacao->somaValores(); // 9
echo "\n\n";



abstract class ClasseAbstrata {
    // Força a classe que estende ClasseAbstrata a definir esse método
    abstract protected function pegarValor();
    abstract protected function valorComPrefixo( $prefixo );

    // Método comum
    public function imprimir() {
        print $this->pegarValor();
    }
}

class ClasseConcreta1 extends ClasseAbstrata {
    protected function pegarValor() {
        return "ClasseConcreta1";
    }

    public function valorComPrefixo( $prefixo ) {
        return "{$prefixo}ClasseConcreta1";
    }
}

class ClasseConcreta2 extends ClasseAbstrata {
    protected function pegarValor() {
        return "ClasseConcreta2";
    }

    public function valorComPrefixo( $prefixo ) {
        return "{$prefixo}ClasseConcreta2";
    }
}

$classe1 = new ClasseConcreta1;
$classe1->imprimir();
echo $classe1->valorComPrefixo('FOO_') ."\n";

$classe2 = new ClasseConcreta2;
$classe2->imprimir();
echo $classe2->valorComPrefixo('FOO_') ."\n";
?>