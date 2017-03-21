<div class="form-group {{ $errors->has('nome') ? 'has-error' : ''}}">
    {!! Form::label('nome', 'Nome', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('nome', null, ['class' => 'form-control']) !!}
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
        {!! Form::date('validade', null, ['class' => 'form-control']) !!}
        {!! $errors->first('validade', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('preco') ? 'has-error' : ''}}">
    {!! Form::label('preco', 'Preco', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('preco', null, ['class' => 'form-control']) !!}
        {!! $errors->first('preco', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('quantidade') ? 'has-error' : ''}}">
    {!! Form::label('quantidade', 'Quantidade', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::number('quantidade', null, ['class' => 'form-control']) !!}
        {!! $errors->first('quantidade', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('quantidade_minima') ? 'has-error' : ''}}">
    {!! Form::label('quantidade_minima', 'Quantidade Minima', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::number('quantidade_minima', null, ['class' => 'form-control']) !!}
        {!! $errors->first('quantidade_minima', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('providers_id') ? 'has-error' : ''}}">
    {!! Form::label('providers_id', 'Providers Id', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::select('providers_id', ['1', '2', '3'], null, ['class' => 'form-control']) !!}
        {!! $errors->first('providers_id', '<p class="help-block">:message</p>') !!}
    </div>
</div>

<div class="form-group">
    <div class="col-md-offset-4 col-md-4">
        {!! Form::submit(isset($submitButtonText) ? $submitButtonText : 'Create', ['class' => 'btn btn-primary']) !!}
    </div>
</div>
