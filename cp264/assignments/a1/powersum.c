#include "powersum.h"

int power_overflow(int b, int n) {
    int p = 1;

    for (int i = 1; i < n; i++) {
        p = p*b;
    }

    int temp = p*b;
    if(temp/b != p) {
        return 1;
    } else {
        return 0;
    }


}

int mypower(int b, int n) {
    int p = 1;

    for (int i = 0; i < n; i++) {
        p = p*b;
    }

    if(power_overflow(b, n) == 1){return 0;}
    else {return p;}


}

int powersum(int b, int n) {
    int p = 1;
    int sum = 0;

    for (int i = 0; i < n; i++) {
        p=p*b;
        sum += p;
    }

    if(power_overflow(b, n) == 1){return 0;}
    else {return sum+1;}


} 