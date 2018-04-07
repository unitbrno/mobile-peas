package skodavox.peas.unitbrno.cz.skodavoxapp.utils;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SharedState {
    private static SharedState instance;

    private Map<String, Object> store = new HashMap<>();

    public void set(String key, Object value) {
        synchronized (this) {
            if(store.containsKey(key)) throw new RuntimeException("Store must not contain value. Triggered by " + key);
            store.put(key, value);
        }
    }

    public Object get(String key) {
        return store.get(key);
    }

    public static SharedState create() {
        if(instance == null) {
            instance = new SharedState();
        }
        return instance;
    }
}
