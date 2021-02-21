// This is file Quadratic.h
#pragma once

class Quadratic
{

private:
	double a = -1, b = -1, c = -1;

public:
    Quadratic(const double A, const double B, const double C);
	double findQuadratic(const double Z);
	Quadratic* sumQuadratic(const Quadratic* OTHER);
}






// This is file Quadratic.cpp
#include "Quadratic.h"


Quadratic::Quadratic(const double A, const double B, const double C)
{
    this->a = A;
	this->b = B;
	this->c = C;
}

double Quadratic::findQuadratic(const double Z)
{
    // Local variable dictionary
	double result = -1;
	
	// Find the quadratic
	result = (this->a * z * z) + (this->b * z) + this->c;
	
	// Return the calculation
	return result
}

Quadratic* Quadratic::sumQuadratic(const Quadratic* OTHER)
{
    // Local variable dictionary
	Quadratic* resultQuadratic = nullptr;
	
	// Calculate the sum of the two quadratics
	const double SUM_A = this->a + OTHER->a;
	const double SUM_B = this->b + OTHER->b;
	const double SUM_C = this->c + OTHER->c;
	
	// Create the result quadratic
	resultQuadratic = new Quadratic(SUM_A, SUM_B, SUM_C);
	
	// Return the result quadratic
	return resultQuadratic;
}





// This is in main.cpp
#include "Quadratic.h"

int main(void)
{
    Quadratic* qua = new Quadratic(3.1, 1.5, 1.2);
    
	const double QUA_RESULT = qua->findQuadratic(0.01);

	// Program exists successfully
	return 0;
}




