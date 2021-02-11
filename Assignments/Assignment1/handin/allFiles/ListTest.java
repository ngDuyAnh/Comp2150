import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest
{

    @Test
    void append()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();
        testList.append(DATUM1);
        testList.append(DATUM2);
        testList.append(DATUM3);

        // Assert
        assertEquals(testList.peekIndex(0), DATUM1);
        assertEquals(testList.peekIndex(1), DATUM2);
        assertEquals(testList.peekIndex(2), DATUM3);
    }

    @Test
    void prepend()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();
        testList.prepend(DATUM1);
        testList.prepend(DATUM2);
        testList.prepend(DATUM3);

        // Assert
        assertEquals(testList.peekIndex(0), DATUM3);
        assertEquals(testList.peekIndex(1), DATUM2);
        assertEquals(testList.peekIndex(2), DATUM1);
    }

    @Test
    void insertIndex()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();
        testList.insertIndex(3, DATUM1); // DATUM1
        testList.insertIndex(-1, DATUM2); // DATUM2, DATUM1
        testList.insertIndex(1, DATUM3); // DATUM2, DATUM3, DATUM1

        // Assert
        assertEquals(testList.peekIndex(0), DATUM2);
        assertEquals(testList.peekIndex(1), DATUM3);
        assertEquals(testList.peekIndex(2), DATUM1);
    }

    @Test
    void removeHead()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();
        testList.insertIndex(3, DATUM1); // NODE1
        testList.insertIndex(2, DATUM2); // NODE1, NODE2
        testList.insertIndex(1, DATUM3); // NODE1, NODE3, NODE2

        // Assert
        assertEquals(testList.removeHead(), DATUM1);
        assertEquals(testList.removeHead(), DATUM3);
        assertEquals(testList.removeHead(), DATUM2);
    }

    @Test
    void removeTail()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();
        testList.insertIndex(3, DATUM1); // NODE1
        testList.insertIndex(2, DATUM2); // NODE1, NODE2
        testList.insertIndex(1, DATUM3); // NODE1, NODE3, NODE2

        // Assert
        assertEquals(testList.removeTail(), DATUM2);
        assertEquals(testList.removeTail(), DATUM3);
        assertEquals(testList.removeTail(), DATUM1);
    }

    @Test
    void removeIndex()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();
        testList.insertIndex(3, DATUM1); // NODE1
        testList.insertIndex(2, DATUM2); // NODE1, NODE2
        testList.insertIndex(1, DATUM3); // NODE1, NODE3, NODE2

        // Assert
        assertEquals(testList.removeIndex(3), DATUM2);
        assertEquals(testList.removeIndex(-1), DATUM1);
        assertEquals(testList.removeIndex(0), DATUM3);
    }

    @Test
    void peekHead()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();
        testList.insertIndex(3, DATUM1); // NODE1
        testList.insertIndex(2, DATUM2); // NODE1, NODE2
        testList.insertIndex(1, DATUM3); // NODE1, NODE3, NODE2

        // Assert
        assertEquals(testList.peekHead(), DATUM1);
    }

    @Test
    void peekTail()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();
        testList.insertIndex(3, DATUM1); // NODE1
        testList.insertIndex(2, DATUM2); // NODE1, NODE2
        testList.insertIndex(1, DATUM3); // NODE1, NODE3, NODE2

        // Assert
        assertEquals(testList.peekTail(), DATUM2);
    }

    @Test
    void peekIndex()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();
        testList.insertIndex(3, DATUM1); // NODE1
        testList.insertIndex(2, DATUM2); // NODE1, NODE2
        testList.insertIndex(1, DATUM3); // NODE1, NODE3, NODE2

        // Assert
        assertEquals(testList.peekIndex(3), DATUM2);
        assertEquals(testList.peekIndex(-1), DATUM1);
        assertEquals(testList.peekIndex(1), DATUM3);
    }

    @Test
    void isEmpty()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();

        // Assert
        assertEquals(testList.isEmpty(), true);

        // Test
        testList.insertIndex(3, DATUM1); // NODE1
        testList.insertIndex(2, DATUM2); // NODE1, NODE2
        testList.insertIndex(1, DATUM3); // NODE1, NODE3, NODE2

        // Assert
        assertEquals(testList.isEmpty(), false);
    }

    @Test
    void getLength()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);

        // Test
        final List testList = new List();

        // Assert
        assertEquals(testList.getLength(), 0);

        // Test
        testList.insertIndex(3, DATUM1); // NODE1
        testList.insertIndex(2, DATUM2); // NODE1, NODE2
        testList.insertIndex(1, DATUM3); // NODE1, NODE3, NODE2

        // Assert
        assertEquals(testList.getLength(), 3);
    }

    @Test
    void printString()
    {
        // Test data
        final String USERNAME1 = "User1";
        final String USERNAME2 = "User2";
        final String USERNAME3 = "User3";
        final Item DATUM1 = new User(USERNAME1);
        final Item DATUM2 = new User(USERNAME2);
        final Item DATUM3 = new User(USERNAME3);
        final String EXPECTED_PRINT =
                USERNAME1 + "\n" +
                USERNAME3 + "\n" +
                USERNAME2 + "\n";

        // Test
        final List testList = new List();

        // Assert
        assertEquals(testList.getLength(), 0);

        // Test
        testList.insertIndex(3, DATUM1); // NODE1
        testList.insertIndex(2, DATUM2); // NODE1, NODE2
        testList.insertIndex(1, DATUM3); // NODE1, NODE3, NODE2

        // Assert
        assertEquals(testList.printString(), EXPECTED_PRINT);
    }

}
