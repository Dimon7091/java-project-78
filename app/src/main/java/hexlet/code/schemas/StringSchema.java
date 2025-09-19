package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;

public final class StringSchema extends BaseSchema<String> {

    private Map<String, Object> schemaData;

    public StringSchema() {
        this.schemaData = new LinkedHashMap<>();
    }

    public StringSchema required() {
        schemaData.put("required", true);
        return this;
    }
    public StringSchema minLength(int minLength) {
        schemaData.put("minLength", minLength);
        return this;
    }
    public StringSchema contains(String str) {
        schemaData.put("contains", str);
        return this;
    }
    @Override
    public boolean isValid(String string) {
        if (schemaData.containsKey("required")) {
            if (string == null || string.isEmpty()) {
                return false;
            }
        }
        if (schemaData.containsKey("minLength")) {
            int minLength = (Integer) schemaData.get("minLength");
            if (string.length() < minLength) {
                return false;
            }
        }
        if (schemaData.containsKey("contains")) {
            String isContainsString = (String) schemaData.get("contains");
            if (!string.contains(isContainsString)) {
                return false;
            }
        }
        return true;
    }

    public Map<String, Object> getSchemaData() {
        return schemaData;
    }
}
