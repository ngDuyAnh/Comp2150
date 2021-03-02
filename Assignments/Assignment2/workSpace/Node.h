#pragma once

/*
Duy Anh Nguyen 7892957
March 1, 2021
Node.h
class Node
This will be the node for the linked list ADT.

Private member:
DATUM - Pointer to the datum the node will hold.
nextNode - Pointer to the next node.
prevNode - Pointer to the previous node.

Public method:
Node() - Constructor to create an instance of node.
getDatum() - Return the datum the node holds.
getNext() - Get the next node pointer.
getPrev() - Get the previous node pointer.
setNext() - Set the next node pointer.
setPrev() - Set the previous node pointer.
*/

// Forward declaration
class ListItem;

class Node
{
private:
    // Private member
    const ListItem* DATUM;    // Pointer to the datum
    Node* nextNode = nullptr; // Pointer to the next node
    Node* prevNode = nullptr; // Pointer to the previous node

public:
    // Public method
    Node(const ListItem* DATUM); // Constructor to create an instance of node
    Node(const ListItem* DATUM, const Node* NEXT);
    Node(const ListItem* DATUM, const Node* NEXT, const Node* PREV);
    ListItem* getDatum(); // Get the datum the node holds
    Node* getNext(); // Get the next node pointer
    Node* getPrev(); // Get the previous node pointer
    void setNext(const Node* NEXT); // Set the next node pointer
    void setPrev(const Node* PREV); // Set the previous node pointer
};
