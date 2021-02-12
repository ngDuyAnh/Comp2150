import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringItemTest {

    @Test
    void getString()
    {
        // Test data
        final String DATA = "Test data String.";

        // Test
        final StringItem STRING_CONTAINER = new StringItem(DATA);

        // Assert
        assertEquals(STRING_CONTAINER.getString(), DATA);
    }

    @Test
    void printString()
    {
        // Test data
        final String DATA = "Test data String.";

        // Test
        final StringItem STRING_CONTAINER = new StringItem(DATA);

        // Assert
        assertEquals(STRING_CONTAINER.printString(), DATA);
    }

}
