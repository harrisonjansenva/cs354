#!/bin/gprolog --consult-file

:- include('data.pl').

% Your code goes here.
meetone(Person, MeetingSlot) :-
	free(Person, FreeSlot),
	overlap(FreeSlot, MeetingSlot).

overlap(slot(start1, end1), slot(start2, end2)) :-
	startAvailability :- (lte(time(start1), time(start2))),
	endAvailability :- (lte(time(end1), time(end2)));

lte(time1(h1, m1, d1), time2(h2, m2, d2)) :-
	
	


	







main :- findall(Person,
		meetone(Person,slot(time(8,30,am),time(8,45,am))),
		People),
	write(People), nl, halt.

:- initialization(main).
