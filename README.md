# Sistemas de Informação

Pequeno software em PHP para o projeto da disciplina de SI.

## Schema do Banco de Dados

![Banco de Dados Bitesbar](/analysis/bitesbar_schema.png)

## Executando o Projeto

### Requerimentos

Você precisará de um servidor tal como XAMP, WAMP, LAMP, MAMP com as seguintes 
extensões ativas:

* PHP >= 5.6.4
* OpenSSL PHP Extension
* PDO PHP Extension
* Mbstring PHP Extension
* Tokenizer PHP Extension
* XML PHP Extension
* Module Rewrite

O Laravel utiliza [Composer](https://getcomposer.org/) para resolver as 
dependências, portanto o tenha instalado em sua máquina.

### Instalando Para a Vitória (FTW)!

1. Faça o download do *.zip deste repositório ou use `git clone` e coloque a 
pasta `laravel` no diretório público do seu servidor web e navegue até ela;
2. Renomeie o arquivo `.env.example` para `.env` e altere as configurações 
abaixo com os seus parâmetros. Geralmente você precisará editar somente os 
seguintes itens:
    - APP_URL
    - DB_DATABASE
    - DB_USERNAME
    - DB_PASSWORD
3. Crie um banco de dados no seu SGBD com o mesmo nome informado no passo 
anterior e defina a codificação de caracteres como `utf8mb4_unicode_ci`
4. Execute o comando `composer install` ou `php composer.phar install`
5. Execute o comando `php artisan key:generate`
6. Execute o comando `php artisan migrate --seed`
7. Execute o seu servidor web.
8. *Opcional:* Se preferir, sirva diretamente do laravel executando o comando 
`php artisan serve`.
9. Acesse a URL da sua aplicação.
    - *PS*: A pasta raíz do projeto é a `laravel/public`, sendo necessário 
    adicionar /public ao final da URL, mas você pode configurar seu servidor 
    web para apontar automaticamente para esta pasta, não necessitando do 
    /public ao final da URL.