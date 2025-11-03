#include <stdio.h>
#include <stdlib.h>
#include "queue_stack.h"
#include "tree.h"

TPROPS tree_property(TNODE *root) {
// your code
    int height = 0;
    int nodes = 0;
    QUEUE *q;
    TPROPS r = {0};
    
    if (root ) {
        TNODE *lp = root->left;
        TNODE *rp = root->right;
        enqueue(q, root);
        

        while (q != NULL) {
            height++;

            if(lp != NULL) {
                nodes++;
            }
            if(rp != NULL) {
                nodes++;
            }

        }
    }
    return r;
}

void preorder(TNODE *root) {
// your code
}

void inorder(TNODE *root) {
// your code
}

void postorder(TNODE *root) {
// your code
}

void bforder(TNODE *root) {
// your code
}

TNODE *bfs(TNODE *root, char val) {
// your code
}

TNODE *dfs(TNODE *root, char val) {
// your code

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
