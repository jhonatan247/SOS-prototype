package co.colombiandentist.www.sos_prototype.Logica;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Usuario on 27/11/2017.
 */

public class Message {
    public  static  void mostrarMensaje(Context context, String message){
        Toast.makeText(context, message,Toast.LENGTH_LONG).show();
    }
    public static  void mostrarAlerta(Context ctx, String mensaje, String Titulo, DialogInterface.OnClickListener clickOK){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(mensaje)
                .setTitle(Titulo);
        builder.setPositiveButton("Aceptar", clickOK);
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static  void mostrarAlertaView(Context ctx, String mensaje, String Titulo, DialogInterface.OnClickListener clickOK, View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(mensaje)
                .setTitle(Titulo);
        builder.setView(view);
        builder.setPositiveButton("Aceptar", clickOK);
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static  void mostrarAlertaPersonalizada2(Context ctx, String mensaje, String Titulo, String btn1,  DialogInterface.OnClickListener click1, String btn2,  DialogInterface.OnClickListener click2){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(mensaje)
                .setTitle(Titulo);
        builder.setPositiveButton(btn1, click1);
        builder.setNegativeButton(btn2, click2);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
