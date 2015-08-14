package app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import app.delegates.SharedPreferenceDelegate;
import app.tabsample.R;


public class MeActivity extends Activity {
    private SharedPreferenceDelegate sharedPreferenceDelegate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        sharedPreferenceDelegate = new SharedPreferenceDelegate(this);
    }

    public void clearAllData(View view) {
        this.sharedPreferenceDelegate.clearAllData();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.me, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
