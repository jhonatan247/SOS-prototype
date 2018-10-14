package co.colombiandentist.www.sos_prototype.Logica.vHolder;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.colombiandentist.www.sos_prototype.Datos.FirebaseHelper;
import co.colombiandentist.www.sos_prototype.Logica.Models.NotificationModel;
import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoUser;
import co.colombiandentist.www.sos_prototype.R;

/**
 * Created by Usuario on 28/11/2017.
 */

public class NotificationViewHolder extends RecyclerView.ViewHolder {
    CardView card;
    ImageView imagen;
    TextView user;
    TextView title;
    TextView fecha;
    public NotificationViewHolder(View itemView) {
        super(itemView);
        imagen= (ImageView) itemView.findViewById(R.id.imageCNotification);
        user= (TextView) itemView.findViewById(R.id.userCNotification);
        title= (TextView) itemView.findViewById(R.id.titleCNotification);
        fecha= (TextView) itemView.findViewById(R.id.fechaCNotification);
        card = (CardView)itemView.findViewById(R.id.cardview_notification);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void onBind(NotificationModel notification, Activity ctx){
        try {
            title.setText(notification.getTitulo());
            fecha.setText(notification.getFecha());
            pojoUser u = FirebaseHelper.getDataUsuario(notification.getUsuario());
            user.setText(u.getNombre()+" "+u.getApellido());
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
