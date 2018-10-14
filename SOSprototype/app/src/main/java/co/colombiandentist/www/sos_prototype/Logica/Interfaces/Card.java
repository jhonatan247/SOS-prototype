package co.colombiandentist.www.sos_prototype.Logica.Interfaces;

/**
 * Created by Usuario on 26/10/2017.
 */

public interface Card {
    int getCardType();
    String getId();
    void changeValues(Card card);
}
