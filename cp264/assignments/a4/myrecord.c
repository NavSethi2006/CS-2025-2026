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

   strcpy(r.letter_grade, g[ans-1]);
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

   for (int i = 0; i < count; i++) {
      avg += dataset[i].score;

   }

   avg /= count;

   for (int i = 0; i < count; i++) {
      stan_deviation += (dataset[i].score-avg)*(dataset[i].score-avg);
   }
   stan_deviation = sqrt(stan_deviation/count);

   stats.mean = avg;
   stats.stddev = stan_deviation;
   stats.count = count;
   
   select_sort((void*)dataset,0, count);

   stats.median = dataset[count/2].score;


}


int report_data(FILE *fp, RECORD *dataset, STATS stats) {
// your code

   return 0;
}