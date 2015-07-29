package eu.mhutti1.digitaleagles.digitaleagles;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tony on 28/07/2015.
 */
public class TimeDateService extends Service
{
    private String timeStamp;
    private String date = "";
    private String time = "";

    public TimeDateService()
    {

    }

    public void onCreate()
    {

    }

    public void onCreateCommand(Intent intent, int flags, int startId)
    {
        Toast.makeText(this, "Service started.", Toast.LENGTH_SHORT); //Toast to make sure service has started sucessfully

        timeStamp = Clock.getTimeStamp();   //get timeStamp of Local Time
        
        date = parseDate(timeStamp);          //parse the TimeStamp into a data format
        time = parseTime(timeStamp);          //Parse the TimeStamp into a time format



    }

    private String parseDate(String timeStamp)
    {

        StringBuilder sb = new StringBuilder(timeStamp);

        date = sb.substring(0,7 ); // Should return the first 6 characters

        return date;
    }
    private String parseTime(String timeStamp)
    {
        StringBuilder sb = new StringBuilder(timeStamp);

        time = sb.substring(8, timeStamp.length());

        return time;
    }

    public IBinder onBind(Intent intent)
    {
        return null; //Non bound Service
    }

}


