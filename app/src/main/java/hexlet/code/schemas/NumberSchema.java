package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;

public class NumberSchema extends BaseSchema<Integer> {
    private Map<String, Object> schemaDate;

    public NumberSchema() {
        this.schemaDate = new LinkedHashMap<>();
    }

    public NumberSchema required() {
        schemaDate.put("required", true);
        return this;
    }

    public NumberSchema positive() {
        schemaDate.put("positive", true);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Map<String, Integer> value = new LinkedHashMap<>();
        value.put("min", min);
        value.put("max", max);
        schemaDate.put("range", value);
        return this;
    }
    @Override
    public boolean isValid(Integer number) {
        if (schemaDate.containsKey("required")) {
            if (number == null) {
                return false;
            }
        }
        if (schemaDate.containsKey("positive")) {
            if (number <= 0) {
                return false;
            }
        }
        if (schemaDate.containsKey("range")) {
            @SuppressWarnings("unchecked")
            Map<String, Integer> range = (Map<String, Integer>) schemaDate.get("range");
            var min = range.get("min");
            var max = range.get("max");
            if (number < min || number > max) {
                return false;
            }
        }
        return true;
    }

}
