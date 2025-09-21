 
#include <math.h>
#include "matrix.h"

float norm(float *v, int n) {
// your code

    float a = 0;
    for (int i = 0; i <= n;i++) {
        a += pow(v[i], 2);
    }

    double ans = sqrt(a);

    return (float)ans;
}

void normalize(const float *vin, float *vout, int n) {
// your code
    float a = norm(vin, n);

    



}

void matrix_scalar_multiply(const float *A, float scalar, float *B, int rows, int cols) {
// your code
}

void matrix_transpose(const float *A, float *B, int rows, int cols) {
// your code
}

void matrix_add(const float *A, const float *B, float *C, int rows, int cols) {
// your code
}

void matrix_multiply(const float *A, const float *B, float *C, int rowsA, int colsA, int colsB) {
// your code
}