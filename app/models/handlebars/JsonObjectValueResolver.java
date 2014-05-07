package models.handlebars;

import com.github.jknack.handlebars.ValueResolver;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.naming.event.ObjectChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sergey Talov on 07.05.2014.
 * email: serg.talov@gmail.com
 */
public class JsonObjectValueResolver implements ValueResolver {

    @Override
    public Object resolve(Object context, String name) {
        Object value = null;
        if (context instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) context;

            JsonElement jsonElement = jsonObject.get(name);
            if (jsonElement != null) {
                if (jsonElement.isJsonObject()) {
                    value = jsonElement.getAsJsonObject();
                } else if (jsonElement.isJsonArray()) {
                    value = jsonElement.getAsJsonArray();
                } else {
                    value = jsonElement.getAsString();
                }
            }
        }

        return value == null ? UNRESOLVED : value;
    }

    @Override
    public Object resolve(Object context) {
        return UNRESOLVED;
    }

    @Override
    public Set<Map.Entry<String, Object>> propertySet(Object context) {
        JsonObject jsonContext = (JsonObject) context;
        Map<String, Object> result = new HashMap<String, Object>();
        for (Map.Entry<String, JsonElement> entry : jsonContext.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result.entrySet();
    }
}
