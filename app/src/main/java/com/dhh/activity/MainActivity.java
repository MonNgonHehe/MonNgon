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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.dhh.asynctask.TaskDanhMucCon;
import com.dhh.asynctask.TaskMonAn;
import com.dhh.database.SqliteDBFood;
import com.dhh.fragment.FragmentAction;
import com.dhh.monngon.R;
import com.dhh.object.DanhMucCon;
import com.dhh.object.MonAn;

import java.util.ArrayList;
import java.util.List;

import duong.ChucNangPhu;

import static com.dhh.database.SqliteDBFood.PATH;

/**
 * 26/2/2017
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String LIST_DATA = "list_data";
    public static final String THIS = "this";
    public static final String DANH_MUC_CON="danh_muc_con";
    public static final String MON_AN="mon_an";
    public static final String KEY_DANH_MUC_TO = "dmt";
    public static final String KEY_MON_AN = "mon an";

    private TabLayout tabLayout;
    private LayoutInflater inflater;
    private SqliteDBFood duLieu;
    private  ArrayList<DanhMucCon> danhMucCons;
    private ArrayList<MonAn> monAns;
    private List<ArrayList<MonAn>> listArrMonAns =new ArrayList<>();
    private FragmentAction fragmentAction;

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
        inflater=getLayoutInflater();
        setUIApp();
    }

    private void setUIApp() {


    }

    private void initData() {
        duLieu = new SqliteDBFood(this);
        try {
            if (duLieu.checkDB()){
                startDanhMucCon();
//                startMonAn();
            } else { // k có thì copy database và khỏi tạo lại
                duLieu.getDuongSQLite().copyDataBase(this, PATH,"mon_ngon.sqlite");
                initData();
            }
        } catch (Exception e) {}
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
        switch (id){
            case R.id.nav_lam_banh:
                fragmentAction =new FragmentAction();
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
//                transaction.add( R.id.frame_fragment,fragmentAction);
//                transaction.show(fragmentAction);
                Bundle bundle=new Bundle();
                bundle.putString(KEY_DANH_MUC_TO,"0");
                fragmentAction.setArguments(bundle);
                transaction.replace( R.id.frame_fragment,fragmentAction);
                transaction.commit();

        }
        return true;
    }
    public  ArrayList<MonAn> getMonAns(){
//        if (monAns==null){
//            startMonAn();
//        }
        return monAns;
    }
    public ArrayList<DanhMucCon> getDanhMucCons(String iddanhmucto){
        ArrayList<DanhMucCon> dmCon=new ArrayList<>();
        for (DanhMucCon danhMucCon:danhMucCons) {
            if(danhMucCon.getId_danh_muc().equals(iddanhmucto)){
//                startDanhMucCon();
                dmCon.add(danhMucCon);
            }
        }


        return dmCon;
    }
//    public  ArrayList<DanhMucCon> getTabDanhMucCons(){
//        for()
//    }
    public void startDanhMucCon() {
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if ((ArrayList<DanhMucCon>) msg.obj != null){
                    setDanhMucCons((ArrayList<DanhMucCon>) msg.obj);
                    setViewMain();
                }
              //  else initViewIntro();
            }
        };
        TaskDanhMucCon taskDanhMucCon =new TaskDanhMucCon(this,handler);
        taskDanhMucCon.execute();
    }
    public void startMonAn(String idDanhMucCon) {
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if ((ArrayList<MonAn>) msg.obj != null){
                    ChucNangPhu.showLog("handleMessage "+((ArrayList<MonAn>) msg.obj).size());
                    setMonAns((ArrayList<MonAn>) msg.obj);

                }

              //  else initViewIntro();
            }
        };
        TaskMonAn taskMonAn =new TaskMonAn(this,handler);
        taskMonAn.execute(idDanhMucCon);
    }

    public  void setDanhMucCons(ArrayList<DanhMucCon> danhMucCons){
        ChucNangPhu.showLog("setDanhMucCons "+danhMucCons.size());
        this.danhMucCons=danhMucCons;
    }
    public  void setMonAns(ArrayList<MonAn> monAns){
        ChucNangPhu.showLog("setMonAns "+monAns.size());
        this.monAns=monAns;
    }
}
