package eu.mhutti1.digitaleagles.digitaleagles;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Isaac on 28/07/2015.
 */
public class Fragment4 extends NavigationControl.PlaceholderFragment {
    ListView list;
    ArrayList<String> textList;
    ArrayAdapter<String> listAdapter;
    int[] textIds;
    String[] datetime;
    DBResponseBean[] beans;
    public DatabaseHandler dataService;
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
