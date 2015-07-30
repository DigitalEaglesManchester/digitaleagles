package eu.mhutti1.digitaleagles.digitaleagles;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Isaac on 28/07/2015.
 */
public class Fragment2 extends NavigationControl.PlaceholderFragment implements AdapterView.OnItemClickListener {
    ListView list;
    ArrayList<String> textList;
    ArrayAdapter<String> listAdapter;
    int[] textIds;
    String[] datetime;
    ArrayList<DBResponseBean> beans;
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

        list = (ListView) thisActivity.findViewById(R.id.listView2);
        list.setOnItemClickListener(this);
        textList = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(thisActivity, android.R.layout.simple_list_item_1, textList);
        list.setAdapter(listAdapter);
        dataService = new DatabaseHandler(thisActivity);
        SQLiteDatabase a = dataService.getWritableDatabase();
        dataService.data = a;
        textIds = new int[20];
        datetime = new String[20];

        beans = dataService.getResponses(20);

        int i = 0;
        for (DBResponseBean bean : beans){
            textIds[i] = bean.getResponse_id();
            datetime[i] = bean.getTime() + " on "+ bean.getDate();
            listAdapter.add(datetime[i]);
            i++;
        }
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        //Toast.makeText(thisActivity.getApplicationContext(),Integer.toString(textIds[position]), Toast.LENGTH_SHORT).show();

    }



}
