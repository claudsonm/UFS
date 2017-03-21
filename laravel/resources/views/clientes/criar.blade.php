@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Cadastro
                </div>

                <div class="panel-body">
                    {!! Form::open(['route' => 'clientes.index', 'role' => 'form']) !!}
                    @include('clientes.form', ['textoSubmit' => 'Cadastrar Cliente'])
                    {{ Form::close() }}
                </div>
            </div>
        </div>
    </div>
</div>
@stop