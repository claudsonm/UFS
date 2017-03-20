<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateCustomersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('customers', function (Blueprint $table) {
            $table->increments('id');
            $table->string('nome');
            $table->date('data_nascimento')->nullable();
            $table->char('cpf', 14)->unique()->nullable();
            $table->char('cep', 9)->nullable();
            $table->string('logradouro');
            $table->smallInteger('numero')->nullable();
            $table->string('complemento')->nullable();
            $table->string('bairro');
            $table->string('cidade');
            $table->char('estado', 2);
            $table->softDeletes();
            $table->nullableTimestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('customers');
    }
}
