velha(X, Sol) :-
    nth1(10, X, Jogador), !,
    lista_sem_ultimo(X, T),
    realizaJogada(T, Jogador, T2), !,
    proximo(Jogador, Oponente),
    append(T2, [Oponente], Sol).


proximo(o,x).
proximo(x,o).


% Jogador ( BOLA )
realizaJogada(T, o, Ntab) :-
    ameaca(T,bola,W),!,move(W,T,o,Ntab),! %% tenta vencer
    ; ameaca(T,cruz,W),!,move(W,T,o,Ntab),! %% se defende
    ; vazia(5,T),move(5,T,o,Ntab),!
    ; chute(10,W),member(W,[1,3,7,9]),vazia(W,T),move(W,T,o,Ntab),!
    ; chute(10,W),member(W,[2,4,6,8]),vazia(W,T),move(W,T,o,Ntab),!.

% Jogador ( CRUZ )
realizaJogada(T, x, Ntab) :-
    ameaca(T,cruz,W),!,move(W,T,x,Ntab),! %% vence
    ; ameaca(T,bola,W),!,move(W,T,x,Ntab),! %% defesa
    ; vazia(5,T),move(5,T,x,Ntab),!
    ; chute(10,W),member(W,[1,3,7,9]),vazia(W,T),move(W,T,x,Ntab),!
    ; chute(10,W),member(W,[2,4,6,8]),vazia(W,T),move(W,T,x,Ntab),!.

% chute(N,S):- repeat, random(0,N,S).
chute(N,S) :- repeat, S is random(N).


% move(+Index, +List, +Value, -NewList)
move(N, Tab, C, Ntab) :- replace(Tab, N, C, Ntab), !.
% vazia(+Index, +List)
vazia(N,Tab) :- nth1(N, Tab, X), X == v.
% bola(+Index, +List)
bola(N,Tab) :- nth1(N, Tab, X), X == o.
% cruz(+Index, +List)
cruz(N,Tab) :- nth1(N, Tab, X), X == x.


% Verifica se um determinado jogador esta ameacado
ameaca(Tab,CB,W) :- emlinha3(Pos),ameaca(CB,Pos,Tab,W),!.
%%
ameaca(cruz,[A,B,C],T,A) :- vazia(A,T),cruz(B,T),cruz(C,T).
ameaca(cruz,[A,B,C],T,B) :- vazia(B,T),cruz(A,T),cruz(C,T).
ameaca(cruz,[A,B,C],T,C) :- vazia(C,T),cruz(A,T),cruz(B,T).
%%
ameaca(bola,[A,B,C],T,A) :- vazia(A,T),bola(B,T),bola(C,T).
ameaca(bola,[A,B,C],T,B) :- vazia(B,T),bola(A,T),bola(C,T).
ameaca(bola,[A,B,C],T,C) :- vazia(C,T),bola(A,T),bola(B,T).


% Situacoes em que o jogo termina
emlinha3([1,2,3]). %% horiz
emlinha3([4,5,6]).
emlinha3([7,8,9]).
emlinha3([1,4,7]). %% vert
emlinha3([2,5,8]).
emlinha3([3,6,9]).
emlinha3([1,5,9]). %% diag
emlinha3([3,5,7]).


% Obtem uma lista sem o ultimo elemento
lista_sem_ultimo([X|Xs], Ys) :-
    lista_sem_ultimo_ant(Xs, Ys, X).
lista_sem_ultimo_ant([], [], _).
lista_sem_ultimo_ant([X1|Xs], [X0|Ys], X0) :-
    lista_sem_ultimo_ant(Xs, Ys, X1).


% Substitui um elemento da lista
% replace(+List,+Index,+Value,-NewList).
replace([_|T], 1, X, [X|T]).
replace([H|T], I, X, [H|R]):- I > -1, NI is I-1, replace(T, NI, X, R), !.
replace(L, _, _, L).