#include "IntegerItem.h"

/*
Duy Anh Nguyen 7892957
March 5, 2021
IntegerItem.cpp
class IntegerItem
*/

// Public method
/* setValue()
Set new datum value.

Parameter:
VALUE - Value to set datum.

*/
void IntegerItem::setValue(const int VALUE)
{
    this->datum = VALUE;
}



// Public override method

/* getValue()
Get the datum integer value.

Return:
Datum integer.
*/
int IntegerItem::getValue()
{
    return this->datum;
}
