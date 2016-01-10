package de.pgl.collection.measure.measurement;

import de.pgl.collection.measure.evaluation.MeasurementsHolder;

public class Measurement {
    private String implClass;
    private String method;
    private int size;
    private Long durationInMs;

    public Measurement(String implClass, String method, int size) {
        this.implClass = implClass;
        this.method = method;
        this.size = size;
    }

    public Measurement execute(Execution execution) {
        durationInMs = execution.durationInMs();
        return this;
    }

    public void addToStack() {
        MeasurementsHolder.add(this);
    }

    public void executeAndHoldResult(Execution execution) {
        execute(execution);
        addToStack();
    }

    public Long getDurationInMs() {
        return durationInMs;
    }

    public String getConfig() {
        return String.format("%s-%s-%s", implClass, method, size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measurement that = (Measurement) o;

        if (size != that.size) return false;
        if (!implClass.equals(that.implClass)) return false;
        return method.equals(that.method);

    }

    @Override
    public int hashCode() {
        int result = implClass.hashCode();
        result = 31 * result + method.hashCode();
        result = 31 * result + size;
        return result;
    }

}
