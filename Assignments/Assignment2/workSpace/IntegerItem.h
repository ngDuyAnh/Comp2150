#pragma once

#include "ListItem.h"

/*
Duy Anh Nguyen 7892957
IntegerItem.h
class IntegerItem
This will be the hold an integer for the queue.

Private member:
datum - The integer datum.

Public override method:
getValue() - Return the integer value.
*/

class IntegerItem : public ListItem
{
private:
    // Private member
    int datum = -1; // The integer datum

public:
    // Public override method
    int getValue() override; // Get the integer value
};
