package skodavox.peas.unitbrno.cz.skodavoxapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static final String SHARED_PREFERENCES = "sharedPreferences";
    ;
    private static final String SHARED_PREFERENCES_EMAIL = "email";
    ;

    public static void saveEmailOfCurrentUser(Activity activity, String userEmail) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SHARED_PREFERENCES_EMAIL, userEmail);
        editor.apply();

    }

    public static String retrieveEmailOfCurrentUser(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String email = "";
        if (sharedPref.contains(SHARED_PREFERENCES_EMAIL)) {
            email = sharedPref.getString(SHARED_PREFERENCES_EMAIL, "");
        }
        return email;
    }


}
