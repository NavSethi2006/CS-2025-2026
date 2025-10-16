 
#include <stdio.h>
#include <stdlib.h> 
#include "dllist.h"

NODE *dll_node(char data) {
// your code
    NODE *newnode = (NODE*) malloc(sizeof(NODE));
    newnode->data = data;
    return newnode;
}

void dll_insert_start(DLL *dllp, NODE *np) {
// your code
    NODE *curr = dllp->start;
    NODE *prev = dllp->end;

    np->next = curr;
    np->prev = prev;

    dllp->start = np;

    dllp->length++;

}

void dll_insert_end(DLL *dllp, NODE *np) {

    NODE *curr = dllp->start;
    NODE *prev = dllp->end;

    np->next = curr;
    np->prev = prev;

    dllp->end = np;
    dllp->length++;

}

void dll_delete_start(DLL *dllp) {
// your code
    NODE* to_delete = dllp->start;

    if(dllp->start->next != NULL) {
        dllp->start = dllp->start->next;
        dllp->start->prev = dllp->end;
    } else {
        dllp->start = NULL;
    }
    dllp->length--;


    free(to_delete);

}

void dll_delete_end(DLL *dllp) {
// your code
    if (dllp == NULL || dllp->end == NULL) {
        return;
    }

    NODE *toDelete = dllp->end;

    // If there’s only one node
    if (dllp->start == dllp->end) {
        dllp->start = dllp->end = NULL;
    } else {
        dllp->end = dllp->end->prev;
        dllp->end->next = NULL;
    }

    free(toDelete);
}

void dll_clean(DLL *dllp) {
// your code
    NODE *temp, *ptr = dllp->start;
    while (ptr != NULL) {
        temp = ptr;
        ptr = ptr->next;
        free(temp);
    }
    dllp->start = NULL;
    dllp->length = 0;
    dllp->end = NULL;
}