<?php

namespace App\Http\Controllers;

use App\Customer;
use App\Http\Controllers\Controller;
use App\Http\Requests;
use App\Product;
use App\Promotion;
use App\Sale;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Session;

class SaleController extends Controller
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
            $vendas = Sale::paginate($perPage);
        } else {
            $vendas = Sale::paginate($perPage);
        }

        return view('vendas.index', compact('vendas'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        $listaClientes = Customer::pluck('nome', 'id');
        $listaProdutos = Product::pluck('nome', 'id')->all();
        $listaProdutos[0] = '';
        $t = Carbon::now();
        $listaPromocoes = Promotion::where('inicio', '<=', $t)
            ->where('fim', '>=', $t)
            ->get()
            ->pluck('nome_promocao', 'id');

        return view('vendas.create', compact('listaClientes', 'listaPromocoes', 'listaProdutos'));
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
            // Reduz a quantidade em estoque do produto
            $p = Product::find($requestData['produto'][$i]);
            $qntAtual = (float) (str_replace(',', '.', $p->quantidade));
            $p->quantidade = $qntAtual - $qnt;
            $p->save();
        }
        $produtosVenda = array_combine($requestData['produto'], $dadosJuncao);
        
        $venda = Sale::create($requestData);
        $venda->products()->sync($produtosVenda);

        Session::flash('flash_message', 'Sale added!');

        return redirect('vendas');
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
        $venda = Sale::findOrFail($id);

        return view('vendas.show', compact('venda'));
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
        $venda = Sale::findOrFail($id);

        $listaClientes = Customer::pluck('nome', 'id');
        $listaProdutos = Product::pluck('nome', 'id')->all();
        $listaProdutos[0] = '';
        $listaPromocoes = $venda->promotion()->get()->pluck('nome_promocao', 'id')->all();

        return view('vendas.edit', compact('venda', 'listaClientes', 'listaProdutos', 'listaPromocoes'));
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
        
        $venda = Sale::findOrFail($id);
        $venda->update($requestData);

        Session::flash('flash_message', 'Sale updated!');

        return redirect('vendas');
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
        Sale::destroy($id);

        Session::flash('flash_message', 'Sale deleted!');

        return redirect('vendas');
    }
}
