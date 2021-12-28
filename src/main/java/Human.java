public class Human {
    String fullName;
    String PhotoURL;
    String birthday;
    Long id;
    String City;
    String Sex;
    Human(String fullName, String PhotoURL, String birthday, Long id, String City, String Sex){
        this.fullName = fullName;
        this.birthday = birthday;
        this.PhotoURL = PhotoURL;
        this.id = id;
        this.City = City;
        this.Sex = Sex;

    }

    public Human(String fullName) {
        this.fullName = fullName;
    }


    public String getName() {

        return fullName;
    }
}
