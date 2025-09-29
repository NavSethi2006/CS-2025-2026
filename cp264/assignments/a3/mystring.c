/*
--------------------------------------------------
Project: a3q1
File:    mystring.h
Author:  Navin Sethi
Version: 2025-09-12
--------------------------------------------------
*/
 #include "mystring.h"


int str_words(char *s) {
 // your code

    if (s == 0) return -1;
    int count = 0;
    int in_word = 0;
    char *p = s;
    while (*p) {
        if ((*p >= 'A' && *p <= 'Z' ) || (*p >= 'a' && *p <= 'z' )) {
            if(!in_word) {
                in_word = 1;
            }
        }else if(*p == ' ' || *p == '\t' || *p == ',' || *p == '.') {
            if(in_word) {
                count++;
                in_word = 0;
            }
        }
        p++; // move to next
    }
    if(in_word) {
        count++;
    }

    return count;
}
 

int str_lower(char *s) {
    if (s == 0) return -1;
    int shift_val = 32;
    int count = 0;
    char *p = s;
    while(*p) {
        if((*p >= 'A' && *p <= 'Z' )) {
            *p += shift_val;
            count++;
        }
        p++;
    }
 }
 

void str_trim(char *s) {
    char *p=s, *dp=s;
    int last_was_space = 0;
    while(*p) {
        if(*p != ' ' || (p > s && *(p-1) != ' ')) {
            *dp = *p;
            dp++;
        }
        p++;
    }
    if(*(p-1)!=' ') {
        *dp='\0';
    }else {
        *(dp-1) = '\0';
    }

}