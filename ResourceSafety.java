import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class ResourceSafety {
    public static void main(String[] args) {
        String report = "Product,Sales\nPen,120\nBook,90\n";
        try (BufferedReader reader = new BufferedReader(new StringReader(report))) {
            String line;
            System.out.println("Sales report:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.println("Report error: " + ex.getMessage());
        }
        System.out.println("Resources closed automatically by try-with-resources.");
    }
}
