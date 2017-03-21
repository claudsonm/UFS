<?php

namespace App;

use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;

class User extends Authenticatable
{
    use Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'name', 'email', 'password', 'gerente', 'cpf', 'ctps', 'data_nascimento'
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'password', 'remember_token',
    ];

    protected $dates = ['data_nascimento'];

    public function phones()
    {
        return $this->hasMany('App\Phone');
    }

    public function sales()
    {
        return $this->hasMany('App\Sale');
    }

    public function purchase()
    {
        return $this->hasMany('App\Purchase');
    }
}
