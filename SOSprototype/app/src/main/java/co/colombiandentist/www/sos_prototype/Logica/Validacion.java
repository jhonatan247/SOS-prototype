package co.colombiandentist.www.sos_prototype.Logica;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.view.Surface;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Usuario on 25/10/2017.
 */

public class Validacion {
    private static final String PATTERN_EMAIL = "^[_A-Za-z\\+]+(\\.[_A-Za-z]+)*@unal.edu.co";
    public static Activity ctx;
    public  static  String getFechaCompleta(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    public static boolean validateEmail(TextInputEditText email) {


        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        Matcher matcher = pattern.matcher(email.getText().toString().trim());
        return invalidate(email,matcher.matches());

    }
    public static boolean validateLenght(TextInputEditText t, int l) {
        return invalidate(t,t.getText().toString().trim().length()>=l);

    }
    public static boolean validateEquals(TextInputEditText t1, TextInputEditText t2) {
        return invalidate(t2,t1.getText().toString().trim().equals(t2.getText().toString().trim()));

    }
    public static boolean invalidate(TextInputEditText t, boolean cond){
        if(!cond) {
            t.requestFocus();
        }
        return cond;
    }
    public static Boolean isLandscape(Context context){
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
        switch (rotation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                return false;
            case Surface.ROTATION_90:
            default:
                return true;
        }
    }
}
