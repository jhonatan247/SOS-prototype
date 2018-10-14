package co.colombiandentist.www.sos_prototype.Logica.vHolder;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.colombiandentist.www.sos_prototype.Datos.FirebaseHelper;
import co.colombiandentist.www.sos_prototype.Logica.Models.DocenteModel;
import co.colombiandentist.www.sos_prototype.R;

/**
 * Created by Usuario on 28/11/2017.
 */

public class DocenteViewHolder extends RecyclerView.ViewHolder {
    CardView card;
    ImageView icon;
    TextView nombre;
    TextView departamento;
    public DocenteViewHolder(View itemView) {
        super(itemView);
        icon= (ImageView) itemView.findViewById(R.id.iconCDocente);
        nombre= (TextView) itemView.findViewById(R.id.nombreCDocente);
        departamento= (TextView) itemView.findViewById(R.id.departamentoCDocente);
        card = (CardView)itemView.findViewById(R.id.cardview_docente);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void onBind(DocenteModel person, Activity ctx){
        try {
            nombre.setText(person.getNombre()+" "+person.getNombre());
            departamento.setText(FirebaseHelper.getDepartamento(person.getDepartamento()).getValue().toString());
            icon.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_professor_color));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
