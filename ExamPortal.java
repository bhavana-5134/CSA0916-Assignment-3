import java.io.IOException;

class SubmissionException extends Exception {
    public SubmissionException(String message) { super(message); }
}
class SubmissionStorageException extends SubmissionException {
    public SubmissionStorageException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}

class SubmissionStorage {
    public void save(String fileName) throws IOException {
        if (fileName.equals("answer02.pdf")) {
            throw new IOException("Disk quota exceeded");
        }
        System.out.println("Saved " + fileName);
    }
}
class ExamService {
    private final SubmissionStorage storage = new SubmissionStorage();

    public void submit(String fileName) throws SubmissionStorageException {
        try {
            storage.save(fileName);
        } catch (IOException ex) {
            throw new SubmissionStorageException("Could not store " + fileName, ex);
        }
    }
}
public class ExamPortal {
    public static void main(String[] args) {
        ExamService service = new ExamService();
        for (String file : new String[]{"answer01.pdf", "answer02.pdf"}) {
            try {
                service.submit(file);
            } catch (SubmissionStorageException ex) {
                System.out.println("Error: " + ex.getMessage());
                System.out.println("Cause: " + ex.getCause().getMessage());
            }
        }
    }
}
