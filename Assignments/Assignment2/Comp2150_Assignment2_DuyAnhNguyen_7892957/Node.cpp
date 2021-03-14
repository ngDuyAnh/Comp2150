#include "Node.h"
#include "ListItem.h"

/*
Duy Anh Nguyen 7892957
March 1, 2021
Node.cpp
class Node
*/

// Public method

/* Node()
Constructor to create an instance of node.
*/
Node::Node(const ListItem* const DATUM) : DATUM(DATUM)
{
    // Initialize the link pointer to nullptr
    this->nextNode = nullptr;
    this->prevNode = nullptr;
}

Node::Node(const ListItem* const DATUM, Node* const next) : Node(DATUM)
{
    this->nextNode = next;
}

Node::Node(const ListItem* const DATUM, Node* const next, Node* const prev) : Node(DATUM, next)
{
    this->prevNode = prev;
}



/* getDatum()
Get the datum the node holds.

Return:
The pointer to the datum the node holds.
*/
ListItem* Node::getDatum()
{
    return const_cast<ListItem*>(this->DATUM);
}



/* getNext()
Get the next node pointer.

Return:
The pointer to the next node.
*/
Node* Node::getNext()
{
    return this->nextNode;
}



/* getPrev()
Get the previous node pointer.

Return:
The pointer to the previous node.
*/
Node* Node::getPrev()
{
    return this->prevNode;
}



/* setNext()
Set the next node pointer.

Parameter:
NEXT - The next node pointer to set to.
*/
void Node::setNext(Node* const next)
{
    this->nextNode = const_cast<Node*>(next);
}



/* setPrev()
Set the previous node pointer.

Parameter:
NEXT - The previous node pointer to set to.
*/
void Node::setPrev(Node* const prev)
{
    this->prevNode = const_cast<Node*>(prev);
}
