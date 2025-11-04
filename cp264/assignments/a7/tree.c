#include <stdio.h>
#include <stdlib.h>
#include "queue_stack.h"
#include "tree.h"

TPROPS tree_property(TNODE *root) {
// your code
    TPROPS prop = {0, 0};  // initialize (order=0, height=0)

    if (root == NULL)
        return prop;       // base case: empty tree

    // recursive calls for left and right subtrees
    TPROPS left = tree_property(root->left);
    TPROPS right = tree_property(root->right);

    // compute order (total number of nodes)
    prop.order = 1 + left.order + right.order;

    // compute height (1 + max(left.height, right.height))
    prop.height = 1 + (left.height > right.height ? left.height : right.height);

    return prop;
}

void preorder(TNODE *root) {
// your code
    if(root == NULL) {
        return;
    }
    printf("%c ", root->data);
    preorder(root->left);
    preorder(root->right);
}

void inorder(TNODE *root) {
// your code

    if(root == NULL) {
        return;
    }
    inorder(root->left);
    printf("%c ", root->data);
    inorder(root->right);
}

void postorder(TNODE *root) {
// your code
if(root == NULL) {
    return;
}
    postorder(root->left);
    postorder(root->right);
    printf("%c ", root->data);

}


void aux_bforder(TNODE *root) {

}

void bforder(TNODE *root) {
// your code
    if (root == NULL)
    return;

    QUEUE queue = {0};  // initialize empty queue
    enqueue(&queue, root);

    while (queue.front) {
    TNODE *node = (TNODE *)dequeue(&queue);
    printf("%c ", node->data);

    if (node->left)
        enqueue(&queue, node->left);
    if (node->right)
        enqueue(&queue, node->right);
    }
}

TNODE *bfs(TNODE *root, char val) {
// your code

    if (root == NULL)
        return NULL;

    QUEUE queue = {0};
    enqueue(&queue, root);

    while (queue.front) {
    TNODE *node = (TNODE *)dequeue(&queue);

    if (node->data == val) {
        clean_queue(&queue);
        return node;  // found!
    }

    if (node->left)
        enqueue(&queue, node->left);
    if (node->right)
        enqueue(&queue, node->right);
    }

    return NULL; // not found
}

TNODE *dfs(TNODE *root, char val) {
// your code

    if (root == NULL)
        return NULL;

    STACK stack = {0};
    push(&stack, root);

    while (stack.top) {
    TNODE *node = (TNODE *)pop(&stack);

    if (node->data == val) {
        clean_stack(&stack);
        return node;  // found!
    }

    if (node->left)
        push(&stack, node->left);
    if (node->right)
        push(&stack, node->right);
    }

    return NULL; // not found


}


// the following functions are given, need to add to your program.

TNODE *tree_node(char val) {
    TNODE *np = (TNODE *) malloc(sizeof(TNODE));
    if (np != NULL) {
        np->data = val;
        np->left = NULL;
        np->right = NULL;
    }
    return np;
}

void clean_tree(TNODE **rootp) {
    TNODE *p = *rootp;
    if (p) {
        if (p->left)
            clean_tree(&p->left);
        if (p->right)
            clean_tree(&p->right);
        free(p);
    }
    *rootp = NULL;
}

void insert_tree(TNODE **rootp, char val) {
    if (*rootp == NULL) {
        *rootp = tree_node(val);
    } else {
        QUEUE queue = { 0 };
        TNODE *p;
        enqueue(&queue, *rootp);
        while (queue.front) {
            p = dequeue(&queue);
            if (p->left == NULL) {
                p->left = tree_node(val);
                break;
            } else {
                enqueue(&queue, p->left);
            }

            if (p->right == NULL) {
                p->right = tree_node(val);
                break;
            } else {
                enqueue(&queue, p->right);
            }
        }
    }
}
