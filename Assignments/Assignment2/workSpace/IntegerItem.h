#pragma once

#include "ListItem.h"

/*
Duy Anh Nguyen 7892957
IntegerItem.h
class IntegerItem
This will be the hold an integer for the queue.

Private member:
datum - The integer datum.

Public method:
IntegerItem() - Constructor to create an instance.
setValue() - Set a new value.

Public override method:
getValue() - Return the integer value.
*/

class IntegerItem : public ListItem
{
private:
    // Private member
    int datum = -1; // The integer datum

public:
    // Public method
    IntegerItem(const int DATUM); // Constructor to create an instance
    void setValue(const int VALUE); // Set a mew value

    // Public override method
    int getValue() override; // Get the integer value
};
