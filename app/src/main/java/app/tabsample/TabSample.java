package app.tabsample;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


/**
 * @author Adil Soomro
 *
 */
public class TabSample extends TabActivity {
	/** Called when the activity is first created. */
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setTabs() ;
	}
    private String getStringFromSrc(int stringId){
        return getResources().getString(stringId);
    }
	private void setTabs()
	{
		addTab(getStringFromSrc(R.string.tab_detection), R.drawable.tab_home, DetectionActivity.class);
		addTab(getStringFromSrc(R.string.tab_data), R.drawable.tab_search, DataActivity.class);
		
		addTab(getStringFromSrc(R.string.tab_community), R.drawable.tab_home, CommunityActivity.class);
		addTab(getStringFromSrc(R.string.tab_me), R.drawable.tab_search, MeActivity.class);
	}
	
	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);	
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
}