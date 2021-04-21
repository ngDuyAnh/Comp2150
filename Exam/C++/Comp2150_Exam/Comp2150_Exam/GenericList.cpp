#include "GenericList.h"


GenericList::GenericList(const GenericList& other)
{
    // Deep copy
    Node* otherNode = other.head;

    while (otherNode != nullptr)
    {
        ListItem* datum = otherNode->getData();

        // Insert the copy to the current list
        this->insert(datum);

        // Go to the next node
        otherNode = otherNode->getNext();
    }
}

// Deep copy assignment
GenericList GenericList::operator=(const GenericList& other)
{
    // Deep copy list
    GenericList list(other);

    // Make a copy of the other list
    return list;
}
