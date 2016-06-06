package pratik.com.droidutils;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PRATIK on 02-Jun-16.
 */
public class Validator {

    /**
     * USER NAME VALIDATION
     *
     * @param context  activity
     * @param username String
     * @param length   int
     * @return boolean
     */
    public static boolean isValidUserName(Context context, String username, int length) {

        if (username.trim().length() >= length) {
            //VALID USER NAME
            return true;
        } else {
            Toast.makeText(context, "User name should be " + length + " character", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * PASSWORD VALIDATION
     *
     * @param context  activity
     * @param password String
     * @param length   int
     * @return boolean
     */
    public static boolean isValidPassword(Context context, String password, int length) {

        if (password.trim().length() >= length) {
            //VALID PASSWORD
            return true;
        } else {
            Toast.makeText(context, "Password should be " + length + " character", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * PASSWORD VALIDATION
     *
     * @param context       activity
     * @param password      String
     * @param length        int
     * @param isSpecialchar boolean is special character allowed
     * @param isNumber      boolean is number allowed
     * @return boolean
     */
    public static boolean isValidPassword(Context context, String password, int length, boolean isSpecialchar, boolean isNumber) {

        if (password.trim().length() >= length) {
            //LENGTH IS GOOD
            Pattern p = Pattern.compile("(?=.*[A-Za-z])");
            Matcher m = p.matcher(password.trim());
            //VALIDATE PASSWORD CONTAINS SPECIAL CHARACTER
            if (m.find()) {
                if (isSpecialchar) {
                    Pattern p1 = Pattern.compile("(?=.*[!@#$%&*_])");
                    Matcher m1 = p1.matcher(password.trim());
                    if (m1.find()) {
                        //VALIDATE PASSWORD CONTAINS NUMBER
                        if (isNumber) {
                            Pattern p2 = Pattern.compile("(?=.*\\d)");
                            Matcher m2 = p2.matcher(password.trim());
                            if (m2.find())
                                return true;
                            else {
                                Toast.makeText(context, "Password must contain atleast 1 number", Toast.LENGTH_LONG).show();
                                return false;
                            }
                        } else {
                            return true;
                        }
                    } else {
                        Toast.makeText(context, "Password must contain atleast 1 special character", Toast.LENGTH_LONG).show();
                        return false;
                    }
                } else {
                    if (isNumber) {
                        Pattern p2 = Pattern.compile("(?=.*\\d)");
                        Matcher m2 = p2.matcher(password.trim());
                        if (m2.find())
                            return true;
                        else {
                            Toast.makeText(context, "Password must contain atleast 1 number", Toast.LENGTH_LONG).show();
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            } else {
                Toast.makeText(context, "Password must contain atleast 1 alphabet", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(context, "Password should be " + length + " character", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * CHECK EMAIL IS VALID OR NOT
     *
     * @param context       activity
     * @param email_address String
     * @return boolean
     */
    public static boolean isValidEmail(Context context, String email_address) {

        Pattern EMAIL_ADDRESS_PATTERN = Pattern
                .compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (EMAIL_ADDRESS_PATTERN.matcher(email_address.trim()).matches()) {
            return true;
        } else {
            Toast.makeText(context, "Invalid email address", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * CHECK MOBILE IS VALID OR NOT
     *
     * @param context       activity
     * @param mobile_number String
     * @return boolean
     */
    public static boolean isValidMobile_no(Context context, String mobile_number) {
        Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("^([1-9]{1})*([0-9]{9})$");
        if (EMAIL_ADDRESS_PATTERN.matcher(mobile_number.trim()).matches()) {
            return true;
        } else {
            Toast.makeText(context, "Invalid Mobile Number", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * CHACK DATE IS VALID OR NOT
     *
     * @param context activity
     * @param date    String
     * @return boolean
     */
    public static boolean isValidDate(Context context, String date) {
        Pattern DATE_PATTERN = Pattern.compile("^([0-9]{2})*-([A-Z,a-z]{3})*-([0-9]{4})$");
        if (DATE_PATTERN.matcher(date).matches()) {
            return true;
        } else {
            Toast.makeText(context, "Invalid date", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
