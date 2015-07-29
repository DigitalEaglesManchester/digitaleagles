package eu.mhutti1.digitaleagles.digitaleagles;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment3 extends NavigationControl.PlaceholderFragment {
    GoogleMap map;
    double[] Lat = {1,2,3,4,5,6,7,8,9,10};
    double[] Lng = {1,2,3,4,5,6,7,8,9,10};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.screen3, container, false);
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
            map = ((MapFragment) thisActivity.getFragmentManager().findFragmentById(R.id.map)).getMap();
        for(int i=1; i< Lat.length; i++){
            LatLng Loc = new LatLng(Lat[i], Lng[i]);
            map.addMarker(new MarkerOptions().position(Loc).title("Marker"));
            map.moveCamera(CameraUpdateFactory.newLatLng(Loc));

            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker arg0) {
                    if (arg0.getTitle().equals("Marker")) {
                        Toast toast = Toast.makeText(thisActivity.getApplicationContext(), "This is a message displayed in a Toast", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    return true;
                }

            });
            GetData data = new GetData();
            String [] LatLong = data.findLocation();
            String Date = data.getDate();
            String Time  = data.getTime();
            Log.i("test",Date);
            Log.i("test", Time);
        }
    }
}
