package pl.javastart.task;

public class Competitor {
    private String firstName;
    private String lastName;
    private int score;

    public Competitor(String firstName, String lastName, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return  firstName + " " + lastName + "; " + score;
    }
}
