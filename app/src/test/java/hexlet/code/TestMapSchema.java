package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void sizeofTest() {
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
    @Test
    public void shapeTest() {
        Map<String, BaseSchema<String>> shapeSchemas = new HashMap<>();
        shapeSchemas.put("firstName", validator.string().required());
        shapeSchemas.put("lastName", validator.string().required().minLength(2));

        var schema = validator.map();
        schema.shape(shapeSchemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }
}
