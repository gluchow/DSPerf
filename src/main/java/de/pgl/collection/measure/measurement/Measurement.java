package de.pgl.collection.measure.measurement;


import de.pgl.collection.measure.Configs;

public class Measurement {
    private String implClass;
    private String method;
    private int size;
    private Long duration;

    public Measurement(String implClass, String method, int size, Execution execution) {
        this.implClass = implClass;
        this.method = method;
        this.size = size;
        this.duration = execution.durationInNanoseconds();
    }

    public Long getDuration() {
        return duration;
    }

    public String getConfig() {
        return implClass + Configs.VALUE_SEPARATOR + method + Configs.VALUE_SEPARATOR + size;
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
