# Note: To build gcd.s as a stand-alone binary, do this:
#   $ as -o gcd.o gcd.s
#   $ ld gcd.o -o gcd -lc
SOURCES := $(wildcard *.c *.s)
OBJECTS = $(SOURCES:.c=.o)
OBJECTS := $(OBJECTS:.s=.o)

main: $(OBJECTS)

.PHONY: clean
clean:
	rm -f *.o main
