package eu.mhutti1.digitaleagles.digitaleagles;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.InflateException;
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

import java.util.ArrayList;

public class Fragment3 extends NavigationControl.PlaceholderFragment {
    GoogleMap map;
    View rootView;
    ArrayList<DBResponseBean> beans;
    public DatabaseHandler dataService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup parent =null;
        if (rootView != null) {
            parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
                Log.i("ONCREATE DEBUG", "Parent Null - View Removed");
            }else {
                parent.addView(rootView);
                Log.i("ONCREATE DEBUG", "Parent NOT Null - View Added");
            }
        }
        try
        {
            rootView = inflater.inflate(R.layout.screen3, container, false);
        }
        catch (InflateException e) {
            Log.i("ONCREATE DEBUG", "INFLATE EXCEPTION CAUGHT!");
            try {
                parent.addView(rootView);
            }catch(NullPointerException i ){

            }
        }

        return rootView;


}

    @Override
    public void onResume() {
        super.onResume();
            map = ((MapFragment) thisActivity.getFragmentManager().findFragmentById(R.id.map)).getMap();


            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker arg0) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, NavigationControl.PlaceholderFragment.newInstance(2, thisActivity))
                            .commit();
                    NavigationControl act = (NavigationControl)thisActivity;
                    act.latlon = arg0.getPosition();

                    return true;
                }

            });
            GetData data = new GetData();
            String Date = data.getDate();
            String Time  = data.getTime();
            Log.i("test",Date);
            Log.i("test", Time);
            dataService = new DatabaseHandler(thisActivity);
            SQLiteDatabase a = dataService.getWritableDatabase();
            dataService.data = a;
            beans = dataService.getResponses(20);
            int k = 0;
            for (DBResponseBean bean : beans){
                LatLng Loc = new LatLng(bean.getLatitude(), bean.getLongitude());
                map.addMarker(new MarkerOptions().position(Loc).title(String.valueOf(bean.getResponse_id())));
                map.moveCamera(CameraUpdateFactory.newLatLng(Loc));
                k++;
            }

      //  }
    }


}
