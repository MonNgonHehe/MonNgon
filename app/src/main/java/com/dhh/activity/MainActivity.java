package com.dhh.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.dhh.asynctask.TaskDanhMucCon;
import com.dhh.database.SqliteDBFood;
import com.dhh.fragment.FragmentAction;
import com.dhh.monngon.R;
import com.dhh.object.DanhMucCon;
import com.dhh.object.MonAn;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import java.util.ArrayList;

import duong.ChucNangPhu;

import static com.dhh.database.SqliteDBFood.PATH;

/**
 * 26/2/2017
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String KEY_DANH_MUC_TO = "dmt";
    public static final String KEY_MON_AN = "mon an";
    private static final String LIST_DANH_MUC_CON = "list danh muc con chua phan loai";
    private SqliteDBFood duLieu;
    private ArrayList<DanhMucCon> danhMucCons;
    private ArrayList<MonAn> monNgons;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initData();

    }

    private void setView() {
        setContentView(R.layout.activity_main);

    }

    private void setViewMain() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setUIApp();
    }

    private void setUIApp() {


    }

    private void initData() {
        duLieu = new SqliteDBFood(this);
        try {
            if (duLieu.checkDB()) {
                startDanhMucCon();
            } else { // k có thì copy database và khởi tạo lại
                duLieu.getDuongSQLite().copyDataBase(this, PATH, "mon_ngon.sqlite");
                initData();
            }
        } catch (Exception e) {
        }
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
//            SearchView searchView = (SearchView) item.getActionView();
//            EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//            searchEditText.setTextColor(getResources().getColor(R.color.black));
//            searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));
//            searchEditText.setBackgroundResource(R.drawable.back_ground_edittext);
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    return false;
//                }
//            });

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * -----------------------------Event
     * menu navigation select
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        switch (id) {
            case R.id.nav_lam_banh:
                selectedItemNavigation(id);
                return true;
            case R.id.nav_mon_an_theo_bua:
                selectedItemNavigation(id);
                return true;
            case R.id.nav_theo_van_hoa:
                selectedItemNavigation(id);
                return true;
            case R.id.nav_theo_cach_che_bien:
                selectedItemNavigation(id);
                return true;
            case R.id.nav_theo_thuc_pham:
                selectedItemNavigation(id);
                return true;
            case R.id.nav_kheo_tay:
                selectedItemNavigation(id);
                return true;
        }
        return true;

    }

    public void selectedItemNavigation(int id) {

        String idDanhMucto = "";
        switch (id) {
            case R.id.nav_lam_banh:
                idDanhMucto = "0";
                break;
            case R.id.nav_mon_an_theo_bua:
                idDanhMucto = "1";
                break;
            case R.id.nav_theo_van_hoa:
                idDanhMucto = "2";
                break;
            case R.id.nav_theo_cach_che_bien:
                idDanhMucto = "3";
                break;
            case R.id.nav_theo_thuc_pham:
                idDanhMucto="4";
                break;
            case R.id.nav_kheo_tay:
                idDanhMucto = "5";
                break;
        }
        setViewFragmentAction(idDanhMucto);
    }


    public void setViewFragmentAction(String idDanhMucto) {
        FragmentAction fragmentAction = new FragmentAction();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DANH_MUC_TO, idDanhMucto);
        fragmentAction.setArguments(bundle);
        transaction.replace(R.id.frame_fragment, fragmentAction);
        transaction.commit();
    }
    public ArrayList<DanhMucCon> getDanhMucCons() {
        return danhMucCons;
    }

    public void startDanhMucCon() {
        TaskDanhMucCon taskDanhMucCon = new TaskDanhMucCon(this, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if ((ArrayList<DanhMucCon>) msg.obj != null) {
                    setDanhMucCons((ArrayList<DanhMucCon>) msg.obj);
                    setViewMain();
                }
            }
        });
        taskDanhMucCon.execute();
    }
    public void setDanhMucCons(ArrayList<DanhMucCon> danhMucCons) {
        ChucNangPhu.showLog("setDanhMucCons " + danhMucCons.size());
        this.danhMucCons = danhMucCons;
    }

}
