/*
 * your program signature
 */ 

#include <stdio.h>
#include <stdlib.h> 
#include "edgelist.h"

EDGELIST *new_edgelist() {
    EDGELIST *tp = malloc(sizeof(EDGELIST));
    tp->size = 0;
    tp->start = NULL;
    tp->end = NULL;
    return tp;
}

void insert_edge_end(EDGELIST *g, int from, int to, int weight) {
// your code

    EDGENODE *node = malloc(sizeof(EDGENODE));
    node->from = from;
    node->to = to;
    node->weight = weight;
    node->next = NULL;

    if(g->end != NULL) { 
        g->end->next = node;
        g->end = node;
    } else {
        g->end = node;
        g->start = g->end;
    }
    g->size++;
}

void insert_edge_start(EDGELIST *g, int from, int to, int weight) {
// your code;
    EDGENODE *node = malloc(sizeof(EDGENODE));
    node->from = from;
    node->to = to;
    node->weight = weight;

    if(g->start != NULL) {
        node->next = g->start;
        g->start = node;
    } else {
        g->start = node;
        g->end = g->start;
    }
    g->size++;
}

void delete_edge(EDGELIST *g, int from, int to) {
// your code

    EDGENODE *curr = g->start;
    EDGENODE *prev = NULL;


    while(curr != NULL) {

        if(curr->from == from && curr->to == to) {
            if(prev == NULL) {
                g->start = g->start->next;
                free(curr);
            } else if (curr->next == NULL) {
                prev->next = NULL;
                g->end = prev;
                free(curr);
            } else {
                prev->next = curr->next;
            }
            g->size--;
            return;
        }
        prev = curr;
        curr = curr->next;
    }

}

int weight_edgelist(EDGELIST *g) {
// your code
    EDGENODE *curr = g->start;
    int weight = 0;
    while(curr != NULL) {
        weight += curr->weight;
        curr = curr->next;
    }

    return weight;
}

void clean_edgelist(EDGELIST **gp) {
    EDGELIST *g = *gp;
    EDGENODE *temp, *p = g->start;
    while (p) {
        temp = p;
        p = p->next;
        free(temp);
    }
    free(g);
    *gp = NULL;
}

void display_edgelist(EDGELIST *g) {
    if (g == NULL)
        return;
    printf("size %d ", g->size);
    printf("(from to weight) ");
    EDGENODE *p = g->start;
    while (p) {
        printf("(%d %d %d) ", p->from, p->to, p->weight);
        p = p->next;
    }
}
