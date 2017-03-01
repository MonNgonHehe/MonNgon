package com.dhh.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.dhh.database.DataBaseMonNgon;

/**
 * 26/2/2017
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewMain();
        DataBaseMonNgon dataBaseMonNgon=new DataBaseMonNgon(this);
    }

    private void setViewMain() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        inflater=getLayoutInflater();
        setUIApp();
    }


    private void addTabItem(String tabName) {
        TextView tv3= (TextView) inflater.inflate(R.layout.tab_custem,null).findViewById(R.id.tv_tab);
        tv3.setText(tabName);
        TabLayout.Tab tab= tabLayout.newTab();
        tab.setCustomView(tv3);
        tabLayout.addTab(tab);
    }
    /**
     * set giao dien app
     */
    private void setUIApp() {
//        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorHeight(0);
        inflater=getLayoutInflater();
        addTabItem("123123");
        addTabItem("323");
        addTabItem("43434");
        addTabItem("123123");
        addTabItem("323");
        addTabItem("43434");
        addTabItem("123123");
        addTabItem("323");
        addTabItem("43434");
        addTabItem("123123");
        addTabItem("323");
        addTabItem("43434");
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                tabUISelect=tab.getPosition();
                tab.getCustomView().setBackground(getResources().getDrawable(R.drawable.tab_select));
            }
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().setBackground(getResources().getDrawable(R.drawable.tab_unselect));
            }
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getCustomView().setBackground(getResources().getDrawable(R.drawable.tab_select));
            }
        });
        tabLayout.getTabAt(0).select();
//        tabUISelect=0;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            SearchView searchView = (SearchView)item.getActionView();
            EditText searchEditText = (EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchEditText.setTextColor(getResources().getColor(R.color.black));
            searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));
            searchEditText.setBackgroundResource(R.drawable.back_ground_edittext);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                // mấy câu lệnh này chả có tác dụng j với cái edittext cả  nó vẫn màu vậy
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {


                    return false;
                }
            });

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * -----------------------------Event
     * menu navigation select
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
