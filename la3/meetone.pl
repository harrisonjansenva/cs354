#!/bin/gprolog --consult-file

:- include('data.pl').

% Your code goes here.

to_minutes(time(12, Min, am), Total) :- 
	Total is Min.

to_minutes(time(H, Min, am), Total) :- 
	H \== 12,
	Total is (H * 60) + Min.

to_minutes(time(12, Min, pm ), Total) :-
	Total is (12 * 60) + Min.

to_minutes(time(H, Min, pm), Total) :-
	H \== 12,
	Total is (H + 12) * 60 + Min.

lte(Time1, Time2) :-
	to_minutes(Time1, Result1), 
	to_minutes(Time2, Result2), 
	Result1 =< Result2.

start(Time1, Time2, Time2) :-
	lte(Time1, Time2).

start(Time1, Time2, Time1) :- 
	lte(Time2, Time1).

end_earliest(Time1, Time2, Time2) :-
	lte(Time2, Time1).

end_earliest(Time1, Time2, Time1) :- 
	lte(Time1, Time2).

overlap(slot(Start1, End1), slot(Start2, End2), slot(Overlap1, Overlap2)) :-
	start(Start1, Start2, Overlap1),
	end_earliest(End1, End2, Overlap2),
	lte(Overlap1, Overlap2),
	Overlap1 \== Overlap2.

meetone(Person, MeetingSlot) :-
	free(Person, FreeSlot),
	overlap(FreeSlot, MeetingSlot, _).


main :- findall(Person,
		meetone(Person,slot(time(8,30,am),time(8,45,am))),
		People),
	write(People), nl, halt.

:- initialization(main).
