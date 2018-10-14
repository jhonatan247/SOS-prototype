package co.colombiandentist.www.sos_prototype.Presentacion.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.colombiandentist.www.sos_prototype.Datos.FirebaseHelper;
import co.colombiandentist.www.sos_prototype.Logica.Interfaces.Card;
import co.colombiandentist.www.sos_prototype.Logica.Message;
import co.colombiandentist.www.sos_prototype.Logica.Models.ComentModel;
import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoComent;
import co.colombiandentist.www.sos_prototype.Logica.RecyclerAdapter.AdapterRecycler;
import co.colombiandentist.www.sos_prototype.Logica.Validacion;
import co.colombiandentist.www.sos_prototype.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ComentsActivity extends AppCompatActivity {
    RecyclerView recycler;
    String key;
    EditText editMensaje;
    Button btnEnviar;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coments);
        ctx = this;
        editMensaje = (EditText)findViewById(R.id.editMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        AdapterRecycler adapterRecyclerView =
                new AdapterRecycler(new ArrayList<Card>(), this);

        recycler.setAdapter(adapterRecyclerView);
        key = getIntent().getStringExtra("key");
        FirebaseDatabase.getInstance().getReference().child(FirebaseHelper.NEWS_REFERENCE).child(key).child(FirebaseHelper.COMENTS_REFERENCE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pojoComent pojo = dataSnapshot.getValue(pojoComent.class);
                ComentModel model = new ComentModel(dataSnapshot.getKey(), pojo.getUser(), pojo.getAno(), pojo.getMensaje());
                ((AdapterRecycler) recycler.getAdapter()).addCard(model);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                pojoComent pojo = dataSnapshot.getValue(pojoComent.class);
                ComentModel model = new ComentModel(dataSnapshot.getKey(), pojo.getUser(), pojo.getAno(), pojo.getMensaje());
                ((AdapterRecycler) recycler.getAdapter()).updateCard(model);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editMensaje.getText().toString().trim().equals("")) {
                    pojoComent coment = new pojoComent(FirebaseHelper.getCuUser().getUid(), Validacion.getFechaCompleta(), editMensaje.getText().toString());
                    FirebaseDatabase.getInstance().getReference().child(FirebaseHelper.NEWS_REFERENCE).child(key).child(FirebaseHelper.COMENTS_REFERENCE).push().setValue(coment).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Message.mostrarMensaje(ctx, "El mensaje ha sido enviado");
                        }
                    });
                    editMensaje.setText("");
                }

            }
        });
    }
}
