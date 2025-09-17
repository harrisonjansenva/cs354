CFLAGS := -Wall -Wextra
all: main libhack.so

main: libgreet.so main.o
	$(CC) main.o -o main -L./ -lgreet

libgreet.so: greet.o
	$(CC) -fPIC -shared greet.o -o libgreet.so

libhack.so: hack.o
	$(CC) -fPIC -shared hack.o -o libhack.so

.PHONY: clean
clean:
	rm -fr *.o *.so main
