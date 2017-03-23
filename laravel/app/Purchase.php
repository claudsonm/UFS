<?php

namespace App;

use Carbon\Carbon;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Purchase extends Model
{
    use SoftDeletes;

    public $timestamps = true;

    protected $dates = ['data'];

    protected $fillable = array(
        'data', 'valor', 'user_id'
    );

    public function user()
    {
        return $this->belongsTo('App\User');
    }

    public function products()
    {
        return $this->belongsToMany('App\Product')->withPivot('preco', 'quantidade');
    }

    public function setDataAttribute($valor)
    {
        $this->attributes['data'] = Carbon::parse($valor);
    }

    public function setValorAttribute($valor)
    {
        $this->attributes['valor'] = !is_null($valor) ? str_replace(',', '.', $valor) : null;
    }

    public function getValorAttribute()
    {
        return str_replace('.', ',', $this->attributes['valor']);
    }
}
