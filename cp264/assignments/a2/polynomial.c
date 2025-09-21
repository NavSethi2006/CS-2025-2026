#include "polynomial.h"

#define EPSILON 1e-6
#define MAXCOUNT 100

float horner(float *p, int n, float x)
{
// your code
    float r = 0;
    for(int i = 0; i < n; i++) {
        r = r*x+p[i];
    }
    return r;
}

void derivative(float *p, float *d, int n)
{
// your code

    for(int i = 0; i < n;i++) {
        d[i] = (float)(n-1-i) * p[i];
    }

}

float newton(float *p, int n, float x0)
{
// your code

    float x = x0;
    float d[64];
    
    derivative(p, d, n);

    for (int i = 0; i < MAXCOUNT; i++) {
        float fx = horner(p, n, x);
        float fpx = horner(d, n-1,x);

        if(fabsf(fpx) < EPSILON) {
            return x0;
        }

        float x_new = x - fx/fpx;
        if(fabsf(x_new - x) < EPSILON) {
            return x_new;
        }
      x=x_new;
    }

return x0;
}

