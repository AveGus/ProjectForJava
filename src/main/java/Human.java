public class Human {
    String fullName;
    String PhotoURL;
    String birthday;
    Long id;
    String City;
    Human(String fullName, String PhotoURL, String birthday, Long id, String City){
        this.fullName = fullName;
        this.birthday = birthday;
        this.PhotoURL = PhotoURL;
        this.id = id;
        this.City = City;
    }

    public Human(String fullName) {
        this.fullName = fullName;
    }


    public String getName() {

        return fullName;
    }
}
