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
        return $this->hasOne('App\Provider');
    }

    public function sales()
    {
        return $this->belongsToMany('App\Sale');
    }

    public function purchases()
    {
        return $this->belongsToMany('App\Purchase');
    }
}
