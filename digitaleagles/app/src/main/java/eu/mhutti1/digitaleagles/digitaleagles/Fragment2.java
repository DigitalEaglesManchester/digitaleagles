package eu.mhutti1.digitaleagles.digitaleagles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Isaac on 28/07/2015.
 */
public class Fragment2 extends NavigationControl.PlaceholderFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.screen2, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
