%! homem no lado esquerdo

pareamento([h,l,o,a,r],[l,a,r,h,o]).

pareamento([h,l,o,r,a],[l,a,r,h,o]).
pareamento([h,l,o,r,a],[o,a,r,l,a]).

pareamento([h,l,a,r,o],[l,a,r,h,o]).
pareamento([h,l,a,r,o],[a,r,h,l,o]).
pareamento([h,l,a,r,o],[l,r,h,o,a]).

pareamento([h,o,a,r,l],[a,r,h,l,o]).
pareamento([h,o,a,r,l],[o,r,h,l,a]).

pareamento([h,o,r,l,a],[r,h,l,o,a]).


%! homem no lado direito

pareamento([a,r,h,l,o],[h,o,a,r,l]).
pareamento([a,r,h,l,o],[h,l,a,r,o]).

pareamento([o,r,h,l,a],[h,o,r,l,a]).
pareamento([o,r,h,l,a],[h,l,o,r,a]).
pareamento([o,r,h,l,a],[h,o,a,r,l]).

pareamento([l,r,h,o,a],[h,l,o,r,a]).
pareamento([l,r,h,o,a],[h,l,a,r,o]).

pareamento([l,a,r,h,o],[h,l,a,r,o]).

/* Caso base - solucao trivial */
travessia([r,h,l,o,a],_,[[r,h,l,o,a]]).

travessia(X,Nos_Visitados,Sol) :-
  pareamento(X,Y),
  not(member(Y,Nos_Visitados)),
  travessia(Y,[X|Nos_Visitados],Sol1),
  append([X],Sol1,Sol).
  
imprimir([]).
imprimir([H|T]) :-
  write(H), nl,
  imprimir(T).

/*
   clausula principal
*/
  
game :-
  travessia([h,l,o,a,r],[],Sol),
  imprimir(Sol).
