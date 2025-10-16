#include <stdio.h>
#include <stdlib.h>
#include "bigint.h"

BIGINT bigint(char *p) {
  BIGINT bn = {0};
  if (p == NULL) 
    return bn;
  else if (!(*p >= '0' && *p <= '9')) {// not begin with digits 
    return bn;
  }
  else if (*p == '0' && *(p+1) == '\0') {// just "0"
    dll_insert_end(&bn, dll_node(*p -'0'));
    return bn;
  }  
  else { 
    while (*p) {
      if (*p >= '0' && *p <= '9' ){
        dll_insert_end(&bn, dll_node(*p -'0'));
      } else {
        dll_clean(&bn);
        break;
      }
      p++;
    }
    return bn;
  }
}

BIGINT bigint_add(BIGINT op1, BIGINT op2) {
// your code
BIGINT sum = {0, NULL, NULL};

NODE *p = op1.end;  // start from least significant digit (end)
NODE *q = op2.end;

int carry = 0;

// continue until both operands are exhausted and carry is zero
while (p != NULL || q != NULL || carry != 0) {
    int d1 = (p != NULL) ? p->data - '0' : 0;
    int d2 = (q != NULL) ? q->data - '0' : 0;

    int total = d1 + d2 + carry;
    carry = total / 10;
    int digit = total % 10;

    // create node for resulting digit
    NODE *np = dll_node(digit + '0');
    dll_insert_start(&sum, np);  // insert at front since we build from LSD to MSD

    if (p) p = p->prev;
    if (q) q = q->prev;
}

return sum;

}

BIGINT bigint_fibonacci(int n) {
// your code
  if (n == 0)
    return bigint("0");
  if (n == 1)
    return bigint("1");

  BIGINT f0 = bigint("0");  // Fibonacci(0)
  BIGINT f1 = bigint("1");  // Fibonacci(1)
  BIGINT fn;                // Fibonacci(n)

  for (int i = 2; i <= n; i++) {
    fn = bigint_add(f0, f1);  // fn = f0 + f1

    // move to next pair
    dll_clean(&f0);
    f0 = f1;
    f1 = fn;
  }

  // free remaining old list
  dll_clean(&f0);

  return f1;  // return Fibonacci(n)

}