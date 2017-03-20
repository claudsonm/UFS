<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateProductSaleTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('product_sale', function (Blueprint $table) {
            $table->unsignedInteger('product_id')->index();
            $table->unsignedInteger('sale_id')->index();
            $table->integer('quantidade');

            $table->foreign('product_id')->references('id')->on('products')
                        ->onDelete('no action')
                        ->onUpdate('no action');

            $table->foreign('sale_id')->references('id')->on('sales')
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
        Schema::dropIfExists('product_sale');
    }
}
