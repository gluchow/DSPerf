package de.pgl.collection.measure.io;

import de.pgl.collection.measure.Configs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class MeasurementsWriter {
    public static final String MEASURE_LOG_FILENAME = "result.csv";

    private static PrintWriter writer = null;

    private static void init() {
        try {
            writer = new PrintWriter(new FileWriter(MEASURE_LOG_FILENAME, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void writeMeasurements(Map<String, Long> measurements) {
//        init();
//        measurements.forEach(MeasurementsWriter::writeLine);
//        writer.close();
//    }

    public static void writeMeasurements(Map<String, String> measurements) {
        init();
        measurements.forEach(MeasurementsWriter::writeLine);
        writer.close();
    }

    private static void writeLine(String measurementData) {
        writer.println(measurementData);
        System.out.println(measurementData);
    }

    private static void writeLine(String measurementConfig, String duration) {
        writeLine(measurementConfig + Configs.VALUE_SEPARATOR + duration);
    }

}
