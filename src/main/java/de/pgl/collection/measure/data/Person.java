package de.pgl.collection.measure.data;

public class Person implements Comparable<Person> {
    private String name;
    private String email;
    private int age;
    private String address;

    public Person(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        return !(address != null ? !address.equals(person.address) : person.address != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Person other) {
        int nameCompare = name.compareTo(other.name);
        if (nameCompare != 0) {
            return nameCompare;
        }

        int emailCompare = email.compareTo(other.email);
        if (emailCompare != 0) {
            return emailCompare;
        }

        int addressCompare = address.compareTo(other.address);
        if (addressCompare != 0) {
            return addressCompare;
        }

        return new Integer(age).compareTo(other.age);
    }
}