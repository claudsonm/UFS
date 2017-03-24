<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Auth::routes();

Route::get('/home', 'HomeController@index');
Route::resource('clientes', 'CustomerController');
Route::resource('promocoes', 'PromotionController');
Route::resource('fornecedores', 'ProviderController');
Route::resource('produtos', 'ProductController');
Route::resource('compras', 'PurchaseController');
Route::resource('vendas', 'SaleController');
Route::resource('usuarios', 'UsuarioController');
Route::get('produtos/{id}/preco', 'ProductController@mostrarPreco')->name('produtos.preco');