 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "myrecord_sllist.h"


NODE *sll_search(SLL *sllp, char *name) {
// your code
    NODE *curr = sllp->start;
    NODE *ret = NULL;

    while (curr != NULL) {

        if(strcmp(curr->data.name, name) == 0) {
            ret = curr;
            break;
        }
        curr = curr->next;
    }
    
    return ret;

}

void sll_insert(SLL *sllp, char *name, float score) {
// your code
    NODE *new_node = (NODE*) malloc(sizeof(NODE));
    strcpy(new_node->data.name, name);
    new_node->data.score = score;
    new_node->next = NULL;

    NODE *curr = sllp->start;
    NODE *prev = NULL;

    while (curr != NULL && strcmp(curr->data.name, name) < 0)
    {
        prev = curr;
        curr = curr->next;
    }

    if (prev == NULL) { // empty linked list or found at start
        sllp->start = new_node; // insert at beginning
        new_node->next = curr;
    } else { // found the node or not found, i.e. p = NULL
        prev->next = new_node; // insert between between prev and p,
        new_node->next = curr;
    }

    sllp->length++;

}

int sll_delete(SLL *sllp, char *name) {
// your code

    NODE *curr = sllp->start;
    int ret;
    NODE *prev = NULL;

    while (curr != NULL) {
        if(strcmp(curr->data.name, name) == 0) {

            prev->next = curr->next;
            break;
        }
        prev = curr;
        curr = curr->next;
    }
    sllp->length--;

    return ret;


}

void sll_clean(SLL *sllp) {
    NODE *temp, *ptr = sllp->start;
    while (ptr != NULL) {
        temp = ptr;
        ptr = ptr->next;
        free(temp);
    }
    sllp->start = NULL;
    sllp->length = 0;
}