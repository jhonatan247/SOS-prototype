package co.colombiandentist.www.sos_prototype.Datos;

/**
 * Created by Usuario on 28/11/2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import co.colombiandentist.www.sos_prototype.Logica.Message;
import co.colombiandentist.www.sos_prototype.Logica.Pojos.pojoUser;

import java.util.List;

/**
 * Created by Usuario on 27/07/2017.
 */

public class DatabaseHelper {

    bdHelper helper;
    String retorno= "";

    public  DatabaseHelper(Context context){
        helper = new bdHelper(context);
    }

    public void insertUsuarios(List<String> keys, List<pojoUser> users){

        SQLiteDatabase database = helper.getWritableDatabase();
        if(helper.RestarUser(database)) {///revisar
            for(int i =0; i< users.size(); i++) {
                pojoUser user = users.get(i);
                ContentValues values = new ContentValues();
                values.put(helper.ID_USUARIO, keys.get(i));
                values.put(helper.NOMBRE_USUARIO,user.getNombre());
                values.put(helper.APELLIDO_USUARIO,user.getApellido());
                values.put(helper.FOTO_USUARIO, user.getFoto());
                values.put(helper.CORREO_USUARIO,user.getCorreo());
                values.put(helper.CONTRASEÑA_USUARIO, user.getContraseña());
                database.insert(helper.TABLE_USUARIO, null, values);
            }
        }

    }
    public void insertUsuario(String key, pojoUser user){

        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(helper.ID_USUARIO, key);
        values.put(helper.NOMBRE_USUARIO,user.getNombre());
        values.put(helper.APELLIDO_USUARIO,user.getApellido());
        values.put(helper.FOTO_USUARIO, user.getFoto());
        values.put(helper.CORREO_USUARIO,user.getCorreo());
        values.put(helper.CONTRASEÑA_USUARIO, user.getContraseña());
        database.insert(helper.TABLE_USUARIO, null, values);



    }
    public Cursor getUsuarios(){
        SQLiteDatabase database = helper.getWritableDatabase();
        return database.rawQuery(helper.SELECT_ALL_TABLE_USUARIO, null);
    }
    public Cursor searchUsuarios(String text){
        SQLiteDatabase database = helper.getWritableDatabase();
        return database.rawQuery(helper.LIKE_USER(text), null);
    }
    public Cursor selectUsuarios(String id){
        SQLiteDatabase database = helper.getWritableDatabase();
        return database.rawQuery(helper.SELECT_USER(id), null);
    }
    static class bdHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "BD_MYEF";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_USUARIO= "USUARIO";
        private static final String ID_USUARIO = "ID_USUARIO";
        private static final String NOMBRE_USUARIO = "NOMBRE";
        private static final String APELLIDO_USUARIO = "APELLIDO";
        private static final String CORREO_USUARIO = "CORREO";
        private static final String FOTO_USUARIO = "FOTO";
        private static final String CONTRASEÑA_USUARIO = "CONTRASEÑA";
        private static final String CREATE_TABLE_USUARIO =
                "CREATE TABLE "+TABLE_USUARIO+"("+
                        ID_USUARIO+" VARCHAR(255) NOT NULL,"+
                        NOMBRE_USUARIO+" VARCHAR(255) NOT NULL,"+
                        APELLIDO_USUARIO+" VARCHAR(255) NOT NULL,"+
                        CORREO_USUARIO+" VARCHAR(255) NOT NULL,"+
                        FOTO_USUARIO+" VARCHAR(255) NOT NULL,"+
                        CONTRASEÑA_USUARIO+" VARCHAR(255) NOT NULL" +
                        ");" +
                        "GO";
        private static final String SELECT_ALL_TABLE_USUARIO ="SELECT * FROM "+TABLE_USUARIO+" ORDER BY "+NOMBRE_USUARIO+" ASC;";
        private static final String DROP_TABLE_USUARIO = "DROP TABLE IF EXISTS " + TABLE_USUARIO;
        private static String SELECT_USER(String id){
            return "SELECT * FROM "+TABLE_USUARIO+" WHERE "+
                    ID_USUARIO+" LIKE '"+id+"'"+
                    ";";
        }
        private static String LIKE_USER(String texto){
            return "SELECT * FROM "+TABLE_USUARIO+" WHERE "+
                    NOMBRE_USUARIO+" LIKE '%"+texto+"%' OR "+
                    APELLIDO_USUARIO+" LIKE '%"+texto+"%' OR " +
                    CORREO_USUARIO+" LIKE '%"+texto+"%' OR " +
                    "ORDER BY "+NOMBRE_USUARIO+" ASC" +
                    ";";
        }


        private static final String TABLE_DOCENTE= "DOCENTE";
        private static final String ID_DOCENTE = "ID_DOCENTE";
        private static final String NOMBRE_DOCENTE = "NOMBRE";
        private static final String DEPARTAMENTO_DOCENTE = "DEPARTAMENTO";
        private static final String CREATE_TABLE_DOCENTE =
                "CREATE TABLE "+TABLE_DOCENTE+"("+
                        ID_DOCENTE+" VARCHAR(255) NOT NULL,"+
                        NOMBRE_DOCENTE+" VARCHAR(255) NOT NULL,"+
                        DEPARTAMENTO_DOCENTE+" VARCHAR(255) NOT NULL"+
                        ");" +
                        "GO";
        private static final String SELECT_ALL_TABLE_DOCENTE ="SELECT * FROM "+TABLE_DOCENTE+" ORDER BY "+NOMBRE_DOCENTE+" ASC;";
        private static final String DROP_TABLE_DOCENTE = "DROP TABLE IF EXISTS " + TABLE_DOCENTE;
        private static String SELECT_DOCENTE(String id){
            return "SELECT * FROM "+TABLE_USUARIO+" WHERE "+
                    ID_DOCENTE+" LIKE '"+id+"'"+
                    ";";
        }
        private static String LIKE_DOCENTE(String texto){
            return "SELECT * FROM "+TABLE_DOCENTE+" WHERE "+
                    NOMBRE_DOCENTE+" LIKE '%"+texto+"%' OR "+
                    "ORDER BY "+NOMBRE_DOCENTE+" ASC" +
                    ";";
        }




        private static final String TABLE_ASIGNATURA= "ASIGNATURA";
        private static final String ID_ASIGNATURA = "ID_ASIGNATURA";
        private static final String NOMBRE_ASIGNATURA = "NOMBRE";
        private static final String DEPARTAMENTO_ASIGNATURA = "DEPARTAMENTO";
        private static final String CREATE_TABLE_ASIGNATURA =
                "CREATE TABLE "+TABLE_ASIGNATURA+"("+
                        ID_ASIGNATURA+" VARCHAR(255) NOT NULL,"+
                        NOMBRE_ASIGNATURA+" VARCHAR(255) NOT NULL,"+
                        DEPARTAMENTO_ASIGNATURA+" VARCHAR(255) NOT NULL"+
                        ");" +
                        "GO";
        private static final String SELECT_ALL_TABLE_ASIGNATURA ="SELECT * FROM "+TABLE_ASIGNATURA+" ORDER BY "+NOMBRE_ASIGNATURA+" ASC;";
        private static final String DROP_TABLE_ASIGNATURA = "DROP TABLE IF EXISTS " + TABLE_ASIGNATURA;
        private static String SELECT_ASIGNATURA(String id){
            return "SELECT * FROM "+TABLE_ASIGNATURA+" WHERE "+
                    ID_ASIGNATURA+" LIKE '"+id+"'"+
                    ";";
        }
        private static String LIKE_ASIGNATURA(String texto){
            return "SELECT * FROM "+TABLE_ASIGNATURA+" WHERE "+
                    NOMBRE_ASIGNATURA+" LIKE '%"+texto+"%' OR "+
                    "ORDER BY "+NOMBRE_ASIGNATURA+" ASC" +
                    ";";
        }
        private static final String TABLE_CALIFICACION_ASIGNATURA= "CALIFICACION_ASIGNATURA";
        private static final String ID_CALIFICACION_ASIGNATURA= "ID_ASIGNATURA";
        private static final String ID_ASIGNATURA_CALIFICACION_ASIGNATURA = "ASIGNATURA";
        private static final String COMENTARIO_CALIFICACION_ASIGNATURA = "COMENTARIO";
        private static final String FECHA_CALIFICACION_ASIGNATURA = "FECHA";
        private static final String CALIFICACION_CALIFICACION_ASIGNATURA = "CALIFICACION";
        private static final String CREATE_TABLE_CALIFICACION_ASIGNATURA =
                "CREATE TABLE "+TABLE_CALIFICACION_ASIGNATURA+"("+
                        ID_CALIFICACION_ASIGNATURA+" VARCHAR(255) NOT NULL,"+
                        ID_ASIGNATURA_CALIFICACION_ASIGNATURA+" VARCHAR(255) NOT NULL,"+
                        COMENTARIO_CALIFICACION_ASIGNATURA+" VARCHAR(255) NOT NULL,"+
                        FECHA_CALIFICACION_ASIGNATURA+" VARCHAR(255) NOT NULL,"+
                        CALIFICACION_CALIFICACION_ASIGNATURA+" INT NOT NULL"+
                        ");" +
                        "GO";
        private static final String SELECT_ALL_TABLE_CALIFICACION_ASIGNATURA ="SELECT * FROM "+TABLE_CALIFICACION_ASIGNATURA+" ORDER BY "+FECHA_CALIFICACION_ASIGNATURA+" DESC;";
        private static final String DROP_TABLE_CALIFICACION_ASIGNATURA = "DROP TABLE IF EXISTS " + TABLE_CALIFICACION_ASIGNATURA;
        private static String SELECT_CALIFICACION_ASIGNATURA(String id){
            return "SELECT * FROM "+TABLE_CALIFICACION_ASIGNATURA+" WHERE "+
                    ID_CALIFICACION_ASIGNATURA+" LIKE '"+id+"'"+
                    ";";
        }
        private static final String TABLE_CALIFICACION_DOCENTE= "CALIFICACION_DOCENTE";
        private static final String ID_CALIFICACION_DOCENTE= "ID_DOCENTE";
        private static final String ID_ASIGNATURA_CALIFICACION_DOCENTE= "ASIGNATURA";
        private static final String ID_DOCENTE_CALIFICACION_DOCENTE= "DOCENTE";
        private static final String COMENTARIO_CALIFICACION_DOCENTE = "COMENTARIO";
        private static final String FECHA_CALIFICACION_DOCENTE= "FECHA";
        private static final String CALIFICACION_CALIFICACION_DOCENTE = "CALIFICACION";
        private static final String CREATE_TABLE_CALIFICACION_DOCENTE =
                "CREATE TABLE "+TABLE_CALIFICACION_DOCENTE+"("+
                        ID_CALIFICACION_DOCENTE+" VARCHAR(255) NOT NULL,"+
                        ID_ASIGNATURA_CALIFICACION_DOCENTE+" VARCHAR(255) NOT NULL,"+
                        ID_DOCENTE_CALIFICACION_DOCENTE+" VARCHAR(255) NOT NULL,"+
                        COMENTARIO_CALIFICACION_DOCENTE+" VARCHAR(255) NOT NULL,"+
                        FECHA_CALIFICACION_DOCENTE+" VARCHAR(255) NOT NULL,"+
                        CALIFICACION_CALIFICACION_DOCENTE+" INT NOT NULL"+
                        ");" +
                        "GO";
        private static final String SELECT_ALL_TABLE_CALIFICACION_DOCENTE ="SELECT * FROM "+TABLE_CALIFICACION_DOCENTE+" ORDER BY "+FECHA_CALIFICACION_DOCENTE+" DESC;";
        private static final String DROP_TABLE_CALIFICACION_DOCENTE = "DROP TABLE IF EXISTS " + TABLE_CALIFICACION_DOCENTE;
        private static String SELECT_CALIFICACION_DOCENTE(String id){
            return "SELECT * FROM "+TABLE_CALIFICACION_DOCENTE+" WHERE "+
                    ID_CALIFICACION_DOCENTE+" LIKE '"+id+"'"+
                    ";";
        }
        private static final String TABLE_NOTICIA= "NOTICIA";
        private static final String TABLE_NOTIFICACION= "NOTIFICACION";


        private Context context;

        public bdHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }
        public boolean RestarUser(SQLiteDatabase db){
            try {
                db.execSQL(DROP_TABLE_USUARIO);
                onCreate(db);
                return true;
            } catch (Exception e) {
                Message.mostrarMensaje(context, e.toString());
                return false;
            }
        }
        public boolean RestarDocente(SQLiteDatabase db){
            try {
                db.execSQL(DROP_TABLE_DOCENTE);
                onCreate(db);
                return true;
            } catch (Exception e) {
                Message.mostrarMensaje(context, e.toString());
                return false;
            }
        }
        public boolean RestarAsignatura(SQLiteDatabase db){
            try {
                db.execSQL(DROP_TABLE_ASIGNATURA);
                onCreate(db);
                return true;
            } catch (Exception e) {
                Message.mostrarMensaje(context, e.toString());
                return false;
            }
        }
        public bdHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_USUARIO);
                db.execSQL(CREATE_TABLE_DOCENTE);
                db.execSQL(CREATE_TABLE_ASIGNATURA);
            } catch (Exception e) {
                Message.mostrarMensaje(context, e.toString());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            RestarUser(db);
            RestarDocente(db);
            RestarAsignatura(db);
        }
    }
}

