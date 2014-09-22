package test01.fragment.ybjaa.testfragment01;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ybjaa.extend.view.NavMenuPanel;

import java.util.List;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_my);


        //获得所有的TextView
        getWindow().getDecorView();


        NavMenuPanel panel= (NavMenuPanel) findViewById(R.id.view);

        TextView view01=new TextView(this);


        int height=(int)(30/getResources().getDisplayMetrics().scaledDensity+0.5f);

        LinearLayout.LayoutParams params=new  LinearLayout.LayoutParams(0,30);
        params.weight=1;

        view01.setLayoutParams(params);

        view01.setTextColor(Color.parseColor("#000000"));
        view01.setText("4444");


        panel.addView(view01);

        panel.setFragmentManager(getFragmentManager());





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
