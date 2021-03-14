#include "ListItem.h"

/*
Duy Anh Nguyen 7892957
March 4, 2021
ListItem.cpp
class ListItem
*/

// Public method

/* ~ListItem()
Destructor to release memory.
*/
ListItem::~ListItem()
{

}



/* compareTo()
Compare value of two instance.
	-1 means that the current instance value is smaller.
	0 means that they are equal.
	1 means that the current instance value is greater.

Return:
Integer indicate if the current value is smaller, equal, or greater.
-2 will be the error code if something is wrong.
*/
int ListItem::compareTo(ListItem* const other)
{
	// Local variable dictionary
	int compareReturn = -2; // The value to return after compare operation

	// Compare
	if (this->getValue() == other->getValue())
	{
		compareReturn = 0;
	}
	else if (this->getValue() < other->getValue())
	{
		compareReturn = -1;
	}
	else if (this->getValue() > other->getValue())
	{
		compareReturn = 1;
	}
	
	// Return the result of operation
	return compareReturn;
}
