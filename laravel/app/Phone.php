<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Phone extends Model
{
    public $timestamps = false;

    protected $fillable = array('telefone');

    public function user()
    {
        return $this->belongsTo('App\User');
    }
}
