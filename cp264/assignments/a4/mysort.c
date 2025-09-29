/*
--------------------------------------------------
Project: a4q1
File:    mystring.h
Author:  Navin Sethi
Version: 2025-09-12
--------------------------------------------------
*/

#include "mysort.h"

// swap pointers
void swap(void **x, void **y) {
     void *temp = *y;
     *y = *x;
     *x = temp;
}

// a compare floating values pointed by void pointers. 
int cmp(void *x, void *y) {
   float a = *(float*)x;
   float b = *(float*)y; 
     if (a > b) return 1;
     else if (a < b) return -1;
     else return 0;
}   

void select_sort(void *a[], int left, int right)
{
// your code
}

void quick_sort(void *a[], int left, int right)
{ 
// your code
}

void my_sort(void *a[], int left, int right, int (*cmp)(void*, void*) )
{ 
// your code
}