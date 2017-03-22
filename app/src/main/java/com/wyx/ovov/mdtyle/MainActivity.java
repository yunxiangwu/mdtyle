package com.wyx.ovov.mdtyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
//    private TextView tvText;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView= (NavigationView) findViewById(R.id.nav_view);
        recyclerView= (RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//       tvText= (TextView) findViewById(R.id.tv_text);
        fab= (FloatingActionButton) findViewById(R.id.fab);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.mipmap.gjfh_233);
        }
//        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"FloatingActionButton",Toast.LENGTH_SHORT).show();
                Snackbar.make(view,"FloatingActionButton",Snackbar.LENGTH_SHORT)
                        .setAction("undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"datasourse",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        initData();

    }

    private void initData() {
        List<FruitBean> mDatas=new ArrayList<>();
        for (int i = 0; i <12 ; i++) {
            FruitBean fruit=new FruitBean();
            if(i%2==0){
                fruit.setName("B");
                fruit.setId(R.mipmap.b);}
                else{
            fruit.setName("A");
            fruit.setId(R.mipmap.a);}
            mDatas.add(fruit);
        }
        CommonAdapter<FruitBean> adapter=new CommonAdapter<FruitBean>(this,R.layout.item_cardview,mDatas) {
            @Override
            protected void convert(ViewHolder holder, FruitBean fruitBean, int position) {
                holder.setText(R.id.tv_name,fruitBean.getName());
                holder.setImageResource(R.id.iv_img,fruitBean.getId());
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                startActivity(new Intent(MainActivity.this,FruitDetailActivity.class));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this,"backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"setting",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }


        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_call:
                Toast.makeText(this,"call",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_friend:
                Toast.makeText(this,"friend",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_location:
                Toast.makeText(this,"location",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_mail:
                Toast.makeText(this,"mail",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_task:
                Toast.makeText(this,"task",Toast.LENGTH_SHORT).show();
                break;
        }


        return true;
    }
}
