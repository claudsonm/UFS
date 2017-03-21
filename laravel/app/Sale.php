<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Sale extends Model
{
    use SoftDeletes;

    public $timestamps = true;

    protected $dates = ['data'];

    protected $fillable = array(
        'data', 'valor', 'tipo_pagamento'
    );

    public function promotion()
    {
        return $this->hasOne('App\Promotion');
    }

    public function customer()
    {
        return $this->hasOne('App\Customer');
    }

    public function user()
    {
        return $this->hasOne('App\User');
    }

    public function products()
    {
        return $this->belongsToMany('App\Product');
    }
}
