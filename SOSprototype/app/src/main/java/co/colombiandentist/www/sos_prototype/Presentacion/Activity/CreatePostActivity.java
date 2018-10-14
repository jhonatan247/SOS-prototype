package co.colombiandentist.www.sos_prototype.Presentacion.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.colombiandentist.www.sos_prototype.Datos.FirebaseHelper;
import co.colombiandentist.www.sos_prototype.Logica.Message;
import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoNews;
import co.colombiandentist.www.sos_prototype.Logica.Validacion;
import co.colombiandentist.www.sos_prototype.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener {
    CircleImageView fotoUser;
    TextView nombreUser;
    ImageButton camara;
    ImageButton galeria;
    EditText mensaje;
    ImageView imagen;
    Button cancelar;
    Button publicar;

    Uri img = null;
    Uri photoURI = null;
    Context ctx;
    String mCurrentPhotoPath;
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQ_IMG = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        fotoUser = (CircleImageView)findViewById(R.id.fotoUser);
        nombreUser = (TextView)findViewById(R.id.nombreUser);
        camara = (ImageButton)findViewById(R.id.camara);
        galeria = (ImageButton)findViewById(R.id.galeria);
        mensaje = (EditText)findViewById(R.id.mensaje);
        imagen = (ImageView)findViewById(R.id.imagen);
        cancelar = (Button)findViewById(R.id.cancelar);
        publicar = (Button)findViewById(R.id.publicar);
        camara.setOnClickListener(this);
        galeria.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        publicar.setOnClickListener(this);
        ctx = this;
    }
    boolean validar(){
        return mensaje.getText().toString().trim().length()>0||img!=null;
    }
    @Override
    public void onClick(View v) {
        if(v==camara){
            try {
                solicitarPermisos();
                dispatchTakePictureIntent();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(v==galeria){
            openGallery();
        }else if((v == cancelar)){
            onBackPressed();
        }else if(v == publicar && validar()){
            if(img!=null){
                StorageReference mStorage = FirebaseStorage.getInstance().getReference();
                StorageReference filepath = mStorage.child("Fotos").child(Math.random()+img.getLastPathSegment());
                filepath.putFile(img).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pojoNews pojo = new pojoNews(FirebaseHelper.getCuUser().getUid(),taskSnapshot.getStorage().getDownloadUrl().toString(), mensaje.getText().toString(), "Post", Validacion.getFechaCompleta());
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(FirebaseHelper.NEWS_REFERENCE);
                        publicar.setEnabled(false);
                        db.push().setValue(pojo).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Message.mostrarMensaje(ctx, "Publicación realizada con éxito");
                                publicar.setEnabled(false);
                                onBackPressed();
                            }
                        });
                    }
                });
            }else{
                pojoNews pojo = new pojoNews(FirebaseHelper.getCuUser().getUid(),"", mensaje.getText().toString(), "Post", Validacion.getFechaCompleta());
                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(FirebaseHelper.NEWS_REFERENCE);
                publicar.setEnabled(false);
                db.push().setValue(pojo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Message.mostrarMensaje(ctx, "Publicación realizada con éxito");
                        publicar.setEnabled(false);
                        onBackPressed();
                    }
                });
            }
        }
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "co.colombiandentist.www.sos_prototype",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    void solicitarPermisos(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    void openGallery(){
        Intent galeria = new Intent(Intent.ACTION_PICK);
        galeria.setType("image/*");
        startActivityForResult(galeria, REQ_IMG);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_IMG:
                if (resultCode == RESULT_OK && null != data) {
                    img = data.getData();
                    //Picasso.with(this).load(imagen).into(imgHeader);
                    imagen.setImageURI(img);
                }

                break;
            case REQUEST_TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    /*Bundle extras = data.getExtras();
                    Bitmap image = (Bitmap) extras.get("data");
                    imagen.setImageBitmap(image);*/
                    img = photoURI;
                    imagen.setImageURI(img);
                }
                break;
        }
    }
}
