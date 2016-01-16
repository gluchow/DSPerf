package de.pgl.collection.measure.measurement.set.creator;

import de.pgl.collection.measure.data.Person;
import de.pgl.collection.measure.data.PersonCreator;
import javolution.util.FastSet;

import java.util.Set;

public class PersonFastSetCreator extends AbstractSetCreator<Person> {

    @Override
    public String getImplName() {
        return "FastSet - Person";
    }

    @Override
    public Set<Person> createSet(int size) {
        ensureSizeGreaterZero(size);

        Set<Person> result = new FastSet<>(size);
        for (int i = 0; i < size; i++) {
            result.add(PersonCreator.createPersonWithSuffix(i));
            printCurrentSizeAt100000(i);
        }
        return result;
    }
}
