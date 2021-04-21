#pragma once

#include "ListItem.h"

class Node;
class ListItem;


class GenericList : public ListItem {

private:

    Node* head;

public:

    GenericList();
    GenericList(GenericList* other);

    void operator=(GenericList* other);

    void print();

    void insert(ListItem* newItem);

    virtual ~GenericList();

};