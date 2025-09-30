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

     for (int i = 0; i < right; ++i) {
          int k = i;
          for(int j = i+1; j < right ; ++j) {
               if (cmp(a[j], a[k]) <0) {
                    k = j;
               }
          }
          if (i != k) { // the following swap a[i] and a[k]
               swap(&a[i], &a[k]);
          }
     }
}

void quick_sort(void *a[], int left, int right)
{ 
// your code
     if( left < right ) {
          int i = left+1;
          int j = right;
          while(i <= j) {
               while( i <= right && cmp( a[i], a[left])<=0) i++;
               while( j >= left && cmp(a[j] , a[left]) > 0) j--;
               if( i < j ) {
                    swap (&a[i], &a[j]); 
               }
          }
          swap(&a[left], &a[j]);
          quick_sort(a, left, j-1);
          quick_sort(a, j+1, right);
     }

}

void my_sort(void *a[], int left, int right, int (*cmp)(void*, void*) )
{ 


}