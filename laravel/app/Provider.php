<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Provider extends Model
{
    public $timestamps = true;

    protected $fillable = array(
        'cnpj', 'razao_social'
    );

    public function products()
    {
        return $this->hasMany('App\Product');
    }
}
