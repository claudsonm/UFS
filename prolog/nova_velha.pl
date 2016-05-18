% Baseado no livro Tecnicas de Inteligencia Artificial para Programacao de Jogos do autor Eloi L. Favero.

velha(X, Sol) :-
    nth1(10, X, Jogador), !,
    lista_sem_ultimo(X, T),
    escolheMov(T, Jogador, Sol), !.

% Jogada do jogador BOLA
escolheMov(T, o, Ntab) :-
    ameaca(T,bola,W),!,move(W,T,o,Ntab),! %% vence
    ; ameaca(T,cruz,W),!,move(W,T,o,Ntab),! %% defesa
    ; vazia(5,T),move(5,T,o,Ntab),!
    ; bola(5,T),(cruz(1,T),cruz(9,T);cruz(3,T),cruz(7,T)),chute(9,W),member(W,[2,4,6,8]),vazia(W,T),move(W,T,o,Ntab),!
    ; chute(10,W),member(W,[1,3,7,9]),vazia(W,T),move(W,T,o,Ntab),!
    ; chute(10,W),member(W,[2,4,6,8]),vazia(W,T),move(W,T,o,Ntab),!.

% Jogada do jogador CRUZ
escolheMov(T, x, Ntab) :-
    ameaca(T,cruz,W),!,move(W,T,x,Ntab),! %% vence
    ; ameaca(T,bola,W),!,move(W,T,x,Ntab),! %% defesa
    ; vazia(5,T),move(5,T,x,Ntab),!
    ; cruz(5,T),(bola(1,T),bola(9,T);bola(3,T),bola(7,T)),chute(9,W),member(W,[2,4,6,8]),vazia(W,T),move(W,T,x,Ntab),!
    ; chute(10,W),member(W,[1,3,7,9]),vazia(W,T),move(W,T,x,Ntab),!
    ; chute(10,W),member(W,[2,4,6,8]),vazia(W,T),move(W,T,x,Ntab),!.

% Numero aleatorio
chute(N,S) :- repeat, S is random(N).

% Metodos de comparacao
move(N, Tab, C, Ntab) :- muda(Tab, N, C, Ntab), !.
vazia(N,Tab) :- nth1(N, Tab, X), X == v.
bola(N,Tab) :- nth1(N, Tab, X), X == o.
cruz(N,Tab) :- nth1(N, Tab, X), X == x.


% Verifica jogadas ameacadas
ameaca(Tab,CB,W) :- emlinha3(Pos),ameaca(CB,Pos,Tab,W),!.
ameaca(cruz,[A,B,C],T,A) :- vazia(A,T),cruz(B,T),cruz(C,T).
ameaca(cruz,[A,B,C],T,B) :- vazia(B,T),cruz(A,T),cruz(C,T).
ameaca(cruz,[A,B,C],T,C) :- vazia(C,T),cruz(A,T),cruz(B,T).
ameaca(bola,[A,B,C],T,A) :- vazia(A,T),bola(B,T),bola(C,T).
ameaca(bola,[A,B,C],T,B) :- vazia(B,T),bola(A,T),bola(C,T).
ameaca(bola,[A,B,C],T,C) :- vazia(C,T),bola(A,T),bola(B,T).


% Verifica se o jogo pode terminar
emlinha3([1,2,3]).
emlinha3([4,5,6]).
emlinha3([7,8,9]).
emlinha3([1,4,7]).
emlinha3([2,5,8]).
emlinha3([3,6,9]).
emlinha3([1,5,9]).
emlinha3([3,5,7]).

% Separacao da lista
lista_sem_ultimo([X|Xs], Ys) :- lista_sem_ultimo_ant(Xs, Ys, X).
lista_sem_ultimo_ant([], [], _).
lista_sem_ultimo_ant([X1|Xs], [X0|Ys], X0) :- lista_sem_ultimo_ant(Xs, Ys, X1).

% Substitui elemento da lista
muda([_|T], 1, X, [X|T]).
muda([H|T], I, X, [H|R]):- I > -1, NI is I-1, muda(T, NI, X, R), !.
muda(L, _, _, L).

% Desenha o jogo da velha na tela
wrtLinha(X,Y,Z,T):-     
    nth1(X,T,V1), wVal(V1),write('|'),
    nth1(Y,T,V2), wVal(V2),write('|'),
    nth1(Z,T,V3), wVal(V3),nl.

wVal(X):- var(X)->write(' ');write(X).

desenha(T) :-
    nl, tab(7),wrtLinha(1,2,3,T), tab(7),write('------'),nl,
    tab(7),wrtLinha(4,5,6,T), tab(7),write('------'),nl,
    tab(7),wrtLinha(7,8,9,T).