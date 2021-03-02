#pragma once

/*
Duy Anh Nguyen 7892957
March 1, 2021
ListItem.h
class ListItem
This will be the parent class for data type that can be whole by 
        LinkedList ADT.
There is a virtual method compareTo() to help compare value for 
        sorting in the LinkedList.

Public pure virtual method:
compareTo() - Compare with other instance for sorting.
*/

class ListItem
{
public:
    // Public pure virtual method
    virtual int compareTo(ListItem *other) = 0; // Compare value
};
