<div class="col-xs-12"><legend>Dados Pessoais</legend></div>

        <div class="form-group col-md-5 @if($errors->first('nome')) has-error @endif">
            {!! Form::label('nome', 'Nome do Paciente') !!}
            {!! Form::text('nome', null, ['class' => 'form-control', 'required' => 'required']) !!}
            <small class="text-danger">{{ $errors->first('nome') }}</small>
        </div>