<?php

namespace App;

use Carbon\Carbon;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Promotion extends Model
{
    use SoftDeletes;

    public $timestamps = true;

    protected $dates = ['inicio', 'fim'];

    protected $fillable = array(
        'nome', 'inicio', 'fim', 'desconto', 'tipo'
    );

    public function sales()
    {
        return $this->hasMany('App\Sale');
    }

    public function setDescontoAttribute($valor)
    {
        $this->attributes['desconto'] = !is_null($valor) ? str_replace(',', '.', $valor) : null;
    }

    public function getDescontoAttribute()
    {
        return str_replace('.', ',', $this->attributes['desconto']);
    }

    public function setInicioAttribute($valor)
    {
        $this->attributes['inicio'] = Carbon::parse($valor);
    }

    public function setFimAttribute($valor)
    {
        $this->attributes['fim'] = Carbon::parse($valor);
    }

    public function getNomePromocaoAttribute()
    {
        $valor =    $this->attributes['tipo'] == 'fixo' ?
                    'R$ '. str_replace('.', ',', $this->attributes['desconto']) :
                    str_replace('.', ',', $this->attributes['desconto']) . '%';
        return $this->attributes['nome'] .' ('. $valor .')';
    }
}
