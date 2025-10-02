/*
--------------------------------------------------
Project: a4q1
File:    mystring.h
Author:  Navin Sethi
Version: 2025-09-12
--------------------------------------------------
*/

#include "mysort.h"

void swap(void **x, void **y) {
     void *temp = *y;
     *y = *x;
     *x = temp;
}

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
     for (int i = left; i < right; i++) {
          int min = i;
          for(int j = i+1; j <= right ; ++j) {
               if (cmp(a[j], a[min]) <0) {
                    min = j;
               }
          }
          if (i != min) swap(&a[i], &a[min]);
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

     if(left >= right) return;

     void *pivot = a[(left + right) / 2];
     int i = left, j = right;

     while(i <= j) {
          while(cmp(a[i], pivot) < 0) i++;
          while(cmp(a[j], pivot) > 0) j--;
          if(i <= j) {
               void *tmp = a[i];
               a[i] = a[j];
               a[j] = tmp;
               i++;
               j--;
          }
     }

     if(left < j) my_sort(a,left,j,cmp);
     if(i < right) my_sort(a,i,right,cmp);

}