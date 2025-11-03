/*
 * your program signature
 */ 
 
#include <stdio.h>
#include <math.h>
#include "bst.h"
#include "myrecord_bst.h"

void add_record(BSTDS *ds, RECORD record) {
// your code
}

void remove_record(BSTDS *ds, char *name) {
// your code    
}

void bstds_clean(BSTDS *ds) {
  bst_clean(&ds->root);
  ds->count = 0;
  ds->mean = 0;
  ds->stddev = 0;
}