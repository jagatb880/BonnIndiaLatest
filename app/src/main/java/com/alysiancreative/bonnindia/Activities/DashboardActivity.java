package com.alysiancreative.bonnindia.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alysiancreative.bonnindia.Fragments.ChangePasswordFragment;
import com.alysiancreative.bonnindia.Fragments.CustomerFragment;
import com.alysiancreative.bonnindia.Fragments.CustomerServiceFragment;
import com.alysiancreative.bonnindia.Fragments.DashboardFragment;
import com.alysiancreative.bonnindia.Fragments.OrderRecievedFragment;
import com.alysiancreative.bonnindia.R;
import com.alysiancreative.bonnindia.Utilities.BadgeUtils;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    SharedPreferences sharedPreferences;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        token = sharedPreferences.getString("TOKEN", "");

        /**
         *Setup the DrawerLayout and NavigationView
         */
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mNavigationView = findViewById(R.id.navigationView);

        View header = mNavigationView.getHeaderView(0);

        //final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the AllJobFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new DashboardFragment()).commit();
        toolbar.setTitle(getString(R.string.nav_dashboard));
        toolbar.setTitleTextColor(getResources().getColor(R.color.fontColor));

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the AllJobFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new DashboardFragment()).commit();
//        toolbar.setTitle(getString(R.string.nav_interview));

        /**
         * Setup click events on the Navigation View Items.
         */
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_dashboard) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new DashboardFragment()).commit();
                    toolbar.setTitle(getString(R.string.nav_dashboard));
                }

                if (menuItem.getItemId() == R.id.nav_customer) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new CustomerFragment()).commit();
                    toolbar.setTitle(getString(R.string.nav_customer));
                }

                if (menuItem.getItemId() == R.id.nav_customer_service) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new CustomerServiceFragment()).commit();
                    toolbar.setTitle(getString(R.string.nav_customer_service));
                }

//                if (menuItem.getItemId() == R.id.nav_order_reveive) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView, new OrderRecievedFragment()).commit();
//                    toolbar.setTitle(getString(R.string.nav_order_reveive));
//                }

//                if (menuItem.getItemId() == R.id.nav_order_reveive) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView, new OrderRecievedFragment()).commit();
//                    toolbar.setTitle(getString(R.string.nav_order_reveive));
//                }

                if (menuItem.getItemId() == R.id.nav_change_password) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new ChangePasswordFragment()).commit();
                    toolbar.setTitle(getString(R.string.nav_change_password));
                }

                if (menuItem.getItemId() == R.id.nav_logout) {
                    sharedPreferences.edit().clear().commit();
                    Toast.makeText(getApplicationContext(), "Logout successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }
                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_dashboard, menu);

        // Get the notifications MenuItem and LayerDrawable (layer-list)
        MenuItem item = menu.findItem(R.id.action_alert);
        LayerDrawable icon = (LayerDrawable) item.getIcon();

        // Update LayerDrawable's BadgeDrawable
        BadgeUtils.setBadgeCount(this, icon, 2);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_alert) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
