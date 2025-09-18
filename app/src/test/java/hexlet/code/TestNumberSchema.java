package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNumberSchema {
    private Validator validator = new Validator();

    @Test
    public void noMethodsTest() {
        var actual = validator.number().isValid(5);
        var expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void withOutRequiredTest() {
        var actual = validator.number().isValid(null);
        var expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void requiredTest() {
        var actualWithNull = validator.number().required().isValid(null);
        var expectedWithNull = false;
        assertEquals(expectedWithNull, actualWithNull);
        var actualNoNull = validator.number().required().isValid(10);
        var expectedNoNull = true;
        assertEquals(expectedNoNull, actualNoNull);
    }

    @Test
    public void positiveTest() {
        var actualNoPositive = validator.number().isValid(-10);
        var expectedNoPositive = true;
        assertEquals(expectedNoPositive, actualNoPositive);
        var actualWithPositive = validator.number().positive().isValid(-10);
        var expectedWithPositive = false;
        assertEquals(expectedWithPositive, actualWithPositive);
    }

    @Test
    public void rangeTest() {
        var actual = validator.number().range(5, 10);
        var expectedInRange = true;
        assertEquals(expectedInRange, actual.isValid(5));
        var expectedLessRange = false;
        assertEquals(expectedLessRange, actual.isValid(4));
        var expectedMoreRange = false;
        assertEquals(expectedMoreRange, actual.isValid(11));
    }

    @Test
    public void fullTest() {
        var actual = validator.number().required().positive().range(5, 10);
        var expectedTrue = true;
        assertEquals(expectedTrue, actual.isValid(7));
        var expectedFalse = false;
        assertEquals(expectedFalse, actual.isValid(-11));
    }
}
