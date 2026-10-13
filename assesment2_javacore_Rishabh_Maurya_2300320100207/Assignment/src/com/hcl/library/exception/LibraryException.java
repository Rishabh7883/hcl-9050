package assesment2_javacore_Rishabh_Maurya_2300320100207.Assignment.src.com.hcl.library.exception;


public class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }

    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}