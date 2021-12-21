import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws ClientException, ApiException {
        Parser parser = new Parser("src/main/resources/java-rtf.csv").parse();
        var jsonFromVK = VkGroups.groupList();
        var Humans = VkGroups.jsonDEARCHIVE(jsonFromVK);
        var Students = new ArrayList<Student>();
        for (Human element: Humans){
            for(Student student: parser.getStudents()){
                if(element.fullName.contains(student.fullName)){
                    student.birthday = element.birthday;
                    student.City = element.City;
                    student.id = element.id;
                    student.PhotoURL = element.PhotoURL;
                    Students.add(student);
                }
            }
        }
        System.out.println(Students);
    }
}

