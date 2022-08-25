package exception;

public class ApplicationException extends Exception {
	@Override
	public String getMessage() {
		return "Internal Database Error! Please try again"
				+ "later!";
	}
}
