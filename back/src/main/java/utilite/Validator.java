package utilite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    private static final String NAME_PATTERN = "^[A-Za-zА-ЯЁа-яё]+$";

    public static boolean validLoginData(String email, String password) {
        boolean result = false;
        if (email != null && password != null) {
            result = valid(email, EMAIL_PATTERN);
        }
        return result;
    }

    public static boolean validRegisterData(String first_name, String last_name, String address, String email, String password, String password_again) {
        boolean result = false;
        if (first_name != null && last_name != null && address != null && email != null && password != null && password.equals(password_again)) {
            result = valid(email, EMAIL_PATTERN) && valid(password, PASSWORD_PATTERN) && valid(first_name, NAME_PATTERN) && valid(last_name, NAME_PATTERN);
            System.out.println(result);
        }
        return result;
    }

    private static boolean valid(String expression, String pattern) {
        Pattern emailPattern = Pattern.compile(pattern);
        Matcher emailMatcher = emailPattern.matcher(expression);
        return emailMatcher.matches();
    }
}
