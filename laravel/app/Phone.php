<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Phone extends Model
{
    protected $fillable = array('telefone');

    public function user()
    {
        return $this->belongsTo('App\User');
    }
}
