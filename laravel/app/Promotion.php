<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Promotion extends Model
{
    use SoftDeletes;

    public $timestamps = true;

    protected $date = ['inicio', 'fim', 'deleted_at'];

    protected $fillable = array(
        'nome', 'inicio', 'fim', 'desconto', 'tipo'
    );

    public function sales()
    {
        return $this->hasMany('App\Sale');
    }
}
