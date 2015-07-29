package eu.mhutti1.digitaleagles.digitaleagles;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jacques on 29/07/2015.
 */
public class GetData extends NavigationControl.PlaceholderFragment{

    public String[] findLocation()
    {
        LocationManager lm = (LocationManager) thisActivity.getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        String slatitude = Double.toString(latitude);
        String slongitude = Double.toString(longitude);
        String[] LatLong = {slatitude,slongitude};;
        return LatLong;
    }

    public String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public String getTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = df.format(c.getTime());
        return formattedTime;
    }
}
