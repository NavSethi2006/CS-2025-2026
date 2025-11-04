/*
--------------------------------------------------
Project: a7q3
File:    myrecord_bst.c
Author:  Navin Sethi
Version: 2025-09-12
--------------------------------------------------
*/
 
#include <stdio.h>
#include <math.h>
#include "bst.h"
#include "myrecord_bst.h"

void add_record(BSTDS *ds, RECORD record) {
// your code

  if(ds == NULL)
    return;

  bst_insert(&(ds->root), record);

  ds->count++;

  float x = record.score;

  if(ds->count == 1) {
    ds->mean = x;
    ds->stddev = 0;
    return;
  }

  float old_mean = ds->mean;
  ds->mean += (x - ds->mean) / ds->count;

  static float M2 = 0;
  M2 += (x - old_mean) * (x - ds->mean);
  ds->stddev = sqrt(M2/ds->count);

}

void remove_record(BSTDS *ds, char *name) {
// your code
  if (ds == NULL || ds->root == NULL) return;

    BSTNODE *node = bst_search(ds->root, name);
    if (node == NULL) return;

    float x = node->data.score;
    int n_old = ds->count;
    int n_new = n_old - 1;
    if (n_new <= 0) {
        bst_delete(&(ds->root), name);
        ds->count = 0;
        ds->mean = 0;
        ds->stddev = 0;
        return;
    }

    float mean_old = ds->mean;
    float std_old = ds->stddev;

    bst_delete(&(ds->root), name);

    float mean_new = (mean_old * n_old - x) / n_new;

    float M2_old = std_old * std_old * n_old;
    float M2_new = M2_old - (x - mean_old) * (x - mean_new);
    if (M2_new < 0) M2_new = 0;

    float std_new = sqrt(M2_new / n_new);

    ds->count = n_new;
    ds->mean = mean_new;
    ds->stddev = std_new;
}

void bstds_clean(BSTDS *ds) {
  bst_clean(&ds->root);
  ds->count = 0;
  ds->mean = 0;
  ds->stddev = 0;
}