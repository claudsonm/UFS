<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Product extends Model
{
    use SoftDeletes;

    public $timestamps = true;

    protected $dates = ['validade'];

    protected $fillable = array(
        'nome', 'descricao', 'validade', 'preco', 'quantidade',
        'quantidade_minima'
    );

    public function provider()
    {
        return $this->belongsTo('App\Provider');
    }

    public function sales()
    {
        return $this->belongsToMany('App\Sale');
    }

    public function purchases()
    {
        return $this->belongsToMany('App\Purchase')->withPivot('preco', 'quantidade');
    }

    /**
     * Substitui a vírgula por ponto antes de persistir no banco
     * @param String $valor
     */
    public function setPrecoAttribute($valor)
    {
        $this->attributes['preco'] = !is_null($valor) ? str_replace(',', '.', $valor) : null;
    }

    /**
     * Substitui a vírgula por ponto antes de persistir no banco
     * @param String $valor
     */
    public function setQuantidadeAttribute($valor)
    {
        $this->attributes['quantidade'] = !is_null($valor) ? str_replace(',', '.', $valor) : null;
    }

    /**
     * Substitui a vírgula por ponto antes de persistir no banco
     * @param String $valor
     */
    public function setQuantidadeMinimaAttribute($valor)
    {
        $this->attributes['quantidade_minima'] = !is_null($valor) ? str_replace(',', '.', $valor) : null;
    }

    public function getPrecoAttribute()
    {
        return str_replace('.', ',', $this->attributes['preco']);
    }

    public function getQuantidadeAttribute()
    {
        return str_replace('.', ',', $this->attributes['quantidade']);
    }

    public function getQuantidadeMinimaAttribute()
    {
        return str_replace('.', ',', $this->attributes['quantidade_minima']);
    }
}
