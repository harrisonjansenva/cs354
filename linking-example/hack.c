#include <stdio.h>

int printf(const char *restrict format, ...) {
  puts("HACKED!");
  return 0;
}
