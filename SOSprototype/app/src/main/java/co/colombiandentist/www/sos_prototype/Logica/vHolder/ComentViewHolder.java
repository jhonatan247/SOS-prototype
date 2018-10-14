package co.colombiandentist.www.sos_prototype.Logica.vHolder;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.colombiandentist.www.sos_prototype.Datos.FirebaseHelper;
import co.colombiandentist.www.sos_prototype.Logica.Models.ComentModel;
import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoUser;
import co.colombiandentist.www.sos_prototype.R;

/**
 * Created by Usuario on 28/11/2017.
 */

public class ComentViewHolder extends RecyclerView.ViewHolder {
    CardView card;
    ImageView foto;
    TextView user;
    TextView fecha;
    TextView mensaje;
    public ComentViewHolder(View itemView) {
        super(itemView);
        foto= (ImageView) itemView.findViewById(R.id.foto);
        user= (TextView) itemView.findViewById(R.id.user);
        fecha= (TextView) itemView.findViewById(R.id.fecha);
        mensaje= (TextView) itemView.findViewById(R.id.mensaje);
        card = (CardView)itemView.findViewById(R.id.cardview_coment);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void onBind(ComentModel person, Activity ctx){
        try {
            pojoUser us = FirebaseHelper.getDataUsuario(person.getUser());
            user.setText(us.getNombre()+" "+us.getApellido());
            fecha.setText(person.getFecha());
            mensaje.setText(person.getComentario());
            //holder.pictureCard.setImageDrawable(activity.getDrawable(R.drawable.imagenproducto));
            /*try {
                if(person.getFoto() != ""&& person.getFoto()!=null)
                    Picasso.with(ctx).load(person.getFoto()).into(foto);
            }catch (Exception e){

                //holder.foto.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.imagenproducto));
            }*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
