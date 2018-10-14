package co.colombiandentist.www.sos_prototype.Presentacion.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import co.colombiandentist.www.sos_prototype.Datos.FirebaseHelper;
import co.colombiandentist.www.sos_prototype.Logica.Message;
import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoUser;
import co.colombiandentist.www.sos_prototype.Logica.Validacion;
import co.colombiandentist.www.sos_prototype.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    Context ctx;
    FirebaseAuth mAuth;
    DatabaseReference mData;
    TextInputEditText txNombres;
    TextInputEditText txApellidos;
    TextInputEditText txCorreo;
    TextInputEditText txContraseña;
    TextInputEditText txConfContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrarse");

        txNombres = (TextInputEditText) findViewById(R.id.txNombres);
        txApellidos = (TextInputEditText) findViewById(R.id.txApellidos);
        txCorreo = (TextInputEditText) findViewById(R.id.txCorreo);
        txContraseña = (TextInputEditText) findViewById(R.id.txContraseña);
        txConfContraseña = (TextInputEditText) findViewById(R.id.txConfContraseña);
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference().child(FirebaseHelper.USER_REFERENCE);
        ctx = this;
    }
    private boolean validate(){
        Validacion.ctx = this;
        return Validacion.validateLenght(txNombres,3)&&
                Validacion.validateLenght(txApellidos,3)&&
                Validacion.validateEmail(txCorreo) &&
                Validacion.validateLenght(txContraseña,7)&&
                Validacion.validateEquals(txContraseña,txConfContraseña);
    }
    public void onRegister(final View v){
        if(validate()){
            v.setEnabled(false);
            final String Nombre = txNombres.getText().toString().trim();
            final String Apellido = txApellidos.getText().toString().trim();
            final String Correo = txCorreo.getText().toString().trim();
            final String Contraseña = txContraseña.getText().toString().trim();
            mAuth.createUserWithEmailAndPassword(Correo,Contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                    Message.mostrarMensaje(ctx, "Ha ocurrido un error");
                    v.setEnabled(true);
                }
            }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    pojoUser user = new pojoUser(Nombre, Apellido,Correo,Contraseña);
                    mData.child(mAuth.getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mAuth.getCurrentUser().sendEmailVerification();
                            Message.mostrarAlerta(ctx, "Por favor ve a tu correo para validar el registro", "Registro exitoso", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    onBackPressed();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            Message.mostrarMensaje(ctx, "Ha ocurrido un error");
                            mAuth.getCurrentUser().delete();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            v.setEnabled(true);
                        }
                    });
                }
            });
        }
    }
}
