package eu.mhutti1.digitaleagles.digitaleagles;

/**
 * Created by tony on 28/07/2015.
 */
public class DBResponseBean
{
    private int response_id;
    private String response;
    private String date;
    private String time;
    private String latitude;
    private String longitude;


    public DBResponseBean()
    {

    }

    public DBResponseBean( String response, String date, String time, String latitude, String longitude)
    {
       // this.response_id = response_id;
        this.response = response;
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;

    }

  /*  public int getResponse_id()
    {
        return response_id;
    }*/

   /* public void setResponse_id(int response_id)
    {
        this.response_id = response_id;
    }
*/
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

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }
}
