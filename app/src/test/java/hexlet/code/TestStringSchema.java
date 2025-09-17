package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestStringSchema {
    private Validator validator = new Validator();

    @Test
    public void noMethodsTest() {
        var actual = validator.string().isValid("");
        var expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void requiredTest() {
        var actualEmptyStr = validator.string().required().isValid("");
        var expectedEmptyStr = false;
        assertEquals(expectedEmptyStr, actualEmptyStr);
        var actualNullStr = validator.string().required().isValid(null);
        var expectedNullStr = false;
        assertEquals(expectedNullStr, actualNullStr);
        var actualFullStr = validator.string().required().isValid("what does the fox say");
        var expectedFullStr = true;
        assertEquals(expectedFullStr, actualFullStr);
    }

    @Test
    public void minLengthTest() {
        var actualValidStrLength = validator.string().minLength(5).isValid("hexlet");
        var expectedValidStrLength = true;
        assertEquals(expectedValidStrLength, actualValidStrLength);
        var actualNotValidStrLength = validator.string().minLength(5).isValid("hexl");
        var expectedNotValidStrLength = false;
        assertEquals(expectedNotValidStrLength, actualNotValidStrLength);
    }

    @Test
    public void containsTest() {
        var actualContains = validator.string().contains("hex").isValid("hexlet");
        var expectedContains = true;
        assertEquals(expectedContains, actualContains);
        var actualNotContains = validator.string().contains("what").isValid("hexlet");
        var expectedNotContains = false;
        assertEquals(expectedNotContains, actualNotContains);
    }
    @Test
    public void fullMethodsTest() {
        var actual = validator.string().required().minLength(5).contains("hex").isValid("hexlet");
        var expected = true;
        assertEquals(expected, actual);
    }
}
