package com.chong.overscrolllistdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends Activity {
    private String[] mStrings = {"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler"};
    private LinkedList<String> mListItems;
    private OverScrollList mListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mListView = (OverScrollList) findViewById(R.id.listview);

        // 设置topView
        ImageView topView = (ImageView) findViewById(R.id.background_img);
        mListView.setTopView(topView);

        // 为ListView设置一个透明的headerView
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.headerview, mListView, false);
        // mListView.addHeaderView(view);
        // 设置headerview不可点击
        mListView.addHeaderView(view, null, false);
        // 初始化Adapter
        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));
        mAdapter = new ArrayAdapter<String>(this, R.layout.item_layout, mListItems);

        mListView.setAdapter(mAdapter);

    }
}
