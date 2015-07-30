package eu.mhutti1.digitaleagles.digitaleagles;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by tony on 28/07/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final String dbName="Transcription";
    private static final String tblName="tblResponse";
    private static final String colID="response_id";
    private static final String colResponse="response";
    private static final String colDate="date";
    private static final String colTime="time";
    private static final String colLat="latitude";
    private static final String colLong="longitude";
    SQLiteDatabase data;

    public DatabaseHandler(Context context)
    {
        super(context, dbName, null, 33);
    }

    @Override
    public void onCreate(SQLiteDatabase db) //Create tables and columns
    {

        db.execSQL("CREATE TABLE " + tblName + " ( " + colID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + colResponse + " TEXT, " + colDate + " TEXT , " + colTime + " TEXT, " + colLat + " TEXT, " + colLong + " TEXT )");
       // db.execSQL("INSERT INTO " + tblName + " (" + colID + ", " + colResponse + ", " + colDate + ", " + colTime + ", " + colLat + ", " + colLong + ") VALUES ('', 'test123', 'monday','miday','mylat','mylong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)   //Deletes and recreates the tables
    {
        //Drops the table
        db.execSQL("DROP TABLE IF EXISTS " + tblName);

        //Create the table
        onCreate(db);
    }
    public String[] debug() {
        String[] response = new String[100];
        ArrayList<String> arrTblNames = new ArrayList<String>();
        Cursor c = data.rawQuery("SELECT * FROM " + tblName + "", null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                arrTblNames.add(c.getString(c.getColumnIndex(colResponse)));
                Log.i("mytag", c.getString(c.getColumnIndex(colResponse)));
                c.moveToNext();
            }
        }
        return response;
    }
    public void addResponse(DBResponseBean bean){
        data.execSQL("INSERT INTO " + tblName + " ("+colResponse + ", " + colDate + ", " + colTime + ", " + colLat + ", " + colLong + ") VALUES ('"+bean.getResponse()+"', '"+bean.getDate()+"','"+bean.getTime()+"','"+bean.getLatitude()+"','"+bean.getLongitude()+"')");
    }

    public DBResponseBean getResponse(int id){
        DBResponseBean myBean = new DBResponseBean();
        ArrayList<String> bean = new ArrayList<String>();
        Cursor c = data.rawQuery("SELECT * FROM " + tblName + " WHERE "+ colID + " = "+id+"", null);
        //Cursor c = data.rawQuery("SELECT * FROM " + tblName, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                //bean.add(c.getString(c.getColumnIndex(colResponse)));


                //Log.i("mytag", c.getString(0));
                myBean = new DBResponseBean(String.valueOf( c.getInt(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
                c.moveToNext();
            }
        }

        return myBean;
    }
    public ArrayList<DBResponseBean> getResponses(int number){
        ArrayList<DBResponseBean> myBean = new ArrayList<>();
        ArrayList<String> bean = new ArrayList<String>();
        Cursor c = data.rawQuery("SELECT * FROM " + tblName +" ORDER BY "+ colID + " DESC LIMIT "+ number, null);
        //Cursor c = data.rawQuery("SELECT * FROM " + tblName, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                //bean.add(c.getString(c.getColumnIndex(colResponse)));


                //Log.i("mytag", c.getString(0));
                myBean.add(new DBResponseBean(String.valueOf( c.getInt(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5)));
                c.moveToNext();
            }
        }

        return myBean;
    }


}
    /*
        Write CRUD methods below using
     */

