package co.colombiandentist.www.sos_prototype.Logica.vHolder;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import co.colombiandentist.www.sos_prototype.Logica.Models.PersonModel;
import co.colombiandentist.www.sos_prototype.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Usuario on 28/11/2017.
 */

public class PersonViewHolder extends RecyclerView.ViewHolder {
    CardView card;
    ImageView foto;
    TextView nombre;
    TextView correo;
    ImageButton mensaje;
    public PersonViewHolder(View itemView) {
        super(itemView);
        foto= (ImageView) itemView.findViewById(R.id.fotoCPerson);
        nombre= (TextView) itemView.findViewById(R.id.nombreCPerson);
        correo= (TextView) itemView.findViewById(R.id.correoCPerson);
        mensaje= (ImageButton) itemView.findViewById(R.id.mensajeCPerson);
        mensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        card = (CardView)itemView.findViewById(R.id.cardview_person);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void onBind(PersonModel person, Activity ctx){
        try {
            nombre.setText(person.getNombre()+" "+person.getApellido());
            correo.setText(person.getCorreo());
            //holder.pictureCard.setImageDrawable(activity.getDrawable(R.drawable.imagenproducto));
            try {
                if(person.getFoto() != ""&& person.getFoto()!=null)
                    Picasso.with(ctx).load(person.getFoto()).into(foto);
            }catch (Exception e){

                //holder.foto.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.imagenproducto));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
