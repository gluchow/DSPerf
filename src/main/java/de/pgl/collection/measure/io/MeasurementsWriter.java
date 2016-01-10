package de.pgl.collection.measure.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class MeasurementsWriter {
    public static final String MEASURE_LOG_FILENAME = "result.txt";
    private static final String SEPARATOR = ":";

    private static PrintWriter writer = null;

    private static void init() {
        try {
            writer = new PrintWriter(new FileWriter(MEASURE_LOG_FILENAME, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        writer.close();
    }

    public static void writeLine(String measurementData) {
        if (writer == null) {
            init();
        }
        writer.println(measurementData);
    }

    public static void writeLine(String measurementConfig, Long duration) {
        writeLine(measurementConfig + SEPARATOR + duration);
    }

    public static void writeMeasurements(Map<String, Long> measurements) {
        measurements.forEach(MeasurementsWriter::writeLine);
    }

}
