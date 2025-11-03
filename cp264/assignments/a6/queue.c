#include <stdio.h>
#include "queue.h"

void enqueue(QUEUE *qp, NODE *np) {
// your code

    NODE* rear = qp->rear;
    if(rear != NULL) {
        rear->next = np;
        qp->rear = np;
    } else {
        qp->front = np;
        qp->rear = qp->front;
    }

    qp->length++;

}  

NODE *dequeue(QUEUE *qp) {
// your code

    NODE *node_to_free = qp->front;
    qp->front = node_to_free->next;

    node_to_free->next = NULL;
    qp->length--;
    return node_to_free;
}

void clean_queue(QUEUE *qp) {
  clean(&(qp->front));
  qp->front = NULL;
  qp->rear = NULL;
  qp->length=0;
}