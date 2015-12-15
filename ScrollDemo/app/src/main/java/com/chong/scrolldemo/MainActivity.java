package com.chong.scrolldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MultiViewGroup mulTiViewGroup;

    public static int screenWidth;  // 屏幕宽度
    public static int scrrenHeight;  //屏幕高度

    private int curscreen = 0;   // 当前位于第几屏幕  ，共3个"屏幕"， 3个LinearLayout

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获得屏幕分辨率大小
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
        scrrenHeight = metric.heightPixels;
        System.out.println("screenWidth && scrrenHeight ---> " + screenWidth + " ---- " + scrrenHeight);

        setContentView(R.layout.activity_main);

        //获取自定义视图的空间引用
        mulTiViewGroup = (MultiViewGroup) findViewById(R.id.mymultiViewGroup);

        Button bt_scrollLeft = (Button) findViewById(R.id.bt_scrollLeft);
        Button bt_scrollRight = (Button) findViewById(R.id.bt_scrollRight);

        bt_scrollLeft.setOnClickListener(this);
        bt_scrollRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_scrollLeft:
                if (curscreen > 0) {  //防止屏幕越界
                    curscreen--;
                    Toast.makeText(MainActivity.this, "第" + (curscreen + 1) + "屏", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "当前已是第一屏", Toast.LENGTH_SHORT).show();
                }
                // mulTiViewGroup.scrollTo(curscreen * screenWidth , 0);
                // getScrollX()得到的是MulTiViewGroup的左上角距离MulTiViewGroup在屏幕上的显示区域的左上角在X轴上偏移量
                // getScrollX() > 0，MulTiViewGroup 内容(子View) 左移，getScrollX() < 0，MulTiViewGroup 内容(子View) 右移
                // getScrollY() > 0，MulTiViewGroup 内容(子View) 上移，getScrollY() < 0，MulTiViewGroup 内容(子View) 下移
                int a = mulTiViewGroup.getScrollX();
                System.out.println("prev_before_getScrollX====" + a);
                int b = mulTiViewGroup.getScrollY();
                System.out.println("prev_before_getScrollY====" + b);
                for (int i = 0; i < mulTiViewGroup.getChildCount(); i++) {
                    View view = mulTiViewGroup.getChildAt(i);
                    System.out.println("ChildView " + i + " prev_before_getLeft==" + view.getLeft());
                    System.out.println("ChildView " + i + " prev_before_getTop==" + view.getTop());
                }
                // x > 0表示视图(View或ViewGroup)的 内容(子View) 从右向左滑动;反之，从左向右滑动
                // y > 0表示视图(View或ViewGroup)的 内容(子View) 从下向上滑动;反之，从上向下滑动
                mulTiViewGroup.scrollBy(10, 50);//内容(子View)左移10px，上移50px
                a = mulTiViewGroup.getScrollX();
                System.out.println("prev_after_getScrollX====" + a);
                b = mulTiViewGroup.getScrollY();
                System.out.println("prev_after_getScrollY====" + b);
                for (int i = 0; i < mulTiViewGroup.getChildCount(); i++) {
                    View view = mulTiViewGroup.getChildAt(i);
                    System.out.println("ChildView " + i + " prev_after_getLeft==" + view.getLeft());
                    System.out.println("ChildView " + i + " prev_after_getTop==" + view.getTop());
                }
                break;
            case R.id.bt_scrollRight:
                if (curscreen < 2) { //防止屏幕越界
                    curscreen++;
                    Toast.makeText(MainActivity.this, "第" + (curscreen + 1) + "屏", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "当前已是最后一屏", Toast.LENGTH_SHORT).show();
                }
                int x = mulTiViewGroup.getScrollX();
                System.out.println("next_before_getScrollX====" + x);
                int y = mulTiViewGroup.getScrollY();
                System.out.println("next_before_getScrollY====" + y);
                for (int i = 0; i < mulTiViewGroup.getChildCount(); i++) {
                    View view = mulTiViewGroup.getChildAt(i);
                    System.out.println("ChildView " + i + " next_before_getLeft==" + view.getLeft());
                    System.out.println("ChildView " + i + " next_before_getTop==" + view.getTop());
                }
                // MulTiViewGroup 内容(子View)左移到(curscreen * screenWidth,30)坐标处，参考坐标是MulTiViewGroup的左上角，并将该点移动到MulTiViewGroup布局坐标的(0,30)点处
                // MulTiViewGroup布局坐标是以MulTiViewGroup在屏幕中显示区域的左上角为原点
                mulTiViewGroup.scrollTo(curscreen * screenWidth, -30);
                x = mulTiViewGroup.getScrollX();
                System.out.println("next_after_getScrollX====" + x);
                y = mulTiViewGroup.getScrollY();
                System.out.println("next_after_getScrollY====" + y);
                for (int i = 0; i < mulTiViewGroup.getChildCount(); i++) {
                    View view = mulTiViewGroup.getChildAt(i);
                    System.out.println("ChildView " + i + " next_after_getLeft==" + view.getLeft());
                    System.out.println("ChildView " + i + " next_after_getTop==" + view.getTop());
                }
                break;
        }
    }

}
