<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateProductPurchaseTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('product_purchase', function (Blueprint $table) {
            $table->unsignedInteger('product_id')->index();
            $table->unsignedInteger('purchase_id')->index();
            $table->integer('quantidade');

            $table->foreign('product_id')->references('id')->on('products')
                        ->onDelete('no action')
                        ->onUpdate('no action');

            $table->foreign('purchase_id')->references('id')->on('purchases')
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
        Schema::dropIfExists('product_purchase');
    }
}
