package com.qtking.mdpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    public static final String FRUIT_NAME = "fruit_name";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fruit_image_view)
    ImageView mIvFruit;
    @BindView(R.id.collapsing_toobar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.fruit_content_text)
    TextView mTvFtuitContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        ButterKnife.bind(this);

        Intent intent = getIntent();


        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
        Logger.d("fruit_name : " + fruitName);
        Logger.d("fruit_image_id : " + fruitImageId);

        Glide.with(this).load(fruitImageId).into(mIvFruit);
        mCollapsingToolbarLayout.setTitle(fruitName);

        String friutContent = generateFruitContentText(fruitName);
        mTvFtuitContent.setText(friutContent);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }

    private String generateFruitContentText(String fruitName) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            stringBuilder.append(fruitName);
        }
        return stringBuilder.toString();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
