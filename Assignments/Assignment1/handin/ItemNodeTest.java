import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Duy Anh Nguyen 7892957
February 5, 2021
ItemNodeTest.java
class ItemNodeTest
Testing class ItemNode for linked list that hold datum
        extends abstract class Item.
*/

class ItemNodeTest
{

    @Test
    void getDatum()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final ItemNode NODE1 = new ItemNode(DATUM1);
        final ItemNode NODE2 = new ItemNode(DATUM2, NODE1);
        final ItemNode NODE3 = new ItemNode(DATUM3, NODE1, NODE2);

        // Assert
        assertEquals(NODE1.getDatum().printString(), USERNAME1);
        assertEquals(NODE2.getDatum().printString(), USERNAME2);
        assertEquals(NODE3.getDatum().printString(), USERNAME3);
    }

    @Test
    void getNext()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final ItemNode NODE1 = new ItemNode(DATUM1);
        final ItemNode NODE2 = new ItemNode(DATUM2, NODE1);
        final ItemNode NODE3 = new ItemNode(DATUM3, NODE1, NODE2);

        // Assert
        assertEquals(NODE1.getNext(), null);
        assertEquals(NODE2.getNext(), NODE1);
        assertEquals(NODE3.getNext(), NODE1);
    }

    @Test
    void getPrev()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final ItemNode NODE1 = new ItemNode(DATUM1);
        final ItemNode NODE2 = new ItemNode(DATUM2, NODE1);
        final ItemNode NODE3 = new ItemNode(DATUM3, NODE1, NODE2);

        // Assert
        assertEquals(NODE1.getPrev(), null);
        assertEquals(NODE2.getPrev(), null);
        assertEquals(NODE3.getPrev(), NODE2);
    }

    @Test
    void setNext()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final ItemNode NODE1 = new ItemNode(DATUM1);
        final ItemNode NODE2 = new ItemNode(DATUM2, NODE1);
        final ItemNode NODE3 = new ItemNode(DATUM3, NODE1, NODE2);
        NODE1.setNext(NODE2);
        NODE2.setNext(NODE3);
        NODE3.setNext(null);

        // Assert
        assertEquals(NODE1.getNext(), NODE2);
        assertEquals(NODE2.getNext(), NODE3);
        assertEquals(NODE3.getNext(), null);
    }

    @Test
    void setPrev()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final ItemNode NODE1 = new ItemNode(DATUM1);
        final ItemNode NODE2 = new ItemNode(DATUM2, NODE1);
        final ItemNode NODE3 = new ItemNode(DATUM3, NODE1, NODE2);
        NODE1.setPrev(null);
        NODE2.setPrev(NODE1);
        NODE3.setPrev(NODE2);

        // Assert
        assertEquals(NODE1.getPrev(), null);
        assertEquals(NODE2.getPrev(), NODE1);
        assertEquals(NODE3.getPrev(), NODE2);
    }

    @Test
    void printString()
    {
        // Test data
        final String USERNAME1 = "User1";
        final Item DATUM1 = new User(USERNAME1);

        // Test
        final ItemNode NODE1 = new ItemNode(DATUM1);

        // Assert
        assertEquals(NODE1.printString(), USERNAME1);
    }

}
