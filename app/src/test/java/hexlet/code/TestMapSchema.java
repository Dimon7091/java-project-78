package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestMapSchema {
    private Validator validator = new Validator();


    @Test
    public void noMethodsTest() {
        var actual = validator.map().isValid(null);
        var expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void requiredTest() {
        var actualWithNull = validator.map().required().isValid(null);
        var expectedWithNull = false;
        assertEquals(expectedWithNull, actualWithNull);
        var actualWithMap = validator.map().required().isValid(new HashMap<>());
        var expectedWithMap = true;
        assertEquals(expectedWithMap, actualWithMap);
    }

    @Test
    public void sizeof() {
        var actualValid = validator.map().sizeof(2).isValid(Map.of("key1", "value",
                "key32", "value"));
        var expectedValid = true;
        assertEquals(expectedValid, actualValid);
        var actualSmallerSize = validator.map().sizeof(1).isValid(Map.of("key1", "value",
                "key32", "value"));
        var expectedSmallerSize = false;
        assertEquals(expectedSmallerSize, actualSmallerSize);
        var actualLargerSize = validator.map().sizeof(1).isValid(Map.of("key1", "value",
                "key32", "value"));
        var expectedLargerSize = false;
        assertEquals(expectedLargerSize, actualLargerSize);
    }
}
