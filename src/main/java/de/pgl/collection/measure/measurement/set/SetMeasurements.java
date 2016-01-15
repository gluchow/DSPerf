package de.pgl.collection.measure.measurement.set;

import de.pgl.collection.measure.Configs;
import de.pgl.collection.measure.evaluation.MeasurementsHolder;
import de.pgl.collection.measure.io.MeasurementsWriter;
import de.pgl.collection.measure.measurement.Measurement;
import de.pgl.collection.measure.measurement.set.creator.FastSetCreator;
import de.pgl.collection.measure.measurement.set.creator.HashSetCreator;
import de.pgl.collection.measure.measurement.set.creator.SetCreator;
import de.pgl.collection.measure.measurement.set.creator.TreeSetCreator;

import java.util.Random;
import java.util.Set;

public class SetMeasurements {

    private final int size;
    private MeasurementsHolder measurementsHolder = new MeasurementsHolder();

    public SetMeasurements(int size) {
        this.size = size;
    }

    public void performMeasurements() {
        for (int i = 0; i < Configs.PERFORM_REPETITION; i++) {
            proceedListOperations(new TreeSetCreator());
            proceedListOperations(new HashSetCreator());
            proceedListOperations(new FastSetCreator());
        }

        MeasurementsWriter.writeMeasurements(measurementsHolder.getDurations());
//        MeasurementsWriter.writeMeasurements(measurementsHolder.getAvgDurations());
//        MeasurementsWriter.writeMeasurements(measurementsHolder.getMaxDurations());
    }

    private void proceedListOperations(SetCreator<Integer> setCreator) {
        String implName = setCreator.getImplName();

        measurementsHolder.addMeasure(new Measurement(implName, "create", size,
                () -> setCreator.createSet(size)));

        Set<Integer> set = setCreator.createSet(size);

        measurementsHolder.addMeasure(new Measurement(implName, "contains", size,
                () -> set.contains(random())));

        // TODO: Mengenoperationen

        measurementsHolder.addMeasure(new Measurement(implName, "remove", size,
                () -> set.remove(random())));

    }

    private int random() {
        return randomInt(size - 1);
    }

    private static int randomInt(int max) {
        return new Random().nextInt(max);
    }
}
