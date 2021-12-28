import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.knowm.xchart.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;


public class Main {

    public static void main(String[] args) throws ClientException, ApiException, SQLException, ClassNotFoundException {
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
                    student.Sex = element.Sex;
                    Students.add(student);
                }
            }
        }
        PieChartGender(Students);
        HistogrammByCity(Students);
        HistogrammByAge(Students);
    }
    public static void PieChartGender(ArrayList<Student> students){
        var female = 0;
        var male = 0;
        for(Student student: students) {
            if (student.Sex == "MALE"){
                male++;
            }
            else{
                female++;
            }
        }
        var pieChart =new PieChartBuilder().width(800).height(600).title(Main.class.getSimpleName()).build();
        pieChart.addSeries("MALE",male);
        pieChart.addSeries("FEMALE",female);
        new SwingWrapper<>(pieChart).displayChart();
    }
    public static void HistogrammByCity(ArrayList<Student> students){
        ArrayList<String> moreCity = new ArrayList<String>();
        var count = new ArrayList<Integer>();
        var city = "";
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Score Histogram").xAxisTitle("Score").yAxisTitle("Number").build();
        for(Student student: students){
            city = student.City;
            if(moreCity.contains(city)){
                var index = moreCity.indexOf(city);
                count.set(index,count.get(index)+1);
            }else if(city == null){
                if(moreCity.contains("undefined")){
                    var index = moreCity.indexOf("udefined");
                    count.set(index,count.get(index)+1);
                }else{
                    moreCity.add("undefined");
                    count.add(1);
                }
            }else{
                moreCity.add(city);
                count.add(1);
            }
        }
        chart.addSeries("count",moreCity,count);
        new SwingWrapper<>(chart).displayChart().setTitle("Cities");
    }
    public static void HistogrammByAge(ArrayList<Student> students){
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Score Histogram").xAxisTitle("Score").yAxisTitle("Number").build();
        var count = new ArrayList<Integer>();
        var allAges = new ArrayList<String>();
        for(Student student: students){
            int birthdayYear;
            var age = "";
            try{
                birthdayYear = Integer.parseInt(student.birthday.split("\\.")[2]);
                age = Integer.toString(2021-birthdayYear);
                if(allAges.contains(age)){
                    var index = allAges.indexOf(age);
                    count.set(index,count.get(index)+1);
                }else if(age == null){
                    if(allAges.contains("undefined")){
                        var index = age.indexOf("udefined");
                        count.set(index,count.get(index)+1);
                    }else{
                        allAges.add("undefined");
                        count.add(1);
                    }
                }else{
                    allAges.add(age);
                    count.add(1);
                }
            }catch (IndexOutOfBoundsException | NullPointerException exception){
                age = null;
            }


        }
        chart.addSeries("count",allAges,count);
        new SwingWrapper<>(chart).displayChart().setTitle("Cities");
    }
}

