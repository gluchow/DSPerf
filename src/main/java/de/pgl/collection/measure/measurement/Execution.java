package de.pgl.collection.measure.measurement;

@FunctionalInterface
public interface Execution {

    void execute();

    default long durationInMs() {
        long start = System.currentTimeMillis();
        execute();
        return System.currentTimeMillis() - start;
    }

    default long durationInNanoseconds() {
        long start = System.nanoTime();
        execute();
        return System.nanoTime() - start;
    }
}
