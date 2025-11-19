#include <stdio.h>
#include <stdlib.h> 
#include <string.h>
#include "hash.h"


HNODE *hashtable_search(HASHTABLE *ht, char *name) {
// your code

    int i = hash(name, ht->size);
    HNODE *p = ht->hna[i];
    while(p != NULL) {
        if(strcmp(p->data.name, name) == 0) {
            return p;
        }
        p = p->next;
    }

    return NULL;
}

int hashtable_insert(HASHTABLE *ht, DATA data) {
// your code

    int i = hash(data.name, ht->size);
    HNODE *curr = ht->hna[i];

    while (curr != NULL) {
        if (strcmp(curr->data.name, data.name) == 0) {
            curr->data.value = data.value;
            return 0;
        }
        curr = curr->next;
    }

    HNODE *newnode = malloc(sizeof(HNODE));
    newnode->data = data;

    newnode->next = ht->hna[i];
    ht->hna[i] = newnode;

    ht->count++;

return 0;
}

int hashtable_delete(HASHTABLE *ht, char *name) {
// your code
    int i = hash(name, ht->size);
    HNODE *prev = NULL;
    HNODE *curr = ht->hna[i];

    while (curr != NULL) {
        if (strcmp(curr->data.name, name) == 0) {
            if (prev == NULL) {
                ht->hna[i] = curr->next;
            }else {
                prev->next = curr->next;            
            }
            ht->count--;
            return 1;
        }
        prev = curr;
        curr = curr->next;
    }
return 0;
}


int hash(char* key, int size) {
    unsigned int hash = 0;
    while (*key) {
        hash += *key++;
    }
    return hash % size;
}

HASHTABLE *new_hashtable(int size) {
    HASHTABLE *ht = (HASHTABLE*) malloc(sizeof(HASHTABLE));
    ht->hna = (HNODE**) malloc(sizeof(HNODE**) * size);
    int i;
    for (i = 0; i < size; i++)
        *(ht->hna + i) = NULL;

    ht->size = size;
    ht->count = 0;
    return ht;
}


void hashtable_clean(HASHTABLE **htp) {
    if (*htp == NULL)
        return;
    HASHTABLE *ht = *htp;
    HNODE *p, *temp;
    int i;
    for (i = 0; i < ht->size; i++) {
        p = ht->hna[i];
        while (p) {
            temp = p;
            p = p->next;
            free(temp);
        }
        ht->hna[i] = NULL;
    }
    free(ht->hna);
    ht->hna = NULL;
    *htp = NULL;
}
