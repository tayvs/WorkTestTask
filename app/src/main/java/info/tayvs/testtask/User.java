package info.tayvs.testtask;

/**
 * Created by tayvs on 30.11.2016.
 */

public class User {

    final String firstName, lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User o = (User) obj;
            return (o.getFirstName().equals(getFirstName())) && (o.getLastName().equals(getLastName()));
        } return false;
    }
}
