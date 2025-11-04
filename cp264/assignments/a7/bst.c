/*
--------------------------------------------------
Project: a7q2
File:    bst.c
Author:  Navin Sethi
Version: 2025-09-12
--------------------------------------------------
*/
 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "bst.h"


BSTNODE *bst_node(RECORD data);
BSTNODE *extract_smallest_node(BSTNODE **rootp);


BSTNODE *bst_search(BSTNODE *root, char *key) {
// your code
    if(root == NULL)
        return NULL;
    if(strcmp(root->data.name, key) == 0) {
        return root;
    }

    BSTNODE *node = bst_search(root->left, key);
    if(node) {
        return node;
    }
    
    return bst_search(root->right, key);

}

void bst_insert(BSTNODE **rootp, RECORD data) {
// your code

    if(*rootp == NULL) {
        
        BSTNODE *node = bst_node(data);

        *rootp = node; // link new node to parent pointer

    }else {
        if ((*rootp)->data.score > data.score) {
            bst_insert(&((*rootp)->left), data);
        } 
        if ((*rootp)->data.score < data.score) {
            bst_insert(&((*rootp)->right), data);
        }
    }
}

void bst_delete(BSTNODE **rootp, char *key) {
// your code
    if (rootp == NULL || *rootp == NULL)
        return;

    int cmp = strcmp(key, (*rootp)->data.name);

    if (cmp < 0) {
        bst_delete(&((*rootp)->left), key);
    } else if (cmp > 0) {
        bst_delete(&((*rootp)->right), key);
    } else {
        BSTNODE *temp = *rootp;

        if (temp->left == NULL && temp->right == NULL) {
            free(temp);
            *rootp = NULL;
        }
        else if (temp->left == NULL) {
            *rootp = temp->right;
            free(temp);
        }
        else if (temp->right == NULL) {
            *rootp = temp->left;
            free(temp);
        }
        else {
            BSTNODE *succ = extract_smallest_node(rootp);
            temp->data = succ->data;
            bst_delete(&(temp->right), succ->data.name);
        }

    }

}


BSTNODE *bst_node(RECORD data) {
    BSTNODE *np = (BSTNODE *) malloc(sizeof(BSTNODE));
    if (np) {
        memcpy(np, &data, sizeof(BSTNODE));
        np->left = NULL;
        np->right = NULL;
    }
    return np;
}

void bst_clean(BSTNODE **rootp) {
    BSTNODE *root = *rootp;
    if (root) {
        if (root->left)
            bst_clean(&root->left);
        if (root->right)
            bst_clean(&root->right);
        free(root);
    }
    *rootp = NULL;
}

BSTNODE *extract_smallest_node(BSTNODE **rootp) {
    BSTNODE *p = *rootp, *parent = NULL;
    if (p) {
        while (p->left) {
            parent = p;
            p = p->left;
        }

        if (parent == NULL)
            *rootp = p->right;
        else
            parent->left = p->right;

        p->left = NULL;
        p->right = NULL;
    }

    return p;
}
