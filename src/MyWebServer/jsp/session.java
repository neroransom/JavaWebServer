package mywebserver.jsp;

import java.util.HashMap;
import java.util.Map;

public class session {
    public session() {

    }

    private static Map<String, Object> attribute = new HashMap<>();

    public void setAttribute(String key, Object o) {
        attribute.put(key, o);
    }

    public Object getAttribute(String key) {
        return attribute.get(key);
    }
}