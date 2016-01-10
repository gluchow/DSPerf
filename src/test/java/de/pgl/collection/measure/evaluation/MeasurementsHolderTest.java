package de.pgl.collection.measure.evaluation;

import de.pgl.collection.measure.Configs;
import de.pgl.collection.measure.measurement.Measurement;
import de.pgl.collection.measure.measurement.list.creator.ArrayListCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MeasurementsHolderTest {

    private MeasurementsHolder holder;

    @Before
    public void setUp() throws Exception {
        holder = new MeasurementsHolder();
    }

    @Test
    public void canAddOneMeasurement() {
        int size = 10;
        List<Integer> list = new ArrayListCreator().createOrderedList(size);
        holder.addMeasure(new Measurement("ArrayList", "find", size, () -> list.indexOf(0)));
        Assert.assertEquals("Should have one measurement.", 1, holder.countOfAllMeasurements());
    }

    @Test
    public void shouldCalculateCorrectMin() {
        String configName = "test_x";
        holder.addMeasure(createMeasurementMockWithDuration(configName, 12L));
        holder.addMeasure(createMeasurementMockWithDuration(configName, 30L));
        holder.addMeasure(createMeasurementMockWithDuration(configName, 10L));
        long actualMin = holder.getMinDurations().get(configName + Configs.VALUE_SEPARATOR + "min");
        Assert.assertEquals(10L, actualMin);
    }

    @Test
    public void shouldCalculateCorrectAvg() {
        String configName = "test_x";
        holder.addMeasure(createMeasurementMockWithDuration(configName, 12L));
        holder.addMeasure(createMeasurementMockWithDuration(configName, 30L));
        holder.addMeasure(createMeasurementMockWithDuration(configName, 10L));
        long actualAvg = holder.getAvgDurations().get(configName + Configs.VALUE_SEPARATOR + "avg");
        Assert.assertEquals(17L, actualAvg);
    }

    @Test
    public void shouldCalculateCorrectMax() {
        String configName = "test_x";
        holder.addMeasure(createMeasurementMockWithDuration(configName, 12L));
        holder.addMeasure(createMeasurementMockWithDuration(configName, 30L));
        holder.addMeasure(createMeasurementMockWithDuration(configName, 10L));
        long actualMax = holder.getMaxDurations().get(configName + Configs.VALUE_SEPARATOR + "max");
        Assert.assertEquals(30L, actualMax);
    }

    private Measurement createMeasurementMockWithDuration(String configName, Long duration) {
        Measurement measurement = mock(Measurement.class);
        when(measurement.getConfig()).thenReturn(configName);
        when(measurement.getDurationInMs()).thenReturn(duration);
        return measurement;
    }

}