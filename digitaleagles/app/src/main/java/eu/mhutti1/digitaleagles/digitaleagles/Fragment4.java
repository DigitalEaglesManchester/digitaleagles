package eu.mhutti1.digitaleagles.digitaleagles;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jacques on 31/07/2015.
 */

public class Fragment4 extends NavigationControl.PlaceholderFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.screen4, container, false);
        return rootView;
    }
    EditText txt;
    @Override
    public void onResume(){
        txt = (EditText) thisActivity.findViewById(R.id.searchText);
        Button button = (Button) thisActivity.findViewById(R.id.search);
        super.onResume();

        try {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   //Log.i("TEST", txt.getText().toString());
                    InputMethodManager inputManager = (InputMethodManager)
                            thisActivity.getSystemService(thisActivity.getApplicationContext().INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(thisActivity.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, NavigationControl.PlaceholderFragment.newInstance(2, thisActivity))
                            .commit();
                    NavigationControl act = (NavigationControl)thisActivity;
                    act.search =  txt.getText().toString();
                }
            });
        }catch (Exception e){

        }
    }




}