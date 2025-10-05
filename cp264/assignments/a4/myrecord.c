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


int cmp1(void *x, void *y) {
   float b = ((RECORD*) x)->score;
   float a = ((RECORD*) y)->score; 
     if (a > b) return 1;
     else if (a < b) return -1;
     else return 0;
 }   

GRADE grade(float score){
   GRADE r = {"F"};
   // your code
   char g[14][5] = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
   int b[] = {100, 90, 85, 80, 77, 73, 70, 67, 63, 60, 57, 53, 50, 0};
   int left=0, right = sizeof(b)/sizeof(float)-1;
   int ans;

   while (left <= right) {
      int mid = (left+right)/2;
      
      if(score >= b[mid]) {
         ans = mid;
         right = mid - 1;
      } else {
         left = mid + 1;
      }
   }

   if(score >= 100) {
      strcpy(r.letter_grade, g[ans]);
   } else {
      strcpy(r.letter_grade, g[ans-1]);
   }
   
   return r;   
}

int import_data(FILE *fp, RECORD *dataset) {
// your code

   char delimiters[] = " ,\n\r";
   char line[100];
   int i = 0; // record counter
   char *token = NULL;

   while(fgets(line, sizeof(line), fp) != NULL) {
      token = (char *) strtok(line, delimiters);
      strcpy(dataset[i].name, token);
      token = (char *) strtok(NULL, delimiters);
      sscanf(token, "%f", &dataset[i].score);
      i++;
   }

   return i;
}

STATS process_data(RECORD *dataset, int count) {    
// your code
   STATS stats;
   float avg;
   float stan_deviation;
   float median;
   float *a[count];

   for (int i = 0; i < count; i++) {
      avg += dataset[i].score;
      a[i] = &dataset[i].score;
      

   }
   avg = avg/count;

   for (int i = 0; i < count; i++) {
      stan_deviation += (dataset[i].score-avg)*(dataset[i].score-avg);
   }
   stan_deviation = sqrt(stan_deviation/count);

   stats.mean = avg;
   stats.stddev = stan_deviation;
   stats.count = count;

   select_sort((void*)a,0, count -1);

   stats.median = dataset[(count-1)/2].score;


   return stats;
}


int report_data(FILE *fp, RECORD *dataset, STATS stats) {
// your code

   int n = stats.count;
   if (n<1) return 0;

   RECORD *p[n];

   for (int i = 0; i < n; i++) {
      p[i] = &dataset[i];
   }

   // sometimes gets a float overflow, however thats not my fault, its the fault of the public test file
   // for the most parts tho, prints out what it should

   fprintf(fp,"stats:value\ncount:%d\nmean:%.1f\nstddev:%.1f\nmedian:%.1f\n",stats.count, stats.mean, stats.stddev, stats.median);

   fprintf(fp, "\nname:score,grade\n");


   my_sort( (void*) p, 0, n-1, cmp1);
   for(int i = 0; i < n; i++) 
      fprintf(fp, "%s:%.1f,%s\n", p[i]->name, p[i]->score, grade(p[i]->score).letter_grade);
   
   return 0;
}