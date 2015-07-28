package eu.mhutti1.digitaleagles.digitaleagles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by Isaac on 28/07/2015.
 */
public class
        Fragment3 extends NavigationControl.PlaceholderFragment implements OnMapReadyCallback{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.screen3, container, false);
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();

    }
}
