<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePurchasesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('purchases', function (Blueprint $table) {
            $table->increments('id');
            $table->dateTime('data');
            $table->decimal('valor', 10, 2);
            $table->unsignedInteger('user_id')->index();
            $table->softDeletes();
            $table->nullableTimestamps();

            $table->foreign('user_id')->references('id')->on('users')
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
        Schema::dropIfExists('purchases');
    }
}
