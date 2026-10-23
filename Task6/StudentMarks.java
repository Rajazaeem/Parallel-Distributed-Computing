package Task6;
import java.util.HashMap;
import java.util.Map;

public class StudentMarks {
    public static void main(String[] args) {
        Map<String, Integer> studentMarks = new HashMap<>();

        studentMarks.put("Aisha", 88);
        studentMarks.put("Bilal", 76);
        studentMarks.put("Chen", 91);
        studentMarks.put("Diya", 84);
        studentMarks.put("Omar", 79);

        System.out.println("Student records:");
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        studentMarks.put("Aisha", 94);
        System.out.println("\nAisha's updated marks: " + studentMarks.get("Aisha"));

        String studentToFind = "Omar";
        System.out.println(studentToFind + "'s marks: " + studentMarks.get(studentToFind));
        System.out.println("Total number of students: " + studentMarks.size());
    }
}