package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, String>> {
    private Map<String, Object> schemaData;

    public MapSchema() {
        this.schemaData = new LinkedHashMap<>();
    }

    public MapSchema required() {
        schemaData.put("required", true);
        return this;
    }

    public MapSchema sizeof(Integer sizeValue) {
        schemaData.put("size", sizeValue);
        return this;
    }

    @Override
    public boolean isValid(Map<String, String> map) {
        if (schemaData.containsKey("required")) {
            if (map == null) {
                return false;
            }
        }
        if (schemaData.containsKey("size")) {
            if (map.size() != (int) schemaData.get("size")) {
                return false;
            }
        }
        return true;
    }
}
