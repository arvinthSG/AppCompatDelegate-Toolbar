package apps.sg.com.toolbartestproject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class ToolBarActivity extends Activity implements AppCompatCallback {


    private AppCompatDelegate delegate;
    private MenuInflater inflater;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tool_bar);
//        setSupportActionBar(myToolbar);

        delegate = AppCompatDelegate.create(this, this);
//        myToolbar.inflateMenu(R.menu.menu_tool_bar);

        //we need to call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);
        //we use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_tool_bar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.rl_toolbar);

        //Finally, let's add the Toolbar
        delegate.setSupportActionBar(myToolbar);
        myToolbar.setLogo(getDrawable(R.drawable.qml_app_logo));
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setHomeButtonEnabled(true);
        View logoView = getToolbarLogoView(myToolbar);

        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logo clicked
                Toast.makeText(getApplicationContext(),"Enough of clicks",Toast.LENGTH_SHORT).show();
            }
        });

        //inflater=delegate.getMenuInflater()
//        inflater=delegate.getMenuInflater();

    }
    public View getToolbarLogoView(Toolbar toolbar){
        //check if contentDescription previously was set
        boolean hadContentDescription = android.text.TextUtils.isEmpty(toolbar.getLogoDescription());
        String contentDescription = String.valueOf(!hadContentDescription ? toolbar.getLogoDescription() : "logoContentDescription");
        toolbar.setLogoDescription(contentDescription);
        ArrayList<View> potentialViews = new ArrayList<View>();
        //find the view based on it's content description, set programatically or with android:contentDescription
        toolbar.findViewsWithText(potentialViews,contentDescription, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        //Nav icon is always instantiated at this point because calling setLogoDescription ensures its existence
        View logoIcon = null;
        if(potentialViews.size() > 0){
            logoIcon = potentialViews.get(0);
        }
        //Clear content description if not previously present
        if(hadContentDescription)
            toolbar.setLogoDescription(null);
        return logoIcon;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        delegate.getMenuInflater().inflate(R.menu.menu_tool_bar, menu);
        return true;
    }

//    }
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"Settings ",Toast.LENGTH_SHORT).show();
        }
        if(id ==android.R.id.home){
            Toast.makeText(getApplicationContext(),"enough of clicks",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
}
