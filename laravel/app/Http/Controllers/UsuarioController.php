<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Requests;
use App\Phone;
use App\User;
use Illuminate\Http\Request;
use Session;

class UsuarioController extends Controller
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
            $usuarios = User::where('name', 'LIKE', "%$keyword%")
                ->orWhere('email', 'LIKE', "%$keyword%")
                
                ->paginate($perPage);
        } else {
            $usuarios = User::paginate($perPage);
        }

        return view('usuarios.index', compact('usuarios'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        return view('usuarios.create');
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
        $requestData['password'] = bcrypt($requestData['password']);
        
        $usuario = User::create($requestData);
        foreach ($requestData['telefone'] as $numero) {
            if (! (is_null($numero) || empty($numero))) {
                $tel = new Phone;
                $tel->telefone = $numero;
                $tel->user()->associate($usuario);
                $tel->save();
            }
        }

        Session::flash('flash_message', 'User added!');

        return redirect('usuarios');
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
        $usuario = User::findOrFail($id);

        return view('usuarios.show', compact('usuario'));
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
        $usuario = User::with('phones')->findOrFail($id);

        return view('usuarios.edit', compact('usuario'));
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
        if (empty($requestData['password']) || is_null($requestData['password'])) {
            $requestData = array_except($requestData, ['password']);
        }
        else {
            $requestData['password'] = bcrypt($requestData['password']);
        }
        
        $usuario = User::findOrFail($id);
        $usuario->update($requestData);
        $usuario->phones()->delete();
        foreach ($requestData['telefone'] as $numero) {
            if (! (is_null($numero) || empty($numero))) {
                $tel = new Phone;
                $tel->telefone = $numero;
                $tel->user()->associate($usuario);
                $tel->save();
            }
        }

        Session::flash('flash_message', 'User updated!');

        return redirect('usuarios');
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
        User::destroy($id);

        Session::flash('flash_message', 'User deleted!');

        return redirect('usuarios');
    }
}
