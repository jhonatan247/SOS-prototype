package co.colombiandentist.www.sos_prototype.Logica.RecyclerAdapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import co.colombiandentist.www.sos_prototype.Logica.Interfaces.Card;
import co.colombiandentist.www.sos_prototype.Logica.Models.AddPostModel;
import co.colombiandentist.www.sos_prototype.Logica.Models.ComentModel;
import co.colombiandentist.www.sos_prototype.Logica.Models.DocenteModel;
import co.colombiandentist.www.sos_prototype.Logica.Models.NewsModel;
import co.colombiandentist.www.sos_prototype.Logica.Models.NotificationModel;
import co.colombiandentist.www.sos_prototype.Logica.Models.PersonModel;
import co.colombiandentist.www.sos_prototype.Logica.vHolder.AddPostViewHolder;
import co.colombiandentist.www.sos_prototype.Logica.vHolder.ComentViewHolder;
import co.colombiandentist.www.sos_prototype.Logica.vHolder.DocenteViewHolder;
import co.colombiandentist.www.sos_prototype.Logica.vHolder.NewsViewHolder;
import co.colombiandentist.www.sos_prototype.Logica.vHolder.NotificationViewHolder;
import co.colombiandentist.www.sos_prototype.Logica.vHolder.PersonViewHolder;
import co.colombiandentist.www.sos_prototype.R;

import java.util.ArrayList;

/**
 * Created by Usuario on 25/10/2017.
 */

public class AdapterRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String DEBUG_TAG = "Adapter";
    private ArrayList<Card> cards;
    private Activity activity;

    public AdapterRecycler(ArrayList<Card> card, Activity activity) {
        this.cards = card;
        this.activity = activity;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder;
        switch (viewType){
            case NewsModel.TYPE:
                viewHolder = new NewsViewHolder(inflater.inflate(R.layout.cardview_new,parent,false));
                break;
            case AddPostModel.TYPE:
                viewHolder = new AddPostViewHolder(inflater.inflate(R.layout.carview_add_post,parent,false),activity);
                break;
            case PersonModel.TYPE:
                viewHolder = new PersonViewHolder(inflater.inflate(R.layout.cardview_person,parent,false));
                break;
            case DocenteModel.TYPE:
                viewHolder = new DocenteViewHolder(inflater.inflate(R.layout.cardview_docente,parent,false));
                break;
            case NotificationModel.TYPE:
                viewHolder = new NotificationViewHolder(inflater.inflate(R.layout.cardview_notification,parent,false));
                break;
            case ComentModel.TYPE:
                viewHolder = new ComentViewHolder(inflater.inflate(R.layout.cardview_coment,parent,false));
                break;
            default:
                viewHolder = new NewsViewHolder(inflater.inflate(R.layout.cardview_new,parent,false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case NewsModel.TYPE:
                NewsModel noticia = (NewsModel) cards.get(position);
                ((NewsViewHolder) holder).onBind(noticia,activity);
                break;
            case AddPostModel.TYPE:
                AddPostModel addPost = (AddPostModel) cards.get(position);
                AddPostViewHolder holderAddPost = (AddPostViewHolder) holder;
                break;
            case PersonModel.TYPE:
                PersonModel person = (PersonModel) cards.get(position);
                ((PersonViewHolder) holder).onBind(person,activity);
                break;
            case DocenteModel.TYPE:
                DocenteModel docente = (DocenteModel) cards.get(position);
                ((DocenteViewHolder) holder).onBind(docente,activity);
                break;
            case NotificationModel.TYPE:
                NotificationModel notify = (NotificationModel) cards.get(position);
                ((NotificationViewHolder) holder).onBind(notify,activity);
                break;
            case ComentModel.TYPE:
                ComentModel coment = (ComentModel) cards.get(position);
                ((ComentViewHolder) holder).onBind(coment,activity);
                break;
        }





    }
    @Override
    public int getItemViewType(int position) {
        return cards.get(position).getCardType();
    }


    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public String getItemKey(int position){
        return cards.get(position).getId();
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        viewHolder.itemView.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        animateCircularReveal(viewHolder.itemView);
    }

    public void animateCircularReveal(View view) {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int centerX = 0;
            int centerY = 0;
            int startRadius = 0;
            int endRadius = Math.max(view.getWidth(), view.getHeight());
            Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
            view.setVisibility(View.VISIBLE);
            animation.start();
        }*/
    }

    public void animateCircularDelete(final View view, final int list_position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            int centerX = view.getWidth();
            int centerY = view.getHeight();
            int startRadius = view.getWidth();
            int endRadius = 0;
            Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

            animation.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    Log.d(DEBUG_TAG, "SampleMaterialAdapter onAnimationEnd for Edit adapter position " + list_position);
                    Log.d(DEBUG_TAG, "SampleMaterialAdapter onAnimationEnd for Edit cardId " + getItemId(list_position));

                    view.setVisibility(View.INVISIBLE);
                    cards.remove(list_position);
                    notifyItemRemoved(list_position);
                }
            });
            animation.start();
        }else{
            cards.remove(list_position);
            notifyItemRemoved(list_position);
        }
    }

    public void addCard(Card card) {
        cards.add(card);
        notifyItemInserted(getItemCount());
    }

    public void updateCard(Card card) {
        int position = getPosition(card.getId());
        cards.get(position).changeValues(card);
        notifyItemChanged(position);
    }

    public void deleteCard(View view, int position) {

        animateCircularDelete(view, position);
    }

    private int getPosition(String key){
        for(int i =0; i<cards.size();i++){
            if(cards.get(i).getId().equals(key) )//&&cards.get(i).getCardType()==type)
                return i;
        }
        return -1;
    }

}
