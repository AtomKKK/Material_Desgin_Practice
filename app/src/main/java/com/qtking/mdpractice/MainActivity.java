package com.qtking.mdpractice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.qtking.mdpractice.adapter.FruitAdapter;
import com.qtking.mdpractice.bean.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.fab)
    FloatingActionButton mFAB;
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    private List<Fruit> mFruitList = new ArrayList<>();

    private Fruit[] fruits = {
            new Fruit("Orange", R.drawable.orange),
            new Fruit("apple", R.drawable.apple),
            new Fruit("banana", R.drawable.banana),
            new Fruit("cherry", R.drawable.cherry),
            new Fruit("grape", R.drawable.grape),
            new Fruit("watermelon", R.drawable.watermelon),
            new Fruit("strawberry", R.drawable.strawberry),
            new Fruit("pear", R.drawable.pear),
            new Fruit("pineapple", R.drawable.pineapple),
            new Fruit("mango", R.drawable.mango)

    };
    private FruitAdapter mFruitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initNavigationView();
        initRecyclerView();
        initRefreshLauout();
    }

    private void initRefreshLauout() {
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();

            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        mFruitAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initRecyclerView() {
        initFruits();
        GridLayoutManager layoutmanager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutmanager);
        mFruitAdapter = new FruitAdapter(mFruitList, this);
        mRecyclerView.setAdapter(mFruitAdapter);
    }

    private void initFruits() {
        mFruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            mFruitList.add(fruits[index]);
        }
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        //此actionBar的具体实现是由ToolBar完成的
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //enable HomeAsUp按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //指定HomeAsUp按钮的icon，其默认是一个箭头
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    private void initNavigationView() {
        mNavigationView.setCheckedItem(R.id.nav_call);//默认选中
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                mDrawerLayout.closeDrawers();
                //@return true to display the item as the selected item
                return true;
            }
        });
    }

    @OnClick(R.id.fab)
    public void fabOnClick(View view) {
//        Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
        Snackbar.make(view, "Hello World", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Undo", Toast.LENGTH_SHORT).show();

            }
        }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_back:
                Logger.d("backup clicked");
                break;
            case R.id.menu_delete:
                Logger.d("delete clicked");
                break;
            case R.id.menu_setting:
                Logger.d("setting clicked");
                break;
            case android.R.id.home:
                //点击homeAsUp弹出侧滑菜单
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
