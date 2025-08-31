package hexlet.code.shemas;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringSchema {

    private Map<String, Object> shemaData;

    public StringSchema() {
        this.shemaData = new LinkedHashMap<>();
    }

    public StringSchema required() {
        shemaData.put("required", true);
        return this;
    }
    public StringSchema minLength(int minLength) {
        shemaData.put("minLength", minLength);
        return this;
    }
    public StringSchema contains(String str) {
        shemaData.put("contains", str);
        return this;
    }
    public boolean isValid(String string) {
        if (shemaData.containsKey("required")) {
            if (string == null || string.isEmpty()) {
                return false;
            }
        }
        if (shemaData.containsKey("minLength")) {
            int minLength = (Integer) shemaData.get("minLength");
            if (string.length() < minLength) {
                return false;
            }
        }
        if (shemaData.containsKey("contains")) {
            String isContainsString = (String) shemaData.get("contains");
            if (!string.contains(isContainsString)) {
                return false;
            }
        }
        return true;
    }
}
