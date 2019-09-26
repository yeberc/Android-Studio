package yeberc.blogspot.bdsqlite01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Bdsqlite extends SQLiteOpenHelper {
    Context contxt;

    public Bdsqlite(@Nullable Context context) {
        super(context, "bd_prueba", null, 1);
        contxt = context;
    }


    @Override
    public void onCreate(SQLiteDatabase bd) {
       // String sql="CREATE TABLE agenda(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT NOT NULL, telefono TEXT NOT NULL";
        bd.execSQL("CREATE TABLE agenda(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT NOT NULL, telefono TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  agenda");
        onCreate(sqLiteDatabase);
    }
    // metodos para manejar la base de datos
    Bdsqlite bds;
    SQLiteDatabase basedatos;
    public void Abrir(){
        bds= new Bdsqlite(contxt);
        basedatos=bds.getWritableDatabase();
    }
    public void Cerrar(){
        basedatos.close();
    }
    public long Insertar(String nombre,String telf) throws Exception{
        ContentValues valores= new ContentValues();
        valores.put("nombre",nombre);
        valores.put("telefono", telf);
        return basedatos.insert("agenda", null, valores);
    }
    public String Listar() throws Exception{
        String dato="";
        String[] lista=new String[]{"nombre","telefono"};
        Cursor c=basedatos.query("agenda",lista,null,null,null, null,null);
        ContentValues valores= new ContentValues();
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            dato+=c.getString(c.getColumnIndex("nombre")).toString()+" "+c.getString(c.getColumnIndex("telefono")).toString()+"\n";
        }
        return dato;
    }
}