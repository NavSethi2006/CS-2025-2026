/*
--------------------------------------------------
Project: a1q1
File:    mychar.c
Author:  Navin Sethi
Version: 2025-09-12
--------------------------------------------------
*/

#include "mychar.h"


int mytype(char c) {

    int return_t;
    //  0 to 9 in ASCII
    if (c >= '0' && c <= '9') {
        return_t = 0;
    }
    // arithmetic operators in ASCII
    else if (c >= 42 && c <= 47) {
        return_t = 1;
    }
    // LEFT PARENTHESIS ( in ASCII
    else if(c == '(') {
        return_t = 2;
    }
    // RIGHT PARENTHESIS ) in ASCII
    else if(c == ')') {
        return_t = 3;
    }
    // English alphabet in ASCII both upper and lower case
    else if((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
        return_t = 4;
    }
    else {
        return_t = -1;
    }

    return return_t;
}

char case_flip(char c) {
// 31 is the difference in between upper and lowercase
    if(c <= 'a') {
        c += 31;
    } else{
        c -= 31;
    }


    return c;
}

int digit_to_int(char c) {

    int return_t;

    return_t = c - '0';

    return return_t;
}