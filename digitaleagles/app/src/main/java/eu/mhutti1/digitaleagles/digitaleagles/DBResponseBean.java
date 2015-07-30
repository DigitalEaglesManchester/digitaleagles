package eu.mhutti1.digitaleagles.digitaleagles;

import android.util.Log;

/**
 * Created by tony on 28/07/2015.
 */
public class DBResponseBean
{
    private int response_id;
    private String response;
    private String date;
    private String time;
    private double latitude;
    private double longitude;


    public DBResponseBean()
    {
        this.response_id = -1;
        this.response = "null";
        this.date = "null";
        this.time = "null";
        this.latitude = -1;
        this.longitude = -1;
    }

    public DBResponseBean( String response, String date, String time, String latitude, String longitude)
    {
       // this.response_id = response_id;
        response = response.replace("'","*");
        this.response = response;
        this.date = date;
        this.time = time;
        this.latitude =  Double.parseDouble(latitude);
        this.longitude =  Double.parseDouble(longitude);

    }
    public DBResponseBean(String response_id, String response, String date, String time, String latitude, String longitude)
    {
        try {
            this.response_id = Integer.parseInt(response_id);
        } catch (NumberFormatException e) {
           Log.i("hi", "wrong");
            this.response_id = -1;
        }
        response = response.replace("'","*");
        this.response = response;
        this.date = date;
        this.time = time;
        try {
            this.latitude =  Double.parseDouble(latitude);
        } catch (NullPointerException e) {
            Log.i("hi", "wrong");
            this.latitude = -1;
        }
        try {
            this.longitude = Double.parseDouble(longitude);
        } catch (NullPointerException e) {
            Log.i("hi", "wrong");
            this.longitude = -1;
        }

    }
    public int getResponse_id()
    {
        return response_id;
    }

    public void setResponse_id(String response_id)
    {
        this.response_id = Integer.getInteger(response_id);
    }

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude =  Double.parseDouble(latitude);
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude =  Double.parseDouble(longitude);
    }
}
