package de.pgl.collection.measure.measurement;

import java.util.Map;
import java.util.TreeMap;

public class MeasurementConfig {
    Map<String, String> configs = new TreeMap<>();

    public void addConfig(String name, String value) {
        configs.put(name, value);
    }

    @Override
    public String toString() {
        return "MeasurementConfig{}";
    }
}
