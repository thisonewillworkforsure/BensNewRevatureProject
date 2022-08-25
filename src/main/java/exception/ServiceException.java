package exception;

public class ServiceException extends Exception {
	@Override
	public String getMessage() {
		return "Internal Database Error! Please try again"
				+ "later!";
	}
}
