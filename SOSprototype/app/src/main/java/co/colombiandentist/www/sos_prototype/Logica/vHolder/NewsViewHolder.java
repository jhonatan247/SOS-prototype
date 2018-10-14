package co.colombiandentist.www.sos_prototype.Logica.vHolder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import co.colombiandentist.www.sos_prototype.Datos.FirebaseHelper;
import co.colombiandentist.www.sos_prototype.Logica.Models.NewsModel;
import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoUser;
import co.colombiandentist.www.sos_prototype.Presentacion.Activity.ComentsActivity;
import co.colombiandentist.www.sos_prototype.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Usuario on 27/11/2017.
 */


public class NewsViewHolder extends RecyclerView.ViewHolder{

    public CircleImageView userPhoto;
    public TextView username;
    public TextView fecha;
    public TextView texto;
    public ImageView imagen;
    public TextView likes;
    public TextView comentarios;
    public Button like;
    public Button comentar;

    private String id="";
    Activity ctxx;


    public NewsViewHolder(View itemView) {
        super(itemView);
        userPhoto = (CircleImageView)itemView.findViewById(R.id.userPhotoCNew);
        username = (TextView)itemView.findViewById(R.id.usernameCNew);
        fecha = (TextView)itemView.findViewById(R.id.fechaCNew);
        texto = (TextView)itemView.findViewById(R.id.textoCNew);
        imagen = (ImageView)itemView.findViewById(R.id.imagenCNew);
        likes = (TextView)itemView.findViewById(R.id.likesCNew);
        comentarios = (TextView)itemView.findViewById(R.id.comentariosCNew);
        like = (Button)itemView.findViewById(R.id.likeCNew);
        comentar = (Button)itemView.findViewById(R.id.comentarCNew);
        comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctxx, ComentsActivity.class);
                Log.i(id,id);
                intent.putExtra("key", id);
                ctxx.startActivity(intent);
            }
        });

    }
    public void onBind(NewsModel noticia, Activity ctx){
        try {
            id = noticia.getId();
            ctxx = ctx;
            texto.setText(noticia.getTexto());
            fecha.setText(noticia.getFecha());
            pojoUser u = FirebaseHelper.getDataUsuario(noticia.getUsuario());

            username.setText(u.getNombre()+" "+u.getApellido());
            int com = FirebaseHelper.getCount(FirebaseHelper.getComentsNoticia(noticia.getId()));
            comentarios.setText(com+" comentarios");
            int lik = FirebaseHelper.getCount(FirebaseHelper.getLikesNoticia(noticia.getId()));
            likes.setText(lik+" likes");
            //holder.pictureCard.setImageDrawable(activity.getDrawable(R.drawable.imagenproducto));
            try {
                if(!imagen.equals("")&&imagen!=null)
                    Picasso.with(ctx).load(noticia.getImagen()).into(imagen);
            }catch (Exception e){

                //holder.foto.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.imagenproducto));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
