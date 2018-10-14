package co.colombiandentist.www.sos_prototype.Logica.Models;

import co.colombiandentist.www.sos_prototype.Logica.Interfaces.Card;

/**
 * Created by Usuario on 27/11/2017.
 */

public class AddPostModel implements Card {
    public static final int TYPE = 1;

    @Override
    public int getCardType() {
        return TYPE;
    }

    @Override
    public String getId() {
        return "AddPost";
    }

    @Override
    public void changeValues(Card card) {

    }

    public AddPostModel() {
    }
}
