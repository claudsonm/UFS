<?php

namespace App;

use Carbon\Carbon;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Sale extends Model
{
    use SoftDeletes;

    public $timestamps = true;

    protected $dates = ['data'];

    protected $fillable = array(
        'data', 'valor', 'tipo_pagamento', 'user_id', 'customer_id', 'promotion_id'
    );

    public function promotion()
    {
        return $this->belongsTo('App\Promotion');
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
        return $this->belongsToMany('App\Product')->withPivot('quantidade', 'preco');
    }

    public function setDataAttribute($valor)
    {
        $this->attributes['data'] = Carbon::parse($valor);
    }
}
