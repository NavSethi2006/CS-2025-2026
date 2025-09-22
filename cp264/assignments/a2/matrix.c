/*
--------------------------------------------------
Project: a2q3
File:    matrix.c
Author:  Navin Sethi
Version: 2025-09-21
--------------------------------------------------
*/

#include <math.h>
#include "matrix.h"

float norm(float *v, int n) {
// your code
    float sum = 0.0f;
    for (int i = 0; i < n; i++) {
        sum += v[i] * v[i];
    }
    return sqrtf(sum);
}

void normalize(const float *vin, float *vout, int n) {
// your code
    float a = norm(vin, n);

    for (int i = 0; i <= n; i++) {
        vout[i] = vin[i] / a;
    }

}

void matrix_scalar_multiply(const float *A, float scalar, float *B, int rows, int cols) {
// your code
    for (int i = 0; i <= rows+1; i++) {
        for(int j = 0; j <= cols+1;j++) {
            B[j] = A[j] * scalar;
        }
    }
}

void matrix_transpose(const float *A, float *B, int rows, int cols) {
// your code
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            B[j * rows + i] = A[i * cols + j];
        }
    }


}

void matrix_add(const float *A, const float *B, float *C, int rows, int cols) {
// your code

    for(int i = 0; i <= rows+cols; i++) {
        C[i] = A[i] + B[i];
    }

}

void matrix_multiply(const float *A, const float *B, float *C, int rowsA, int colsA, int colsB) {
// your code

    for (int i = 0; i < rowsA; i++) {
        for (int j = 0; j < colsB; j++) {
            float sum = 0.0f;
            for (int k = 0; k < colsA; k++) {
                sum += A[i * colsA + k] * B[k * colsB + j];
            }
            C[i * colsB + j] = sum;
        }
    }


}