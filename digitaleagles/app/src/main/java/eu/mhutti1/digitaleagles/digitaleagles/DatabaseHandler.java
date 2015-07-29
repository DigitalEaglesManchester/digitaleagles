package eu.mhutti1.digitaleagles.digitaleagles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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


    public DatabaseHandler(Context context)
    {
        super(context, dbName, null, 33);
    }

    @Override
    public void onCreate(SQLiteDatabase db) //Create tables and columns
    {
        db.execSQL("CREATE TABLE " + tblName + "(" + colID + " INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " + colResponse + " TEXT NOT NULL, " + colDate + " TEXT NULL, " + colTime + "TEXT NULL, " + colLat + " TEXT NULL, " + colLong + " TEXT NULL");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)   //Deletes and recreates the tables
    {
        //Drops the table
        db.execSQL("DROP TABLE IF EXISTS " + tblName);

        //Create the table
        onCreate(db);
    }

    /*
        Write CRUD methods below using
     */
}
