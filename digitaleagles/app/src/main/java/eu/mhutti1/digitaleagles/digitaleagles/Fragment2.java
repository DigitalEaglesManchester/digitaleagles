package eu.mhutti1.digitaleagles.digitaleagles;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Isaac on 28/07/2015.
 */
public class Fragment2 extends NavigationControl.PlaceholderFragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
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
       // ImageButton


        list = (ListView) thisActivity.findViewById(R.id.listView2);
        list.setOnItemClickListener(this);
        list.setOnItemLongClickListener(this);
        //list.seton
        textList = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(thisActivity, android.R.layout.simple_list_item_1, textList);
        list.setAdapter(listAdapter);
        dataService = new DatabaseHandler(thisActivity);
        SQLiteDatabase a = dataService.getWritableDatabase();
        dataService.data = a;
        textIds = new int[20];
        datetime = new String[20];
        NavigationControl act = (NavigationControl) thisActivity;
        LatLng latng = act.latlon;
        String search = act.search;



        if (latng!=null) {
            beans = dataService.getResponses(20, latng);
        }else{

            if (search!=null){
                beans = dataService.getResponses(20,search);

            }else{
                beans = dataService.getResponses(20);
            }
        }



        int i = 0;
        for (DBResponseBean bean : beans){
            textIds[i] = bean.getResponse_id();
            datetime[i] = bean.getTime() + " on "+ bean.getDate();
            listAdapter.add(datetime[i]);
            i++;
        }
        listAdapter.notifyDataSetChanged();
    }
Boolean shorta =true;
    @Override
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        if (shorta) {
            listAdapter.clear();
            String response;
            response = beans.get(position).getResponse();
            ArrayList<String> parts = new ArrayList<String>(Arrays.asList(response.split(";")));
            for (String part : parts) {
                listAdapter.add(part.replace("*", "'"));
            }
            listAdapter.notifyDataSetChanged();
            shorta = false;
        }

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> l, View v, int position, long id)  {
                Log.i("test", String.valueOf(position));

        String response = beans.get(position).getResponse();
        //ArrayList<String> parts = new ArrayList<String>(Arrays.asList(response.split(";")));
        //for (String part : parts) {
         //   listAdapter.add(part.replace("*", "'"));
        //}

        String shareBody =  response.replace(";","\n\n");
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share Using"));

        return true;
    }


}
