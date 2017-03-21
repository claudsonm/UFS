<div class="form-group {{ $errors->has('cnpj') ? 'has-error' : ''}}">
    {!! Form::label('cnpj', 'Cnpj', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('cnpj', null, ['class' => 'form-control']) !!}
        {!! $errors->first('cnpj', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('razao_social') ? 'has-error' : ''}}">
    {!! Form::label('razao_social', 'Razao Social', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('razao_social', null, ['class' => 'form-control']) !!}
        {!! $errors->first('razao_social', '<p class="help-block">:message</p>') !!}
    </div>
</div>

<div class="form-group">
    <div class="col-md-offset-4 col-md-4">
        {!! Form::submit(isset($submitButtonText) ? $submitButtonText : 'Create', ['class' => 'btn btn-primary']) !!}
    </div>
</div>
