package eu.mhutti1.digitaleagles.digitaleagles;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

/**
 * Created by Isaac on 28/07/2015.
 */
public class screen1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
       //findViewById(R.id.tex);
    }

}
