# Grupo 10 - Delivery © 2016

## INTRODUÇÃO
Essa é uma aplicação desenvolvida por alunos do curso de Sistemas de Informação para a matéria de Banco de Dados ofertada pelo Departamento de Computação - UFS no período 2016.1.

## PROFESSOR
André Britto de Carvalho.

## REQUISITOS DA APLICAÇÃO
* Utilizar um sistema operacional Linux ou Windows.
* Possuir conexão com um SGBD PostgreSQL que contenha um banco de dados com o schema `grupo10_delivery` criado.
* Rodar, dentro do schema criado, os scripts de criação e população da aplicação do Delivery.

## EXECUTANDO A APLICAÇÃO

### No Windows
1. Copie o arquivo `grupo10_delivery.jar` para algum diretório que você tenha permissões;
2. Dê dois cliques no arquivo *.jar para executar a aplicação.

### No Linux
1. Copie o arquivo `grupo10_delivery.jar` para algum diretório que você tenha permissões;
2. Abra o terminal e navegue até esse diretório;
3. Estando no mesmo diretório do arquivo *.jar, digite o seguinte comando no terminal: `java -jar grupo10_delivery.jar`;
4. Aguarde a aplicação executar;

## UTILIZANDO A APLICAÇÃO
1. Na aba "Conexão", informe os seguintes dados: Host, Database, Usuário e Senha.
	* _OBS_: A database deve ser a mesma que contenha o schema criado anteriormente.
2. Clique em "Conectar".
3. Caso os dados estejam corretos aparecerá um aviso de "Conexão Estabelecida", basta confirmar clicando em "OK". Caso contrário, certifique-se que você digitou corretamente os dados solicitados anteriormente e tente novamente.
	* _OBS_: Em caso de erro, o motivo do mesmo será exibido em um alerta.
4. Clique na aba "Consultas".
5. No item "Consulta" selecione qual consulta deseja realizar.
6. Para toda consulta selecionada será exibida uma tabela dentro do quadro "Resultados". Movimente a barra de rolagem para ver todas as linhas da tabela (caso seja necessário).
7. Para encerrar a aplicação basta fechar a janela.

## DESENVOLVEDORES
* Claudson Martins [claudsonbms@dcomp.ufs.br].
* Edgar Lima [edgarvln@dcomp.ufs.br].
* Guilherme Boroni [guilhermebp@dcomp.ufs.br].
