<?php

use Carbon\Carbon;
use Illuminate\Database\Seeder;

class ProductsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('products')->insert([
            'nome' => 'Computador E-Corp X9-666',
            'descricao' => 'Mussum Ipsum, cacilds vidis litro abertis. Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Sapien in monti palavris qui num significa nadis i pareci latim. Em pé sem cair, deitado sem dormir, sentado sem cochilar e fazendo pose.',
            'preco' => '2499.00',
            'quantidade' => '200',
            'quantidade_minima' => '10',
            'provider_id' => 2,
            'created_at' => Carbon::now(),
            'updated_at' => Carbon::now(),
        ]);
        DB::table('products')->insert([
            'nome' => 'Zombie Gun L 4 D34D',
            'descricao' => 'Mais vale um bebadis conhecidiss, que um alcoolatra anonimiss. Interagi no mé, cursus quis, vehicula ac nisi. Mé faiz elementum girarzis, nisi eros vermeio. Quem num gosta di mé, boa gente num é.',
            'preco' => '679.00',
            'quantidade' => '50',
            'quantidade_minima' => '5',
            'provider_id' => 1,
            'created_at' => Carbon::now(),
            'updated_at' => Carbon::now(),
        ]);
        DB::table('products')->insert([
            'nome' => 'Yo Gus Pollo',
            'descricao' => 'Atirei o pau no gatis, per gatis num morreus. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis! Nec orci ornare consequat. Praesent lacinia ultrices consectetur. Sed non ipsum felis. Paisis, filhis, espiritis santis.',
            'preco' => '5.99',
            'quantidade' => '100',
            'provider_id' => 3,
            'created_at' => Carbon::now(),
            'updated_at' => Carbon::now(),
        ]);
        DB::table('products')->insert([
            'nome' => 'Erva',
            'descricao' => 'Quem num gosta di mé, boa gente num é. Delegadis gente finis, bibendum egestas augue arcu ut est. Viva Forevis aptent taciti sociosqu ad litora torquent Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis.',
            'preco' => '10.00',
            'quantidade' => '50',
            'quantidade_minima' => '1',
            'created_at' => Carbon::now(),
            'updated_at' => Carbon::now(),
        ]);
    }
}
