<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateProductsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('products', function (Blueprint $table) {
            $table->increments('id');
            $table->string('nome')->unique();
            $table->text('descricao')->nullable();
            $table->date('validade')->nullable();
            $table->decimal('preco', 10, 2)->nullable();
            $table->float('quantidade');
            $table->float('quantidade_minima')->nullable();
            $table->unsignedInteger('provider_id')->nullable()->index();
            $table->softDeletes();
            $table->nullableTimestamps();

            $table->foreign('provider_id')->references('id')->on('providers')
                        ->onDelete('no action')
                        ->onUpdate('no action');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('products');
    }
}
