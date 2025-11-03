#include <stdio.h>
#include "stack.h"

void push(STACK *sp, NODE *np) {
// your code
    NODE *curr = sp->top;

    if(curr != NULL) {
        np->next = curr;
        sp->top = np;
    } else {
        sp->top = np;
    }
    sp->length++;

}

NODE *pop(STACK *sp) {
// your code
    NODE *node_to_free = sp->top;
    sp->top = node_to_free->next;

    node_to_free->next = NULL;

    sp->length--;
    return node_to_free;

}

void clean_stack(STACK *sp) {
  clean(&(sp->top));
  sp->top = NULL;
  sp->length=0;
}