package co.colombiandentist.www.sos_prototype.Presentacion.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.colombiandentist.www.sos_prototype.Datos.FirebaseHelper;
import co.colombiandentist.www.sos_prototype.Logica.Interfaces.Card;
import co.colombiandentist.www.sos_prototype.Logica.Models.PersonModel;
import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoUser;
import co.colombiandentist.www.sos_prototype.Logica.RecyclerAdapter.AdapterRecycler;
import co.colombiandentist.www.sos_prototype.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PersonFragment extends Fragment {

    RecyclerView recycler;

    public PersonFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        recycler = (RecyclerView) rootView.findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        AdapterRecycler adapterRecyclerView =
                new AdapterRecycler(new ArrayList<Card>(), getActivity());

        recycler.setAdapter(adapterRecyclerView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference prodReference = database.getReference(FirebaseHelper.USER_REFERENCE);
        prodReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pojoUser pojo = dataSnapshot.getValue(pojoUser.class);

                PersonModel newsModel = new PersonModel(dataSnapshot.getKey(), pojo.getNombre(),pojo.getApellido(), pojo.getCorreo(), pojo.getFoto());
                ((AdapterRecycler) recycler.getAdapter()).addCard(newsModel);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                pojoUser pojo = dataSnapshot.getValue(pojoUser.class);

                PersonModel newsModel = new PersonModel(dataSnapshot.getKey(), pojo.getNombre(),pojo.getApellido(), pojo.getCorreo(), pojo.getFoto());
                ((AdapterRecycler) recycler.getAdapter()).updateCard(newsModel);
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
        return rootView;
    }
}
