<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Requests;
use App\Product;
use App\Purchase;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Session;

class PurchaseController extends Controller
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
            $compras = Purchase::paginate($perPage);
        } else {
            $compras = Purchase::paginate($perPage);
        }

        return view('compras.index', compact('compras'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        $listaProdutos = Product::pluck('nome', 'id')->all();
        return view('compras.create', compact('listaProdutos'));
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
        $requestData['user_id'] = Auth::id();
        $requestData['quantidade'] = str_replace(',', '.', $requestData['quantidade']);
        $requestData['preco'] = str_replace(',', '.', $requestData['preco']);
        $requestData['valor'] = 0;

        $numeroProdutos = count($requestData['produto']);
        $dadosJuncao = array();
        $precos = array();
        for ($i=0; $i < $numeroProdutos; $i++) { 
            // Gera os dados para inserção na tabela de junção
            $dadosJuncao[] = [
                'quantidade' => $requestData['quantidade'][$i],
                'preco' => $requestData['preco'][$i]
            ];
            // Calcula o valor total da compra
            $qnt = (float) $requestData['quantidade'][$i];
            $requestData['valor'] += ((float) $requestData['preco'][$i]) * $qnt;
            // Incremente a quantidade em estoque do produto
            $p = Product::find($requestData['produto'][$i]);
            $qntAtual = (float) (str_replace(',', '.', $p->quantidade));
            $p->quantidade = $qntAtual + $qnt;
            $p->save();
        }
        $produtosCompra = array_combine($requestData['produto'], $dadosJuncao);

        $compra = Purchase::create($requestData);
        $compra->products()->sync($produtosCompra);

        Session::flash('flash_message', 'Purchase added!');

        return redirect('compras');
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
        $compra = Purchase::findOrFail($id);

        return view('compras.show', compact('compra'));
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
        $compra = Purchase::with('products')->findOrFail($id);
        $listaProdutos = Product::pluck('nome', 'id')->all();

        return view('compras.edit', compact('compra', 'listaProdutos'));
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
        
        $compra = Purchase::findOrFail($id);
        $compra->update($requestData);

        Session::flash('flash_message', 'Purchase updated!');

        return redirect('compras');
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
        Purchase::destroy($id);

        Session::flash('flash_message', 'Purchase deleted!');

        return redirect('compras');
    }
}
