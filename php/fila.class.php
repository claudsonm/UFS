<?php
class Fila {
    private $first;
    function __construct() {}
    function __destruct() {}

    public function enfileira($valor) {
        if ($this->first == NULL) $this->first = $valor;
        else {
            $noAtual = $this->first;
            while ($noAtual->next != NULL)
                $noAtual = $noAtual->next;
            $noAtual->next = $var;
        }
    }

    public function desenfileira($valor) {
        if ($this->first == NULL) return NULL;
        $retorno = $this->first;
        $this->first = $this->first->next;
        return $retorno;
    }
}
?>