#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "avl.h"


AVLNODE *avl_node(RECORD data)
{
  AVLNODE *np = (AVLNODE *)malloc(sizeof(AVLNODE));
  if (np)
  {
    np->data = data;
    np->height = 1;
    np->left = NULL;
    np->right = NULL;
  }
  return np;
}


int max(int a, int b) 
{
  return (a > b)? a : b;
}


int height(AVLNODE *np)
{
// your code
    int h = 0;
    if (np) h = np->height;
    return h;
}

int balance_factor(AVLNODE *np)
{
// your code
    int b = 0;
    if (np) b = height(np->left)-height(np->right);
    return b;
}


AVLNODE *rotate_left(AVLNODE *np)
{
// your code
// your implementation
  AVLNODE *child = np->right;
	AVLNODE *temp = child->left;
	// Rearrange the nodes.
	// < your code here >
	child->left = np;
	np->right = temp; 

  np->height = max(height(np->left), height(np->right))+1;
	child->height = max(height(child->left), height(child->right))+1;
  return child;
}

AVLNODE *rotate_right(AVLNODE *np)
{
// your code

  AVLNODE *child = np->left;
	AVLNODE *temp = child->right;
	// Rearrange the nodes.
	child->right = np;
	np->left = temp;
	
	np->height = max(height(np->left), height(np->right))+1;
	child->height = max(height(child->left), height(child->right))+1;
	// Return new root.
	return (child);
}

AVLNODE *extract_smallest_node(AVLNODE **rootp) {
  AVLNODE *p = *rootp, *parent = NULL;
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

void avl_insert(AVLNODE **rootp, RECORD data)
{  
  AVLNODE *np = (AVLNODE *) malloc(sizeof(AVLNODE));
  if (np == NULL) return;
  strcpy(np->data.name, data.name);
  np->data.score = data.score;
  np->height = 1;
  np->left = NULL;
  np->right = NULL;
  
  // 1. Perform the normal BST insertion
  if (*rootp == NULL) {
    *rootp = np;
    return;
  }
  
  AVLNODE *root = *rootp;
  if (strcmp(data.name, root->data.name) < 0 )
    avl_insert(&root->left, data);
  else if (strcmp(data.name, root->data.name) > 0 )
    avl_insert(&root->right, data);
  else return ;

  // 2. update height of this root node
  root->height = 1 + max(height(root->left), height(root->right));
 
  // 3. Get the balance factor of this ancestor node to check whether this node became unbalanced
  int bf = balance_factor(root);
  
  // 4. re-balance if not balanced
  if(bf > 1){
    if(balance_factor(root->left) >= 0) *rootp = rotate_right(root);
    else{
      root->left = rotate_left(root->left);
      *rootp = rotate_right(root);
    }
  } else if(bf < -1){
    if(balance_factor(root->right) <= 0) *rootp = rotate_left(root);
    else {
      root->right = rotate_right(root->right);
      *rootp = rotate_left(root);
    }
  } 
}

void avl_delete(AVLNODE **rootp, char *name)
{
  AVLNODE *root = *rootp;
  AVLNODE *np;
  if (root == NULL) return;

  if (strcmp(name, root->data.name) == 0) {
    if (root->left == NULL && root->right == NULL) {
      free(root);
      *rootp = NULL;
    } else if (root->left != NULL && root->right == NULL) {
      np = root->left;
      free(root);
      *rootp = np;
    } else if (root->left == NULL && root->right != NULL) {
      np = root->right;
      free(root);
      *rootp = np;
    } else if (root->left != NULL && root->right != NULL) {
      np = extract_smallest_node(&root->right);
      np->left = root->left;
      np->right = root->right;
      free(root);
      *rootp = np;
    }
  } else {
    if (strcmp(name, root->data.name) < 0) {
      avl_delete(&root->left, name);
    } else {
      avl_delete(&root->right, name);
    }
  }

  // If the tree had only one node then return
  if (*rootp == NULL) return;
  root = *rootp;
    
  // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
  root->height = 1+max(height(root->left), height(root->right));

  // STEP 3: GET THE BALANCE FACTOR OF THIS NODE 
  int bf = balance_factor(root);

  // STEP 4: rebalance if not balanced
  if(bf > 1){
    if(balance_factor(root->left) >= 0) *rootp = rotate_right(root);
    else{
      root->left = rotate_left(root->left);
      *rootp = rotate_right(root);
    }
  } else if(bf < -1){
    if(balance_factor(root->right) <= 0) *rootp = rotate_left(root);
    else {
      root->right = rotate_right(root->right);
      *rootp = rotate_left(root);
    }
  } 
  

}

AVLNODE *avl_search(AVLNODE *root, char *name) {
// your code
  if(root == NULL) {
    return NULL;
  }
  if(strcmp(root->data.name, name) == 0) {
    return root;
  }
  AVLNODE *node = avl_search(root->left, name);
  if(node) {
    return node;
  }

  return avl_search(root->right, name);
}


void avl_clean(AVLNODE **rootp) {
  AVLNODE *root = *rootp;
  if (root) {
    if (root->left)
      avl_clean(&root->left);
    if (root->right)
      avl_clean(&root->right);
    free(root);
  }
  *rootp = NULL;
}
