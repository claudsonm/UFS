<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Customer extends Model
{
    use SoftDeletes;

    public $timestamps = true;

    /**
     * Campos que devem ser tratados como uma instância do Carbon
     * @var array
     */
    protected $dates = ['data_nascimento', 'deleted_at'];

    /**
     * Atributos que podem ser enviados ao banco na criação de um cliente
     * @var array
     */
    protected $fillable = array(
        'nome', 'data_nascimento', 'cpf', 'cep', 'logradouro', 'numero',
        'complemento', 'bairro', 'cidade', 'estado'
    );

    public function sales()
    {
        return $this->hasMany('App\Sale');
    }
}
