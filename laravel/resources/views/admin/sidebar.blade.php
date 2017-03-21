<div class="col-md-3">
    <div class="panel panel-default panel-flush">
        <div class="panel-heading">
            Sidebar
        </div>

        <div class="panel-body">
            <ul class="nav" role="tablist">
                <li role="presentation"><a href="{{ url('/home') }}">Dashboard</a></li>
                <li><a href="{{ route('clientes.index') }}">Clientes</a></li>
                <li><a href="{{ route('promocoes.index') }}">Promocoes</a></li>
                <li><a href="{{ route('fornecedores.index') }}">Fornecedores</a></li>
                <li><a href="{{ route('produtos.index') }}">Produtos</a></li>
                <li><a href="{{ route('compras.index') }}">Compras</a></li>
                <li><a href="{{ route('vendas.index') }}">Vendas</a></li>
                <li><a href="{{ route('usuarios.index') }}">Usuários</a></li>
            </ul>
        </div>
    </div>
</div>
