package de.pgl.collection.measure.measurement.list;

import de.pgl.collection.measure.Configs;
import de.pgl.collection.measure.data.Person;
import de.pgl.collection.measure.data.PersonCreator;
import de.pgl.collection.measure.evaluation.MeasurementsHolder;
import de.pgl.collection.measure.io.MeasurementsWriter;
import de.pgl.collection.measure.measurement.Measurement;
import de.pgl.collection.measure.measurement.list.creator.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PersonListMeasurements {
    private final int size;
    private MeasurementsHolder measurementsHolder = new MeasurementsHolder();

    public PersonListMeasurements(int size) {
        this.size = size;
    }

    public void performMeasurements() {
        for (int i = 0; i < Configs.PERFORM_REPETITION; i++) {
            proceedListOperations(new PersonArrayListCreator());
            proceedListOperations(new PersonLinkedListCreator());
            proceedListOperations(new PersonGapListCreator());
            proceedListOperations(new PersonBigListCreator());
            proceedListOperations(new PersonTreeListCreator());
            proceedListOperations(new PersonFastTableCreator());
        }

        MeasurementsWriter.writeMeasurements(measurementsHolder.getDurations());
    }

    private void proceedListOperations(ListCreator<Person> listCreator) {
        String implName = listCreator.getImplName();

        measurementsHolder.addMeasure(new Measurement(implName, "create", size,
                () -> listCreator.createOrderedList(size)));

        List<Person> list = listCreator.createOrderedList(size);

        measurementsHolder.addMeasure(new Measurement(implName, "find_first", size,
                () -> list.indexOf(firstPerson())));
        measurementsHolder.addMeasure(new Measurement(implName, "find_random", size,
                () -> list.indexOf(randomPerson())));
        measurementsHolder.addMeasure(new Measurement(implName, "find_last", size,
                () -> list.indexOf(lastPerson())));

        measurementsHolder.addMeasure(new Measurement(implName, "get_first", size,
                () -> list.get(firstPosition())));
        measurementsHolder.addMeasure(new Measurement(implName, "get_random", size,
                () -> list.get(centerPosition())));
        measurementsHolder.addMeasure(new Measurement(implName, "get_last", size,
                () -> list.get(lastPosition())));

        measurementsHolder.addMeasure(new Measurement(implName, "add_first", size,
                () -> list.add(lastPerson())));
        measurementsHolder.addMeasure(new Measurement(implName, "add_random", size,
                () -> list.add(centerPosition(), lastPerson())));
        measurementsHolder.addMeasure(new Measurement(implName, "add_last", size,
                () -> list.add(firstPosition(), lastPerson())));

        measurementsHolder.addMeasure(new Measurement(implName, "remove_first", size,
                () -> list.remove(firstPosition())));
        measurementsHolder.addMeasure(new Measurement(implName, "remove_random", size,
                () -> list.remove(centerPosition())));
        measurementsHolder.addMeasure(new Measurement(implName, "remove_last", size,
                () -> list.remove(lastPosition())));


        measurementsHolder.addMeasure(new Measurement(implName, "size", size,
                list::size));

        measurementsHolder.addMeasure(new Measurement(implName, "replace at center", size,
                () -> list.set(centerPosition(), PersonCreator.createPersonWithSuffix(222))));

        measurementsHolder.addMeasure(new Measurement(implName, "create sublist of 500 elements by index", size,
                () -> list.subList(size / 4, size / 4 + 500)));


        // Tests with other lists
        List<Person> randomListToSort = listCreator.createRandomList(size);
        measurementsHolder.addMeasure(new Measurement(implName, "sort a random list", size,
                () -> Collections.sort(randomListToSort)));

        List<Person> orderedList = listCreator.createOrderedList(500);
        measurementsHolder.addMeasure(new Measurement(implName, "contains ordered list of 500 elements", size,
                () -> list.containsAll(orderedList)));

        List<Person> randomList = listCreator.createRandomList(size).subList(size / 2, size / 2 + 500);
        measurementsHolder.addMeasure(new Measurement(implName, "contains random list of max 500 elements", size,
                () -> list.containsAll(randomList)));


        measurementsHolder.addMeasure(new Measurement(implName, "add ordered list of 500 elements - last position", size,
                () -> list.addAll(orderedList)));
        // reset:
        removeLast500(list);

        measurementsHolder.addMeasure(new Measurement(implName, "add random list of 500 max elements - last position", size,
                () -> list.addAll(randomList)));
        // reset:
        removeLast500(list);

        measurementsHolder.addMeasure(new Measurement(implName, "add ordered list of 500 elements - first position", size,
                () -> list.addAll(firstPosition(), orderedList)));
        // reset:
        removeFirst500(list);

        measurementsHolder.addMeasure(new Measurement(implName, "add random list of max 500 elements - first position", size,
                () -> list.addAll(firstPosition(), randomList)));
        // reset:
        removeFirst500(list);

        measurementsHolder.addMeasure(new Measurement(implName, "add ordered list of 500 elements - center position", size,
                () -> list.addAll(centerPosition(), orderedList)));
        // reset:
        removeCenter500(list);

        measurementsHolder.addMeasure(new Measurement(implName, "add random list of max 500 elements - center position", size,
                () -> list.addAll(centerPosition(), randomList)));
        // reset:
        removeCenter500(list);


        measurementsHolder.addMeasure(new Measurement(implName, "remove ordered list of 500 elements", size,
                () -> list.removeAll(orderedList)));
        // reset:
        list.addAll(orderedList);
        Collections.sort(list);

        measurementsHolder.addMeasure(new Measurement(implName, "remove random list of max 500 elements", size,
                () -> list.removeAll(randomList)));
        // reset:
        list.addAll(randomList);
        Collections.sort(list);

        measurementsHolder.addMeasure(new Measurement(implName, "retain ordered list of 500 elements", size,
                () -> list.retainAll(orderedList)));


        // Iterator tests
        List<Person> newOrderedList = listCreator.createOrderedList(size);
        measurementsHolder.addMeasure(new Measurement(implName, "Iterator - iterate all", size,
                () -> {
                    Iterator<Person> iterator = newOrderedList.iterator();
                    while (iterator.hasNext()) {
                        iterator.next(); // just call
                    }
                }));

        measurementsHolder.addMeasure(new Measurement(implName, "remove local 500 elements - first position", size,
                () -> removeFirst500(newOrderedList)));
        // reset:
        newOrderedList.addAll(firstPosition(), orderedList);

        ArrayList<Integer> randomIndexes = HelperUtils.create500RandomIndexesInRange(size);
        measurementsHolder.addMeasure(new Measurement(implName, "remove 500 elements at random index", size,
                () -> randomIndexes.forEach(newOrderedList::remove)));

    }

    private void removeCenter500(List<Person> list) {
        for (int i = centerPosition(); i < centerPosition() + 500; i++) {
            list.remove(centerPosition());
        }
    }

    private void removeFirst500(List<Person> list) {
        for (int i = 0; i < 500; i++) {
            list.remove(0);
        }
    }

    private void removeLast500(List<Person> list) {
        for (int i = size + 499; i >= size; i--) {
            list.remove(i);
        }
    }

    private Person firstPerson() {
        return PersonCreator.createPersonWithSuffix(firstPosition());
    }

    private Person randomPerson() {
        return PersonCreator.createRandomPerson(lastPosition());
    }

    private Person lastPerson() {
        return PersonCreator.createPersonWithSuffix(lastPosition());
    }

    private int centerPosition() {
        return size / 2;
    }

    private int lastPosition() {
        return size - 1;
    }

    private int firstPosition() {
        return 0;
    }

}