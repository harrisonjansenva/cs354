#!/bin/gprolog --consult-file

:- include('data.pl').
:- include('uniq.pl').


people([ann,bob,carla]).
% Create minutes conversion rules, one for 12 AM, one for the rest of AM, one for 12 PM, one for the rest of PM.
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

% Check if time is less than or equal to other time
lte(Time1, Time2) :-
	to_minutes(Time1, Result1), 
	to_minutes(Time2, Result2), 
	Result1 =< Result2.

% Find if Time1 or Time2 starts first
start(Time1, Time2, Time2) :-
	lte(Time1, Time2).

start(Time1, Time2, Time1) :- 
	lte(Time2, Time1).

% Find if Time1 or Time2 ends first
end(Time1, Time2, Time2) :-
	lte(Time2, Time1).

end(Time1, Time2, Time1) :- 
	lte(Time1, Time2).

%find overlap in times and return the slot that it overlaps
overlap(slot(Start1, End1), slot(Start2, End2), slot(Overlap1, Overlap2)) :-
	start(Start1, Start2, Overlap1),
	end(End1, End2, Overlap2),
	lte(Overlap1, Overlap2),
	Overlap1 \== Overlap2.

% find common slots, if only one person, return (base case).
common_slots([People], Slot) :-
    free(People, Slot).

% Find common slots, check after return from base case for overlapping slots.
common_slots([Head | Tail], Slot) :-
    common_slots(Tail, TailSlot),
    free(Head, HeadSlot),
    overlap(HeadSlot, TailSlot, Slot).

%Meet if we have people and have common slots between people
meet(Slot) :-
    people(People),
    common_slots(People, Slot).

main :- findall(Slot, meet(Slot), Slots),
        uniq(Slots, Uniq),
        write(Uniq), nl, halt.

:- initialization(main).
