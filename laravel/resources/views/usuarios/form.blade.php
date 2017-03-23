<div class="form-group {{ $errors->has('name') ? 'has-error' : ''}}">
    {!! Form::label('name', 'Name', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('name', null, ['class' => 'form-control']) !!}
        {!! $errors->first('name', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('email') ? 'has-error' : ''}}">
    {!! Form::label('email', 'Email', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::email('email', null, ['class' => 'form-control']) !!}
        {!! $errors->first('email', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('password') ? 'has-error' : ''}}">
    {!! Form::label('password', 'Password', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::password('password', ['class' => 'form-control']) !!}
        {!! $errors->first('password', '<p class="help-block">:message</p>') !!}
    </div>
</div>

<div class="form-group {{ $errors->has('gerente') ? ' has-error' : '' }}">
    <label for="gerente" class="col-md-4 control-label">Gerente</label>
        <div class="col-md-6">        
            <label>
                {!! Form::radio('gerente', 1,  null, []) !!} Sim
            </label> 
            <label>
                {!! Form::radio('gerente', 0,  true, []) !!} Não
            </label>
            <small class="text-danger">{{ $errors->first('gerente') }}</small>
        </div>
</div>

<div class="form-group {{ $errors->has('cpf') ? 'has-error' : ''}}">
    {!! Form::label('cpf', 'Cpf', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('cpf', null, ['class' => 'form-control']) !!}
        {!! $errors->first('cpf', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('ctps') ? 'has-error' : ''}}">
    {!! Form::label('ctps', 'Ctps', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::text('ctps', null, ['class' => 'form-control']) !!}
        {!! $errors->first('ctps', '<p class="help-block">:message</p>') !!}
    </div>
</div><div class="form-group {{ $errors->has('data_nascimento') ? 'has-error' : ''}}">
    {!! Form::label('data_nascimento', 'Data Nascimento', ['class' => 'col-md-4 control-label']) !!}
    <div class="col-md-6">
        {!! Form::date('data_nascimento', (isset($usuario) && !is_null($usuario->data_nascimento)) ? $usuario->data_nascimento->format('Y-m-d') : null, ['class' => 'form-control']) !!}
        {!! $errors->first('data_nascimento', '<p class="help-block">:message</p>') !!}
    </div>
</div>

<div id="lista-telefones">
    <div class="form-group">
        <div class="col-md-6 col-md-offset-4">
            <button type="button" id="btnAdd-tel" class="btn btn-success">Adicionar Telefone</button>
        </div>
    </div>
    
    {{-- SE É UM CADASTRO OU UMA EDIÇÃO DE UM USUÁRIO SEM TELEFONE --}}
    @if (! (isset($usuario) && $usuario->phones->count()))
        <div class="group form-group">
            <label class="col-md-4 control-label">Telefone</label>
            <div class="col-md-5">
                {!! Form::text('telefone[]', null, ['class' => 'form-control']) !!}
            </div>
            <div class="col-md-3">
                <button type="button" class="btn btn-danger btnRemove">-</button>
            </div>
        </div>
    @else
        {{-- MOSTRA TODOS OS NÚMEROS QUE O USUÁRIO POSSUI --}}
        @foreach ($usuario->phones as $numero)
            <div class="group form-group">
                <label class="col-md-4 control-label">Telefone</label>
                <div class="col-md-5">
                    {!! Form::text('telefone[]', $numero->telefone, ['class' => 'form-control']) !!}
                </div>
                <div class="col-md-3">
                    <button type="button" class="btn btn-danger btnRemove">-</button>
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
