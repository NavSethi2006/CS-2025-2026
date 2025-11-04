/*
--------------------------------------------------
Project: a7q3
File:    myrecord_bst.h
Author:  Navin Sethi
Version: 2025-09-12
--------------------------------------------------
*/ 

#ifndef MYRECORD_BST_H
#define MYRECORD_BST_H

#include "bst.h"

/* Define structure data type BSTSD contains pointer to the BSTNODE root
 * and count, mean, and stddev fields for stats of record data stored in
 * the BSTDS.
 */
typedef struct {
  BSTNODE *root;
  int count;
  float mean;
  float stddev;   
} BSTDS;

/* Add a record data into the BSTDS and update its statistic fields 
 * using incremental/online algorithm.
 * @param ds - pointer to the BSTDS.
 * @param record - RECORD data to be added.
 */
void add_record(BSTDS *ds, RECORD record);

/* Delete a node from BSTDA with data.name matching with the given name
 * and stats fields using incremental/online algorithm.
 * @param ds - pointer to the BSTDS.
 * @param name - node data.name to be removed.
 */
void remove_record(BSTDS *ds, char *name);

/* Clean the BSTDS and reset count, mean, stddev
 * @param ds - pointer to the BSTDS.
 */
void bstds_clean(BSTDS *ds);

#endif
