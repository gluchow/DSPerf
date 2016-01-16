package de.pgl.collection.measure.evaluation;

import de.pgl.collection.measure.Configs;
import de.pgl.collection.measure.measurement.Measurement;

import java.text.DecimalFormat;
import java.util.*;

public class MeasurementsHolder {
    private Map<String, List<Long>> measurements = new HashMap<>();

    public void addMeasure(Measurement measurement) {
        if (!measurements.containsKey(measurement.getConfig())) {
            measurements.put(measurement.getConfig(), new ArrayList<>());
        }
        measurements.get(measurement.getConfig()).add(measurement.getDuration());
    }

    public Map<String, Long> getMinDurations() {
        Map<String, Long> result = new TreeMap<>();
        measurements.forEach((key, durations) -> result.put(key + Configs.VALUE_SEPARATOR + "min", Collections.min(durations)));
        return result;
    }

    public Map<String, Long> getAvgDurations() {
        Map<String, Long> result = new TreeMap<>();
        measurements.forEach((key, durations) -> result.put(key + Configs.VALUE_SEPARATOR + "avg", calculateAvg(durations)));
        return result;
    }

    public Map<String, Long> getMaxDurations() {
        Map<String, Long> result = new TreeMap<>();
        measurements.forEach((key, durations) -> result.put(key + Configs.VALUE_SEPARATOR + "max", Collections.max(durations)));
        return result;
    }

    public Map<String, String> getDurations() {
        Map<String, String> result = new TreeMap<>();
        measurements.forEach((key, durations) -> result.put(key, //
                toMsString(Collections.min(durations)) + Configs.VALUE_SEPARATOR //
                        + toMsString(calculateAvg(durations)) + Configs.VALUE_SEPARATOR //
                        + toMsString(Collections.max(durations))));
        return result;
    }

    private String toMsString(long durationInNano) {
        return DecimalFormat.getInstance().format(durationInNano / 1000000d);
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
