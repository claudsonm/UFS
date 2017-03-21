<?php
/**
 * A helper file for your Eloquent Models
 * Copy the phpDocs from this file to the correct Model,
 * And remove them from this file, to prevent double declarations.
 *
 * @author Barry vd. Heuvel <barryvdh@gmail.com>
 */


namespace App{
/**
 * App\Customer
 *
 * @property int $id
 * @property string $nome
 * @property \Carbon\Carbon $data_nascimento
 * @property string $cpf
 * @property string $cep
 * @property string $logradouro
 * @property int $numero
 * @property string $complemento
 * @property string $bairro
 * @property string $cidade
 * @property string $estado
 * @property \Carbon\Carbon $deleted_at
 * @property \Carbon\Carbon $created_at
 * @property \Carbon\Carbon $updated_at
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Sale[] $sales
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereBairro($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereCep($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereCidade($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereComplemento($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereCpf($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereCreatedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereDataNascimento($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereDeletedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereEstado($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereLogradouro($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereNome($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereNumero($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Customer whereUpdatedAt($value)
 */
	class Customer extends \Eloquent {}
}

namespace App{
/**
 * App\Phone
 *
 * @property string $telefone
 * @property int $user_id
 * @property-read \App\User $user
 * @method static \Illuminate\Database\Query\Builder|\App\Phone whereTelefone($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Phone whereUserId($value)
 */
	class Phone extends \Eloquent {}
}

namespace App{
/**
 * App\Product
 *
 * @property int $id
 * @property string $nome
 * @property string $descricao
 * @property \Carbon\Carbon $validade
 * @property float $preco
 * @property float $quantidade
 * @property float $quantidade_minima
 * @property int $providers_id
 * @property string $deleted_at
 * @property \Carbon\Carbon $created_at
 * @property \Carbon\Carbon $updated_at
 * @property-read \App\Provider $provider
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Purchase[] $purchases
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Sale[] $sales
 * @method static \Illuminate\Database\Query\Builder|\App\Product whereCreatedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Product whereDeletedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Product whereDescricao($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Product whereId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Product whereNome($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Product wherePreco($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Product whereProvidersId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Product whereQuantidade($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Product whereQuantidadeMinima($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Product whereUpdatedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Product whereValidade($value)
 */
	class Product extends \Eloquent {}
}

namespace App{
/**
 * App\Promotion
 *
 * @property int $id
 * @property string $nome
 * @property string $inicio
 * @property string $fim
 * @property float $desconto
 * @property string $tipo
 * @property string $deleted_at
 * @property \Carbon\Carbon $created_at
 * @property \Carbon\Carbon $updated_at
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Sale[] $sales
 * @method static \Illuminate\Database\Query\Builder|\App\Promotion whereCreatedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Promotion whereDeletedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Promotion whereDesconto($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Promotion whereFim($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Promotion whereId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Promotion whereInicio($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Promotion whereNome($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Promotion whereTipo($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Promotion whereUpdatedAt($value)
 */
	class Promotion extends \Eloquent {}
}

namespace App{
/**
 * App\Provider
 *
 * @property int $id
 * @property string $cnpj
 * @property string $razao_social
 * @property \Carbon\Carbon $created_at
 * @property \Carbon\Carbon $updated_at
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Product[] $products
 * @method static \Illuminate\Database\Query\Builder|\App\Provider whereCnpj($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Provider whereCreatedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Provider whereId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Provider whereRazaoSocial($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Provider whereUpdatedAt($value)
 */
	class Provider extends \Eloquent {}
}

namespace App{
/**
 * App\Purchase
 *
 * @property int $id
 * @property string $data
 * @property float $valor
 * @property int $user_id
 * @property string $deleted_at
 * @property \Carbon\Carbon $created_at
 * @property \Carbon\Carbon $updated_at
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Product[] $products
 * @property-read \App\User $user
 * @method static \Illuminate\Database\Query\Builder|\App\Purchase whereCreatedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Purchase whereData($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Purchase whereDeletedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Purchase whereId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Purchase whereUpdatedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Purchase whereUserId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Purchase whereValor($value)
 */
	class Purchase extends \Eloquent {}
}

namespace App{
/**
 * App\Sale
 *
 * @property int $id
 * @property \Carbon\Carbon $data
 * @property float $valor
 * @property string $tipo_pagamento
 * @property int $user_id
 * @property int $promotion_id
 * @property int $customer_id
 * @property string $deleted_at
 * @property \Carbon\Carbon $created_at
 * @property \Carbon\Carbon $updated_at
 * @property-read \App\Customer $customer
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Product[] $products
 * @property-read \App\Promotion $promotion
 * @property-read \App\User $user
 * @method static \Illuminate\Database\Query\Builder|\App\Sale whereCreatedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Sale whereCustomerId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Sale whereData($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Sale whereDeletedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Sale whereId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Sale wherePromotionId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Sale whereTipoPagamento($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Sale whereUpdatedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Sale whereUserId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\Sale whereValor($value)
 */
	class Sale extends \Eloquent {}
}

namespace App{
/**
 * App\User
 *
 * @property int $id
 * @property string $name
 * @property string $email
 * @property string $password
 * @property bool $gerente
 * @property string $cpf
 * @property string $ctps
 * @property \Carbon\Carbon $data_nascimento
 * @property string $remember_token
 * @property string $deleted_at
 * @property \Carbon\Carbon $created_at
 * @property \Carbon\Carbon $updated_at
 * @property-read \Illuminate\Notifications\DatabaseNotificationCollection|\Illuminate\Notifications\DatabaseNotification[] $notifications
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Phone[] $phones
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Purchase[] $purchase
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Sale[] $sales
 * @method static \Illuminate\Database\Query\Builder|\App\User whereCpf($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User whereCreatedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User whereCtps($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User whereDataNascimento($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User whereDeletedAt($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User whereEmail($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User whereGerente($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User whereId($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User whereName($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User wherePassword($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User whereRememberToken($value)
 * @method static \Illuminate\Database\Query\Builder|\App\User whereUpdatedAt($value)
 */
	class User extends \Eloquent {}
}

