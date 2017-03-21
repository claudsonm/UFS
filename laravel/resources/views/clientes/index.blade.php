@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a class="btn btn-default" href="{{ route('clientes.create') }}" role="button">Novo Cliente</a>
                </div>

                <div class="panel-body">
                    <table class="table table-condensed table-hover">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Cidade</th>
                            </tr>
                        </thead>
                        <tbody>
                        @foreach ($clientes as $c)
                            <tr>
                                <td>{{ $c->nome }}</td>
                                <td>{{ $c->cidade }}</td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
@stop