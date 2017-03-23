<div class="form-group {{ $errors->has('nome') ? 'has-error' : ''}}">
    {!! Form::label('nome', 'Nome', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('nome', null, ['class' => 'form-control', 'required']) !!}
        {!! $errors->first('nome', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('descricao') ? 'has-error' : ''}}">
    {!! Form::label('descricao', 'Descricao', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::textarea('descricao', null, ['class' => 'form-control']) !!}
        {!! $errors->first('descricao', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('validade') ? 'has-error' : ''}}">
    {!! Form::label('validade', 'Validade', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::date('validade', (isset($produto) && !is_null($produto->validade)) ? $produto->validade->format('Y-m-d') : null, ['class' => 'form-control']) !!}
        {!! $errors->first('validade', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('preco') ? 'has-error' : ''}}">
    {!! Form::label('preco', 'Preço de Venda', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('preco', null, ['class' => 'form-control']) !!}
        {!! $errors->first('preco', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('quantidade') ? 'has-error' : ''}}">
    {!! Form::label('quantidade', 'Quantidade', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('quantidade', null, ['class' => 'form-control', 'required']) !!}
        {!! $errors->first('quantidade', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('quantidade_minima') ? 'has-error' : ''}}">
    {!! Form::label('quantidade_minima', 'Quantidade Minima', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('quantidade_minima', null, ['class' => 'form-control']) !!}
        {!! $errors->first('quantidade_minima', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('provider_id') ? 'has-error' : ''}}">
    {!! Form::label('provider_id', 'Fornecedores', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::select('provider_id', $fornecedores, null, ['class' => 'form-control']) !!}
        {!! $errors->first('provider_id', '<p class="help-block">:message</p>') !!}
    </div>
</div>

<div class="form-group">
    <div class="col-md-offset-4 col-md-4">
        {!! Form::submit(isset($submitButtonText) ? $submitButtonText : 'Create', ['class' => 'btn btn-primary']) !!}
    </div>
</div>
