package io.geeteshk.settingsx;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements DrawerFragment.DrawerListener {

    Drawable oldDrawable;
    DrawerFragment mFragment;
    Toolbar mToolbar;
    View oldView;

    int[] resources = {R.drawable.ic_general_selected, R.drawable.ic_advanced_selected, R.drawable.ic_device_selected};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mFragment.init(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mFragment.setListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Roboto-Medium.ttf");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings ||
                super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        if (oldView != null) {
            ImageView oldImage = (ImageView) oldView.findViewById(R.id.nav_icon);
            TextView oldText = (TextView) oldView.findViewById(R.id.title);
            oldImage.setImageDrawable(oldDrawable);
            oldText.setTextColor(0xff000000);
            oldText.setTypeface(Typeface.DEFAULT);
        }

        oldView = view;
        ImageView imageView = (ImageView) view.findViewById(R.id.nav_icon);
        TextView textView = (TextView) view.findViewById(R.id.title);
        oldDrawable = imageView.getDrawable();
        imageView.setImageResource(resources[position]);
        textView.setTextColor(0xff2196f3);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        mToolbar.setTitle(textView.getText());
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new GeneralFragment();
                break;
            case 1:
                fragment = new AdvancedFragment();
                break;
            case 2:
                fragment = new DeviceFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }
    }
}
