/*
--------------------------------------------------
Project: a4q2
File:    mystring.h
Author:  Navin Sethi
Version: 2025-09-12
--------------------------------------------------
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include "myrecord.h"
#include "mysort.h"

GRADE grade(float score){
   GRADE r = {"F"}; 
   // your code
   return r;   
}

int import_data(FILE *fp, RECORD *dataset) {
// your code
}

STATS process_data(RECORD *dataset, int count) {    
// your code
}


int report_data(FILE *fp, RECORD *dataset, STATS stats) {
// your code
}