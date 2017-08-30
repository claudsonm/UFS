# Sistemas Distribuídos
Este repositório contém os algoritmos desenvolvidos para a disciplina SD.

## Chat Full Duplex

### Chat 1

In -> Reciever

Out -> Sender

### Chat 2
In -> Reciever

Out -> Sender

```
Scanner sc = new Scanner(System.in);
String msg = sc.nextLine();
```

## Projeto da Disciplina

### Comandos Disponíveis

`@<usuario>` - Direciona as mensagens para o usuário informado.
`#<grupo>` - Direciona as mensagem para o grupo informado.
`!addGroup <grupo>` - Cria um novo grupo com o nome informado (automaticamente quem criou o grupo será adicionado a ele).
`!addUser <usuario> <grupo>` - Adiciona um usuário ao grupo.
`!delUser <usuario> <grupo>` - Remove um usuário do grupo.
`!delGroup <grupo>` - Remove o grupo informado.

### Exemplo de Uso

```
// Terminal de Alice
User: alice
>> @bob
bob >> Olá Bob
bob >>
bob diz: Eae chegada?
bob >> @chris
chris >> 
bob (ufs) diz: Olá, pessoal!
```

```
// Terminal de Bob
User: bob
>> 
alice diz: Olá Bob
>> @alice
alice >> Eae chegada?

// Comandos de grupo
alice >> !addGroup ufs
alice >> !addUser alice ufs
alice >> !addUser chris ufs
alice >> !delUser chris ufs
alice >> !delGroup ufs
alice >> #ufs
ufs* >> Olá, pessoal!
```