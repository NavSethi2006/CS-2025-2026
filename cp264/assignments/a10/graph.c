/*
 * your program signature
 */ 

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "queue_stack.h"
#include "graph.h"

GRAPH *new_graph(int order) {
  GRAPH *gp = malloc(sizeof(GRAPH));
  gp->nodes = malloc(order * sizeof(GNODE*));
  
  int i;
  for (i = 0; i < order; i++) {
    gp->nodes[i] = malloc(sizeof(GNODE));
    gp->nodes[i]->nid = i;
    strcpy(gp->nodes[i]->name, "null");
    gp->nodes[i]->neighbor = NULL;
  }
  
  gp->order = order;
  gp->size = 0;  
 
  return gp;
}

void insert_edge_graph(GRAPH *g, int from, int to, int weight) {
// your code
  if(g == NULL) return;
  if(from < 0 || from >= g->order || to < 0 || to >= g->order) return;

  GNODE *fromNode = g->nodes[from];
  ADJNODE *curr = fromNode->neighbor;
  ADJNODE *prev = NULL;

  while(curr != NULL) {
    if(curr->nid == to) {
      curr->weight = weight;
      return;
    }
    prev = curr;
    curr = curr->next;
  }

  ADJNODE *newnode = (ADJNODE*) malloc(sizeof(ADJNODE));
  newnode->nid = to;
  newnode->weight = weight;
  newnode->next = NULL;

  if(prev == NULL) {
    fromNode->neighbor = newnode;
  } else {
    prev->next = newnode;
  }

  g->size++;
}

void delete_edge_graph(GRAPH *g, int from, int to) {
// your code
  GNODE *fromNode = g->nodes[from];
  ADJNODE *curr = fromNode->neighbor;
  ADJNODE *prev = NULL;

  while(curr != NULL) {

    if(curr->nid == to) {
      break;
    }

    prev = curr;
    curr = curr->next;
  }

  if(prev == NULL) {
    g->nodes[from]->neighbor = curr->next;
  } else if (curr->next == NULL) {
    prev->next = NULL;
  } else {
    prev->next = curr->next;
  }

//  free(curr);

  g->size--;

}

int get_edge_weight(GRAPH *g, int from, int to) {
// your code
  GNODE *fromNode = g->nodes[from];
  ADJNODE *curr = fromNode->neighbor;

  while(curr != NULL) {
    if(curr->nid == to)
      return curr->weight;

    curr = curr->next;
  }

  return INFINITY;
}

void traverse_bforder(GRAPH *g, int nid) {
// your code
  if (g == NULL) return;
  int n = g->order, visited[n], i;
  for (i=0; i<n;i++) visited[i] = 0;
  QUEUE queue = {0};
  GNODE *gnp = NULL;
  ADJNODE *anp = NULL;
  enqueue(&queue, g->nodes[nid]);
  visited[nid] = 1;
  while (queue.front) {
    gnp = (GNODE*) dequeue(&queue);
    printf("(%d %s) ", gnp->nid, gnp->name);
    ADJNODE *curr = gnp->neighbor;
    while(curr != NULL) {
      int node_id = curr->nid;
      if(!visited[node_id]) {
        visited[node_id] = 1;
        enqueue(&queue, g->nodes[node_id]);
      }

      curr = curr->next;
    }
  }
  clean_queue(&queue);
}


// Use auxiliary stack data structure for the algorithm
void traverse_dforder(GRAPH *g, int nid) {
// your code

  if (g == NULL) return;
  int n = g->order, visited[n], i;
  for (i=0; i<n;i++) visited[i] = 0;
  STACK stack = {0};
  GNODE *gnp = NULL;
  ADJNODE *anp = NULL;
  push(&stack, g->nodes[nid]);
  visited[nid] = 1;
  while (stack.top) {
    gnp = (GNODE*) pop(&stack);
    printf("(%d %s) ", gnp->nid, gnp->name);
    ADJNODE *curr = gnp->neighbor;
    while(curr != NULL) {
      int node_id = curr->nid;
      if(!visited[node_id]) {
        visited[node_id] = 1;
        push(&stack, g->nodes[node_id]);
      }

      curr = curr->next;
    }
  }
  clean_stack(&stack);
}

void clean_graph(GRAPH **gp) {
  int i;
  GRAPH *g = *gp;
  ADJNODE *temp, *ptr;
  for (i = 0; i < g->order; i++) {
    ptr = g->nodes[i]->neighbor;
    while (ptr != NULL) {
      temp = ptr;
      ptr = ptr->next;
      free(temp);
    }
    free(g->nodes[i]);
  }
  free(g->nodes);
  free(g);
  *gp = NULL;
}

void display_graph(GRAPH *g) {
  if (g ) {
  printf("order %d ", g->order);
  printf("size %d ", g->size);
  printf("(from to weight) ");
  int i;
  ADJNODE *ptr;
  for (i = 0; i < g->order; i++) {
    //printf("\n%d:", g->nodes[i]->nid);
    ptr = g->nodes[i]->neighbor;
    while (ptr != NULL) {
      printf("(%d %d %d) ", i,  ptr->nid, ptr->weight);
      ptr = ptr->next;
    }
  }
  }
}
