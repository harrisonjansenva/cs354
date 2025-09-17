#include <stdio.h>

unsigned int gcd(unsigned int, unsigned int);

int main() {
  unsigned int a, b;
  printf("Enter a number> ");
  scanf("%u", &a);

  printf("Enter a number> ");
  scanf("%u", &b);

  unsigned int result = gcd(a, b);
  printf("gcd(%u, %u) = %u.\n", a, b, result);
}
