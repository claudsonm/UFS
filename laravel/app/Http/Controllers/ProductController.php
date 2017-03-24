<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Requests;
use App\Product;
use App\Provider;
use Illuminate\Http\Request;
use Session;

class ProductController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }
    
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\View\View
     */
    public function index(Request $request)
    {
        $keyword = $request->get('search');
        $perPage = 25;

        if (!empty($keyword)) {
            $produtos = Product::with('provider')->paginate($perPage);
        } else {
            $produtos = Product::with('provider')->paginate($perPage);
        }
        
        return view('produtos.index', compact('produtos'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        $fornecedores = Provider::pluck('razao_social', 'id')->all();
        $fornecedores[0] = 'Nenhum';
        
        return view('produtos.create', compact('fornecedores'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     *
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function store(Request $request)
    {
        
        $requestData = $request->all();
        $produto = Product::create($requestData);
        if (! $requestData['provider_id'] == 0){
            $fornecedor = Provider::find($requestData['provider_id']);
            $produto->provider()->associate($fornecedor)->save();
        }

        Session::flash('flash_message', 'Product added!');

        return redirect('produtos');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     *
     * @return \Illuminate\View\View
     */
    public function show($id)
    {
        $produto = Product::findOrFail($id);

        return view('produtos.show', compact('produto'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     *
     * @return \Illuminate\View\View
     */
    public function edit($id)
    {
        $produto = Product::findOrFail($id);
        $fornecedores = Provider::pluck('razao_social', 'id')->all();
        $fornecedores = array_prepend($fornecedores, 'Nenhum');

        return view('produtos.edit', compact('produto', 'fornecedores'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  int  $id
     * @param \Illuminate\Http\Request $request
     *
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function update($id, Request $request)
    {
        $requestData = $request->all();
        
        $produto = Product::findOrFail($id);
        if (! $requestData['provider_id'] == 0) {
            $fornecedor = Provider::find($requestData['provider_id']);
            $produto->provider()->associate($fornecedor)->save();
        }
        else {
            $produto->provider()->dissociate()->save();
        }
        $produto->update($requestData);

        Session::flash('flash_message', 'Product updated!');

        return redirect('produtos');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     *
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function destroy($id)
    {
        Product::destroy($id);

        Session::flash('flash_message', 'Product deleted!');

        return redirect('produtos');
    }

    public function mostrarPreco($id)
    {
        $p = Product::findOrFail($id);
        return $p->preco;
    }
}
