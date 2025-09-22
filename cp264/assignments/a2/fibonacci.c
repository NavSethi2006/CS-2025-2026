/*
--------------------------------------------------
Project: a2q2
File:    fibonacci.c
Author:  Navin Sethi
Version: 2025-09-21
--------------------------------------------------
*/

#include "fibonacci.h"


/**
 *  add your comment
 */

 int safe_add(int a, int b, int *result) {
    if (a > INT_MAX - b) {  // overflow check
        return -1;
    }
    *result = a + b;
    return 0;
}

int recursive_fibonacci(int n) {

    if(n<=1) {
        return n;
    } else {
        return recursive_fibonacci(n-2) + recursive_fibonacci(n-1);
    }

}

/**
 *  add your comment
 */
int iterative_fibonacci(int n)
{
    if (n <= 1) return n;
    else {
        int f1 = 0, f2 = 1;
        for (int i = 2; i <= n; i++) {
            int temp = f2; 
            int temp_sum;
            if (safe_add(f1, f2, &temp_sum) == -1) return -1;
            f2 = temp_sum;
            f1=temp;
        }
    return f2;
    }
}

/**
 *  add your comment
 */
int dpbu_fibonacci(int *f, int n) {
    if(n <= 1) return n;

    f[0]=0;
    f[1]=1; 

    for (int i=2; i <= n; i++) {
        f[i]= f[i-2]+f[i-1];
        if (f[i-2] > INT_MAX - f[i-1]) return -1;
    }

    

    return f[n];
}


/**
 *  add your comment
 */
int dptd_fibonacci(int *f, int n) {
    if (n<=1) {f[n]=n; return f[n];}
    else if (f[n] > 0) {return f[n];}
    else {
        f[n] = dptd_fibonacci(f, n-2) + dptd_fibonacci(f, n-1);
        if(dptd_fibonacci(f, n-2) > INT_MAX - dptd_fibonacci(f, n-2)) return -1;
    return f[n];
    }
}
// your code