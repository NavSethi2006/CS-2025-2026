/*
--------------------------------------------------
Project: a1q1
File:    mymortgage.c
Author:  Navin Sethi
Version: 2025-09-12
--------------------------------------------------
*/

#include "mymortgage.h"

float monthly_payment(float principal_amount, float annual_interest_rate, int years) {
    float r = annual_interest_rate/100/12;
    float months = years*12;
    
    float mortgage = principal_amount * r * pow(1 + r, months) / (pow(1 + r, months) - 1);

    return mortgage;
}

float total_payment(float principal_amount, float annual_interest_rate, int years) {

    float payment = monthly_payment(principal_amount, annual_interest_rate, years);
    return payment * years*12;
}

float total_interest(float principal_amount, float annual_interest_rate, int years) {
    float total_p = total_payment(principal_amount, annual_interest_rate, years);

    return total_p - principal_amount;
}

