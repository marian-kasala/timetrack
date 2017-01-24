package sk.lumba.timetrack.exception;

public class LegacyApplException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public LegacyApplException(String message) {
        super(message);
    }

    public LegacyApplException(String message, Throwable cause) {
        super(message, cause);
    }

}
