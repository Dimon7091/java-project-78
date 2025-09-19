package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class NumberSchema extends BaseSchema<Integer> {
    private Map<String, Object> schemaData;

    public NumberSchema() {
        this.schemaData = new LinkedHashMap<>();
    }

    public NumberSchema required() {
        schemaData.put("required", true);
        return this;
    }

    public NumberSchema positive() {
        schemaData.put("positive", true);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Map<String, Integer> value = new LinkedHashMap<>();
        value.put("min", min);
        value.put("max", max);
        schemaData.put("range", value);
        return this;
    }
    @Override
    public boolean isValid(Integer number) {
        if (schemaData.containsKey("required")) {
            if (number == null) {
                return false;
            }
        }
        if (number == null) {
            // если число не required и null, то считаем валидным
            return true;
        }
        if (schemaData.containsKey("positive")) {
            Optional<Integer> opt = Optional.ofNullable(number);
            if (number == null || number <= 0) {
                return false;
            }
        }
        if (schemaData.containsKey("range")) {
            @SuppressWarnings("unchecked")
            Map<String, Integer> range = (Map<String, Integer>) schemaData.get("range");
            var min = range.get("min");
            var max = range.get("max");
            if (number == null) {
                return false;
            } else if (number < min || number > max) {
                return false;
            }
        }
        return true;
    }

}
