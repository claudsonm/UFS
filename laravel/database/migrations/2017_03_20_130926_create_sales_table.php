<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateSalesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('sales', function (Blueprint $table) {
            $table->increments('id');
            $table->dateTime('data');
            $table->decimal('valor', 10, 2);
            $table->enum('tipo_pagamento', ['credito', 'debito', 'vista'])->nullable()->default('vista');
            $table->unsignedInteger('user_id')->index();
            $table->unsignedInteger('promotion_id')->nullable()->index();
            $table->unsignedInteger('customer_id')->index();
            $table->softDeletes();
            $table->nullableTimestamps();

            $table->foreign('user_id')->references('id')->on('users')
                        ->onDelete('no action')
                        ->onUpdate('no action');

            $table->foreign('promotion_id')->references('id')->on('promotions')
                        ->onDelete('no action')
                        ->onUpdate('no action');

            $table->foreign('customer_id')->references('id')->on('customers')
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
        Schema::dropIfExists('sales');
    }
}
