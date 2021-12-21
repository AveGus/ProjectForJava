import java.util.ArrayList;

public class Theme {
    private final String name;
    private final ArrayList<String> tasks = new ArrayList<>();

    public Theme(String name) {
        this.name = name;
    }

    public ArrayList<String> getTasks() {
        ArrayList<String> clone = new ArrayList<>(tasks.size());
        clone.addAll(tasks);
        return clone;
    }

    public String getName() {
        return name;
    }

    public void addTask(String task) {
        tasks.add(task);
    }
}
