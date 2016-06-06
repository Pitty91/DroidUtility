package pratik.com.droidutils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by PRATIK on 06-Jun-16.
 */
public class Utility {

    /**
     * CHECK INTERNET IS AVAILABLE
     *
     * @param context activity
     * @return boolean
     */
    public static boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            return nInfo != null && nInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * CHECK STRING NOT CONTAINS NULL
     *
     * @param str String
     * @return boolean
     */
    public static boolean isNotNull(String str) {
        return !(str == null || str.equalsIgnoreCase("null") || str.trim().length() == 0);
    }

    /**
     * GET PROGRESS DIALOGE
     *
     * @param context       activity
     * @param dialogMessage String
     * @param isCancallable boolean
     * @return boolean
     */
    public static ProgressDialog getProgressDialog(Context context, String dialogMessage, boolean isCancallable) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(isCancallable);
        progressDialog.setMessage(dialogMessage);
        return progressDialog;
    }

    //HIDE KEYBOARD
    public static void HideKeyboard(Context ctx, View view) {
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //GET CURRENT DATE
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDate(String dateFormat) {
        Date d = new Date();
        //"MMM dd, yyyy"
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        return formatter.format(d);
    }

    //GET CURRENT TIME
    public static String getCurrentTime() {
        String Time = "";
        final Calendar c = Calendar.getInstance();
        // c.setTimeZone(TimeZone.getTimeZone(UserPreferenceManager.preferenceGetString(AppConstants.SharedPreferenceKeys.TIMEZONE,"")));

        int hr = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        String am_pm = null;

        String min = null;
        if (minute > 0 && minute < 10) {
            min = String.valueOf(minute);
            min = "0" + min;
        } else {
            min = String.valueOf(minute);
        }

        if (c.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (c.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";

        Time = hr + ":" + min + " " + am_pm;

        Log.d("current time-", Time);

        return Time;
    }

    //SHOW TOAST
    public static void showToast(Context ctx, String Message) {
        Toast toast = Toast.makeText(ctx, Message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * CHANGE DATE TIME FORMAT
     *
     * @param dateTime   String for date time
     * @param fromFormat String date formate
     * @param toFormat   String target formate
     * @return String date formate
     */
    public static String changeDateTimeFormat(String dateTime, String fromFormat, String toFormat) {
        try {
            SimpleDateFormat sdfFrom = new SimpleDateFormat(fromFormat, Locale.getDefault());
            Date date = sdfFrom.parse(dateTime);
            SimpleDateFormat sdfTo = new SimpleDateFormat(toFormat, Locale.getDefault());
            return sdfTo.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * GET CURRENT DATE
     *
     * @return String date
     */
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        int myYear = c.get(Calendar.YEAR);
        int myMonth = c.get(Calendar.MONTH);
        int myDay = c.get(Calendar.DAY_OF_MONTH);
        String moy1;
        String dom1;

        if (myMonth < 9) {
            moy1 = "0" + String.valueOf(myMonth + 1);
        } else {
            moy1 = String.valueOf(myMonth + 1);
        }
        if (myDay < 9) {
            dom1 = "0" + String.valueOf(myDay);
        } else {
            dom1 = String.valueOf(myDay);
        }
        return String.valueOf(myYear) + "-" + moy1 + "-" + dom1;
    }

    /**
     * GET DEVICE NAME DETAILS
     *
     * @return String date
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model) + " (OS: " + Build.VERSION.SDK_INT + ")";
        } else {
            return capitalize(manufacturer) + " - " + model + " (OS: " + Build.VERSION.SDK_INT + ")";
        }
    }

    /**
     * CAPITALIZE STRING
     *
     * @param s String
     * @return String
     */
    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    /**
     * SHOW DIALOGE ALERT
     *
     * @param context activity
     * @param resId   intiger
     */
    public static void showAlert(Context context, int resId) {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(context);
        alertDialog.setMessage(resId);
        alertDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        android.app.AlertDialog dialog = alertDialog.create();
        dialog.show();

    }

    /**
     * IS NETWORK AVAILABLE OR NOT
     *
     * @param context activity
     * @return boolean
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && ping("https://www.bing.com/");
    }

    /**
     * PING FUNCTION TO CHECK INTERNET SPEED
     *
     * @param u String
     * @return boolean
     */
    public static boolean ping(final String u) {
        try {
            final boolean[] isPing = {false};
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(u);
                        HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                        urlc.setConnectTimeout(500); // Time is in Milliseconds to wait for ping response
                        urlc.connect();
                        InputStreamReader in = new InputStreamReader((InputStream) urlc.getContent());
                        BufferedReader buff = new BufferedReader(in);
                        String line;
                        StringBuilder text = new StringBuilder();
                        do {
                            line = buff.readLine();
                            text.append(line).append("\n");
                        } while (line != null);
                        //Log.e(TAG, "TEXT : " + text.toString());
                        //Log.e(TAG, "TEXT LENGTH: " + text.toString().length());
                        if (text.toString().length() < 400) {
                            isPing[0] = false;
                        }
                        Log.e("PRATIK UTILS", "URLC RESPONCECODE : gsdfgdfgd");
                        //Log.e(TAG, "URLC RESPONCECODE :" + urlc.getResponseCode());
                        isPing[0] = urlc.getResponseCode() == 200;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            th.start();
            th.join();
            return isPing[0];

        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }
}
