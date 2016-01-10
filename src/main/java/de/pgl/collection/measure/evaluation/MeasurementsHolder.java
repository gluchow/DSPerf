package de.pgl.collection.measure.evaluation;

import de.pgl.collection.measure.measurement.Measurement;

import java.util.*;

public class MeasurementsHolder {
    private Map<String, List<Long>> measurements = new HashMap<>();

    public void addMeasure(Measurement measurement) {
        if (!measurements.containsKey(measurement.getConfig())) {
            measurements.put(measurement.getConfig(), new ArrayList<>());
        }
        measurements.get(measurement.getConfig()).add(measurement.getDurationInMs());
    }

    public Map<String, Long> getMinDurations() {
        Map<String, Long> result = new TreeMap<>();
        measurements.forEach((key, durations) -> result.put(key + "-min", Collections.min(durations)));
        return result;
    }

    public Map<String, Long> getAvgDurations() {
        Map<String, Long> result = new TreeMap<>();
        measurements.forEach((key, durations) -> result.put(key + "-avg", calculateAvg(durations)));
        return result;
    }

    public Map<String, Long> getMaxDurations() {
        Map<String, Long> result = new TreeMap<>();
        measurements.forEach((key, durations) -> result.put(key + "-max", Collections.max(durations)));
        return result;
    }

    private Long calculateAvg(List<Long> durations) {
        return durations.stream().reduce(0L, (sum, duration) -> sum + duration) / durations.size();
    }

    public int countOfAllMeasurements() {
        int count = 0;
        for (Map.Entry<String, List<Long>> entry : measurements.entrySet()) {
            count += entry.getValue().size();
        }
        return count;
    }
}
