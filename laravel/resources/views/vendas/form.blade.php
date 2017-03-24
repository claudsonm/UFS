<div class="form-group {{ $errors->has('customer_id') ? 'has-error' : ''}}">
    {!! Form::label('customer_id', 'Cliente', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::select('customer_id', $listaClientes, null, ['class' => 'form-control']) !!}
        {!! $errors->first('customer_id', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('data') ? 'has-error' : ''}}">
    {!! Form::label('data', 'Data', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::input('datetime-local', 'data', ! isset($venda) ? date('Y-m-d\TH:i:s') : $venda->data->format('Y-m-d\TH:i:s'), ['class' => 'form-control']) !!}
        {!! $errors->first('data', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('valor') ? 'has-error' : ''}}">
    {!! Form::label('valor', 'Valor', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('valor', null, ['class' => 'form-control', 'readonly']) !!}
        {!! $errors->first('valor', '<p class="help-block">:message</p>') !!}
    </div>
</div>
<div class="form-group">
    {!! Form::label('tipo_pagamento', 'Tipo Pagamento', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        <label>
            {!! Form::radio('tipo_pagamento', 'vista',  true, []) !!} À Vista
        </label> 
        <label>
            {!! Form::radio('tipo_pagamento', 'credito',  null, []) !!} Crédito
        </label>
        <label>
            {!! Form::radio('tipo_pagamento', 'debito',  null, []) !!} Débito
        </label>
    </div>
</div>
<div class="form-group">
    {!! Form::label('promotion_id', 'Promoção', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
    @if (count($listaPromocoes))
        {!! Form::select('promotion_id', $listaPromocoes, null, ['id' => 'promotion_id', 'class' => 'form-control']) !!}
    @else
        <p class="text-danger">Nenhuma promoção disponível.</p>
    @endif
    </div>
</div>


<div id="multifield">
    <div class="form-group">
        <div class="col-md-6 col-md-offset-1">
            <button type="button" id="btnAdd" class="btn btn-success">Adicionar Produtos</button>
        </div>
    </div>
    
    {{-- SE É UM CADASTRO OU UMA EDIÇÃO DE UMA VENDA SEM PRODUTOS --}}
    @if (! (isset($venda) && $venda->products->count()))
        <div class="group form-group">
            <div class="col-md-5 col-md-offset-1">
                <label class="control-label">Produto</label>
                {!! Form::select('produto[]', $listaProdutos, 0, ['class' => 'form-control select-venda', 'required' => 'required']) !!}
            </div>
            <div class="col-md-2">
                <label class="control-label" title="Quantidade">Quant.</label>
                {!! Form::text('quantidade[]', null, ['class' => 'form-control', 'required' => 'required']) !!}
            </div>
            <div class="col-md-2">
                <label class="control-label" title="Preço">Preço</label>
                {!! Form::text('preco[]', null, ['class' => 'form-control preco', 'required' => 'required']) !!}
            </div>
            <div class="col-md-1">
                <label class="control-label">&nbsp;</label>
                <button type="button" class="btn btn-danger btnRemove form-control" title="Remover">-</button>
            </div>
        </div>
    @else
        {{-- MOSTRA TODOS OS NÚMEROS QUE O USUÁRIO POSSUI --}}
        @foreach ($venda->products as $item)
            <div class="group form-group">
                <div class="col-md-5 col-md-offset-1">
                    <label class="control-label">Produto</label>
                    {!! Form::select('produto[]', $listaProdutos, $item->id, ['class' => 'form-control select-venda', 'disabled']) !!}
                </div>
                <div class="col-md-2">
                    <label class="control-label" title="Quantidade">Quant.</label>
                    {!! Form::text('quantidade[]', $item->pivot['quantidade'], ['class' => 'form-control', 'disabled']) !!}
                </div>
                <div class="col-md-2">
                    <label class="control-label" title="Preço">Preço</label>
                    {!! Form::text('preco[]', $item->pivot['preco'], ['class' => 'form-control preco', 'disabled']) !!}
                </div>
                <div class="col-md-1">
                    <label class="control-label">&nbsp;</label>
                    <button type="button" class="btn btn-danger btnRemove form-control disabled" title="Remover">-</button>
                </div>
            </div>
        @endforeach
    @endif
</div>

<div class="form-group">
    <div class="col-md-offset-4 col-md-4">
        {!! Form::submit(isset($submitButtonText) ? $submitButtonText : 'Create', ['class' => 'btn btn-primary']) !!}
    </div>
</div>
