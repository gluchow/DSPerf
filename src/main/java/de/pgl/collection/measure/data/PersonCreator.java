package de.pgl.collection.measure.data;

import java.util.Random;

public class PersonCreator {
    public static Person createPersonWithSuffix(Integer suffix) {
        return new Person("name_" + suffix, "email_" + suffix, "address_" + suffix);
    }

    public static Person createRandomPerson(Integer range) {
        return createPersonWithSuffix(new Random().nextInt(range));
    }
}
