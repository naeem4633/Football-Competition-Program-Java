public class Competitor {
    private int number;
    private Name name;
    private String dateOfBirth;
    private String category;
    private int age;
    private String email;

    public Competitor(int number, Name name, String dateOfBirth, String category, int age, String email) {
        this.number = number;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.category = category;
        this.age = age;
        this.email = email;
    }

    public int getNumber() {
        return number;
    }

    public Name getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCategory() {
        return category;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
