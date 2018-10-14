package co.colombiandentist.www.sos_prototype.Datos;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoDocente;
import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoNews;
import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoUser;

/**
 * Created by Usuario on 27/11/2017.
 */

public class FirebaseHelper {
    public static String USER_REFERENCE= "usuarios";
    public static String NEWS_REFERENCE= "noticias";
    public static String DOCENTES_REFERENCE= "docentes";
    public static String DEPARTAMENTOS_REFERENCE= "departamentos";
    public static String ASIGNATURAS_REFERENCE= "asignaturas";
    public static String NOTIFICACIONES_REFERENCE= "notificaciones";
    public static String NOMBRE_REFERENCE= "nombre";
    public static String APELLIDO_REFERENCE= "apellido";
    public static String FOTO_REFERENCE= "foto";
    public static String COMENTS_REFERENCE= "coments";
    public static String LIKES_REFERENCE= "likes";

    public static DataSnapshot value;

    public static DataSnapshot getDepartamento(String key){
        return value.child(DEPARTAMENTOS_REFERENCE).child(key);
    }
    public static DataSnapshot getUsuario(String key){
        return value.child(USER_REFERENCE).child(key);
    }
    public static pojoUser getDataUsuario(String key){
        return getUsuario(key).getValue(pojoUser.class);
    }
    public static DataSnapshot getDocente(String key){
        return value.child(DOCENTES_REFERENCE).child(key);
    }
    public static pojoDocente getDataDocente(String key){
        return getDocente(key).getValue(pojoDocente.class);
    }
    public static DataSnapshot getNoticia(String key){
        return value.child(NEWS_REFERENCE).child(key);
    }
    public static DataSnapshot getComentsNoticia(String key){
        return value.child(NEWS_REFERENCE).child(key).child(COMENTS_REFERENCE);
    }
    public static DataSnapshot getLikesNoticia(String key){
        return value.child(NEWS_REFERENCE).child(key).child(LIKES_REFERENCE);
    }
    public static pojoNews getDataNoticia(String key){
        return getNoticia(key).getValue(pojoNews.class);
    }
    public static DataSnapshot getAsignatura(String key){
        return value.child(ASIGNATURAS_REFERENCE).child(key);
    }
    public static DataSnapshot getNotificacion(String key){
        return value.child(USER_REFERENCE).child(getCuUser().getUid()).child(NOTIFICACIONES_REFERENCE).child(key);
    }
    public static FirebaseUser getCuUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }
    public static int getCount(DataSnapshot dSnapshot){
        int c =0;
        for(DataSnapshot d: dSnapshot.getChildren()){
            if(d.getValue()!=null)
                c++;
        }
        return  c;
    }
}
