/*
 * your program signature
 */  
#include <stdio.h>
#include <stdlib.h>
#include "common.h"
#include "queue.h"
#include "stack.h"
#include "expression.h"


int precedence(char op) {
    switch (op) {
        case '^': return 3;
        case '*':
        case '/':
        case '%': return 2;
        case '+':
        case '-': return 1;
        default:  return 0;
    }
}

QUEUE infix_to_postfix(char *infixstr) {
// your code
    char *p = infixstr;
    QUEUE queue = {0}; // result postfix expression in queue
    STACK stack = {0}; // auxiliary stack
    int sign = 1, num = 0;
    while (*p) { // expression string traversal
        if ( *p == '-' && (p == infixstr || *(p-1) == '(') ) { // get the sign of an operand
        sign = -1;
    }
    else if ( mytype(*p) == 0) { // namely *p is digit character, action: use horner’s algorithm to get the operand
        num = *p-'0'; while ((*(p+1) >= '0' && *(p+1) <= '9')) { num = num*10 + *(p+1)-'0'; p++; }
        enqueue(&queue, new_node(sign*num, 0));
        sign = 1;
    }
    else if (mytype(*p) == 1) { // namely *p is an operator character
            while (stack.top && precedence(stack.top->data) >= precedence(*p)) {
                enqueue(&queue, pop(&stack));
            }
    // Then push current operator onto stack
        push(&stack, new_node(*p, 1));
    }
    else if (mytype(*p) == 2) { // *p == '(‘
        push(&stack, new_node(*p, 2));
    }
    else if (mytype(*p) == 3) { // *p == ‘)‘
        while (stack.top && stack.top->data != '(') {
            enqueue(&queue, pop(&stack));
        }
        // Remove '(' from stack (but don’t enqueue it)
        if (stack.top && stack.top->data == '(') {
            pop(&stack);
        }
    } // else ignore
    p++; // move to next character
    }
    // finally pop each node and insert it to queue
    while (stack.top) { enqueue(&queue, pop(&stack) ); }
    return queue;

}

int evaluate_postfix(QUEUE queue) {
    NODE *p = queue.front;
    STACK stack = {0}; // auxilliary stack for postfix evaluation
    int type = 0;
    while (p) { // traversal the queue linked list
        type = p->type;
        if (type == 0) { // operant
        push(&stack, new_node(p->data, 0));
        }
        else if (type == 1){ // operator
            int operator = p->data;
            NODE *oprand2 = pop(&stack);
            if (operator == '+')
                stack.top->data = stack.top->data + oprand2->data;
            else if (operator == '-')
                stack.top->data = stack.top->data - oprand2->data;
            else if (operator == '*') 
                stack.top->data = stack.top->data * oprand2->data;
            else if (operator == '/')
                stack.top->data = stack.top->data / oprand2->data;
            free(oprand2); 
        }
    p = p->next; 
    }
    int result = stack.top->data;
    clean_stack(&stack);
    return result; 

}

int evaluate_infix(char *infixstr) {
// your code
    QUEUE q = infix_to_postfix(infixstr);
    int ret = evaluate_postfix(q);
    return ret;
}