package de.pgl.collection.measure.evaluation;

import de.pgl.collection.measure.measurement.Measurement;

import java.util.*;

public class MeasurementsHolder {
    private static Map<String, List<Long>> measurements = new HashMap<>();

    public static void add(Measurement measurement) {
        if (!measurements.containsKey(measurement.getConfig())) {
            measurements.put(measurement.getConfig(), new ArrayList<>());
        }
        measurements.get(measurement.getConfig()).add(measurement.getDurationInMs());
    }

    public static Map<String, Long> getMinDurations() {
        Map<String, Long> result = new TreeMap<>();
        measurements.forEach((key, durations) -> result.put(key + "-min", Collections.min(durations)));
        return result;
    }

    public static Map<String, Long> getAvgDurations() {
        Map<String, Long> result = new TreeMap<>();
        measurements.forEach((key, durations) -> result.put(key + "-avg", calculateAvg(durations)));
        return result;
    }

    public static Map<String, Long> getMaxDurations() {
        Map<String, Long> result = new TreeMap<>();
        measurements.forEach((key, durations) -> result.put(key + "-max", Collections.max(durations)));
        return result;
    }

    private static Long calculateAvg(List<Long> durations) {
        return durations.stream().reduce(0L, (sum, duration) -> sum + duration) / durations.size();
    }
}
