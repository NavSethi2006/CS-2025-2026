/**
 * your program signature
 */ 

#include "fibonacci.h"

// TODO : MAKE SURE TO CHECK FOR OVERFLOWS BEFORE SUBMISSION

/**
 *  add your comment
 */
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
            f2 = f1+f2;
            f1=temp;
        }
    if(f2 > __INT_MAX__)return -1;

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
    }

    if (f[n] > __INT_MAX__) return -1;

    return f[n];
}

/**
 *  add your comment
 */
int dptd_fibonacci(int *f, int n) {
    if (n<=1) {f[n]=n; return f[n];}
    else if (f[n] >= __INT_MAX__) {return -1;}
    else if (f[n] > 0) {return f[n];}
    else {
        f[n] = dptd_fibonacci(f, n-2) + dptd_fibonacci(f, n-1);
    return f[n];
    }
}
// your code