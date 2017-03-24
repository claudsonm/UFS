<?php

use Carbon\Carbon;
use Illuminate\Database\Seeder;

class ProvidersTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('providers')->insert([
            'cnpj' => '29.993.439/0001-44',
            'razao_social' => 'Umbrella Corporation',
            'created_at' => Carbon::now(),
            'updated_at' => Carbon::now(),
        ]);
        DB::table('providers')->insert([
            'cnpj' => '81.832.136/0001-60',
            'razao_social' => 'Evil Corp',
            'created_at' => Carbon::now(),
            'updated_at' => Carbon::now(),
        ]);
        DB::table('providers')->insert([
            'cnpj' => '50.222.630/0001-89',
            'razao_social' => 'Los Pollos Hermanos',
            'created_at' => Carbon::now(),
            'updated_at' => Carbon::now(),
        ]);
    }
}
