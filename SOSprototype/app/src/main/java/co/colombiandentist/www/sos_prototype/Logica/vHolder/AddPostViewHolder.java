package co.colombiandentist.www.sos_prototype.Logica.vHolder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import co.colombiandentist.www.sos_prototype.Presentacion.Activity.CreatePostActivity;
import co.colombiandentist.www.sos_prototype.R;

/**
 * Created by Usuario on 27/11/2017.
 */

public class AddPostViewHolder extends RecyclerView.ViewHolder{

    public CardView card;

    public AddPostViewHolder(View itemView, final Activity ctx) {
        super(itemView);
        card = (CardView) itemView.findViewById(R.id.cardview_add_post);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, CreatePostActivity.class);
                ctx.startActivity(intent);
            }
        });

    }
}
