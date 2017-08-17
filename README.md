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
```

```
// Terminal de Bob
User: bob
>> 
alice diz: Olá Bob
>> @alice
alice >> Eae chegada?
alice >> 
```