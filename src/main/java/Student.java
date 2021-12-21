import java.util.ArrayList;

public class Student extends Human {

    private final String group;
    private final ArrayList<ThemeMarks> marks;

    public Student(String name, String group, ArrayList<ThemeMarks> marks) {
        super(name);
        this.group = group;
        this.marks = marks;
    }
    public String getGroup() {

        return group;
    }
    public ArrayList<ThemeMarks> getMarks() {
        ArrayList<ThemeMarks> clone = new ArrayList<>(marks.size());
        for (ThemeMarks item : marks) {
            clone.add(item.clone());
        }
        return clone;
    }

    @Override
    public Student clone() {
        return new Student(getName(), group, getMarks());
    }

    @Override
    public String toString() {
        return getName() + ", " + group;
    }
}
