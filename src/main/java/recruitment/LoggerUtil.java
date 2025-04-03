
package recruitment;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerUtil {
    private static final String FILE_PATH = "logs/system.log";

    public static void log(String message) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write("[" + LocalDateTime.now() + "] " + message + "\n");
        } catch (IOException e) {
            System.err.println("Log write failed: " + e.getMessage());
        }
    }
}
