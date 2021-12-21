import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class ThemeMarks {
    private final String name;
    private final LinkedHashMap<String, Integer> marks;

    public ThemeMarks(Theme theme, Integer[] marks) {
        name = theme.getName();
        int index  = 0;
        this.marks = new LinkedHashMap<>();
        for (String task:
                theme.getTasks()) {
            this.marks.put(task, marks[index]);
            index++;
        }
    }

    private ThemeMarks(String name, LinkedHashMap<String, Integer> marks){
        this.name = name;
        this.marks = marks;
    }

    @Override
    public ThemeMarks clone(){
        return new ThemeMarks(name, new LinkedHashMap<>(marks));
    }

    @Override
    public String toString() {
        return name + " [" + marks.values().stream().map(Object::toString).collect(Collectors.joining(", ")) + "]";
    }
}

