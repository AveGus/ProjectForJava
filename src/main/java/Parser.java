import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Parser {

    private final ArrayList<Student> students = new ArrayList<>();
    private final String path;

    public Parser(String path) {
        this.path = path;
    }

    public Parser parse() {
        try(FileReader file = new FileReader(path);
            BufferedReader reader = new BufferedReader(file)) {
            ArrayList<Theme> themes = parseThemes(reader.readLine(), reader.readLine());
            String[] lines = reader.lines().skip(1).toArray(String[]::new);
            for (String student :
                    lines) {
                String[] data = student.split(";");
                String name = data[0];
                String group = data[1];


                ArrayList<ThemeMarks> allMarks = new ArrayList<>();
                int themeIndex = 0;
                ArrayList<Integer> themeMarks = new ArrayList<>();
                for (int i = 2; i < data.length; i++) {
                    int themeMarksMaxCount = themes.get(themeIndex).getTasks().size();
                    int themeEnd = i + themeMarksMaxCount;
                    for (; i < themeEnd; i++) {
                        themeMarks.add(Integer.parseInt(data[i]));
                    }
                    allMarks.add(new ThemeMarks(themes.get(themeIndex), themeMarks.toArray(new Integer[0])));
                    themeMarks.clear();
                    themeIndex++;
                    i--;
                }
                students.add(new Student(name, group, allMarks));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private ArrayList<Theme> parseThemes(String line1, String line2) {
        Stack<Theme> themes = new Stack<>();
        ArrayList<String> themeNames = new ArrayList<>(Arrays.asList(line1.split(";")));
        ArrayList<String> taskNames = new ArrayList<>(Arrays.asList(line2.split(";")));
        if(taskNames.size() > themeNames.size()){
            for (int k = 0; k < taskNames.size() - themeNames.size(); k++) {
                themeNames.add("");
            }
        } else if (taskNames.size() < themeNames.size()){
            for (int k = 0; k < themeNames.size() - taskNames.size(); k++) {
                taskNames.add("");
            }
        }
        String[] themesArray = themeNames.toArray(new String[0]);
        String[] tasksArray = taskNames.toArray(new String[0]);
        for (int i = 2; i < taskNames.size(); i++) {
            if (!themesArray[i].equals("")){
                themes.push(new Theme(themesArray[i]));
            }
            themes.peek().addTask(tasksArray[i]);
        }
        return new ArrayList<>(themes);
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> clone = new ArrayList<>();
        for (Student student :
                students) {
            clone.add(student.clone());
        }
        return clone;
    }

    public static int[] toInt(Integer[] WrapperArray) {

        int[] newArray = new int[WrapperArray.length];
        for (int i = 0; i < WrapperArray.length; i++) {
            newArray[i] = WrapperArray[i];
        }
        return newArray;
    }
}

