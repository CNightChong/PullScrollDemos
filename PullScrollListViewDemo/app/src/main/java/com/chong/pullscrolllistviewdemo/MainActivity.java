package com.chong.pullscrolllistviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends Activity {
    private String[] mStrings = {"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler"};
    private LinkedList<String> mListItems;
    private PullScrollListView mListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mListView = (PullScrollListView) findViewById(R.id.pull_list_view);
        mListItems = new LinkedList<>();
        mListItems.addAll(Arrays.asList(mStrings));
        mAdapter = new ArrayAdapter<>(this, R.layout.item_layout, mListItems);

        // 为ListView设置一个透明的headerView
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.headerview, mListView, false);
        // 设置headerview不可点击
        mListView.addHeaderView(view, null, false);

        // 设置topView
        ImageView headerView = (ImageView) findViewById(R.id.background_img);
        mListView.setmTopView(headerView);

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "position" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
