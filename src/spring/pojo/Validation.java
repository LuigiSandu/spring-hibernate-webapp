package spring.pojo;

public class Validation {

	public static String fieldEmptyError = "Field cannot be empty.";
	public static String userUnavailableError = "Username unavailable.";
	public static String emailUnavailableError = "Email unavailable.";
	public static String oldUserUnavailableError = "Your new username cannot be the old username.";
	public static String oldPassUnavailableError = "Your new password cannot be the old password.";
	public static String oldEmailUnavailableError = "Your new email cannot be the old email.";
	public static String passLengthError = "Password must be at least 8 characters long.";
	
	public static boolean validateCardNumber(String cardNumber) {

		if (cardNumber.length() != 19)
			return false;

		if (cardNumber.charAt(4) != '-' || cardNumber.charAt(9) != '-' || cardNumber.charAt(14) != '-')
			return false;

		for (int i = 0; i < cardNumber.length(); i++) 
		{
			if (i != 4 && i != 9 && i != 14) 
			{
				if (cardNumber.charAt(i) <= 48 || cardNumber.charAt(i) >= 57)
				{
					return false;
				}
			}
		}

		return true;
	}

	public static boolean validateCVC(String cvc) {

		if (cvc.length() != 3) {
			return false;
		}

		for (int i = 0; i < 3; i++) 
		{
			
			if (cvc.charAt(i) <= 48 || cvc.charAt(i) >= 57) 
			{
				return false;
			}
			
		}
		
		
		return true;
	}
}
