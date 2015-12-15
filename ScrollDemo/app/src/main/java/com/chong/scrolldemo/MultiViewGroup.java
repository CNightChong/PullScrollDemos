package com.chong.scrolldemo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

//自定义ViewGroup ， 包含了三个LinearLayout控件，存放在不同的布局位置，通过scrollBy或者scrollTo方法切换
public class MultiViewGroup extends ViewGroup {

    private Context mContext;

    private static String TAG = "MultiViewGroup";
    private int measureCout = 0;
    private int layoutCout = 0;

    public MultiViewGroup(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public MultiViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "MultiViewGroup Construction: getWidth() == " + getWidth() + ", " + "getHeight() == " + getHeight());
        mContext = context;
        setBackgroundColor(Color.GRAY);
        init();
    }

    private void init() {
        // 初始化3个 LinearLayout控件
        LinearLayout oneLL = new LinearLayout(mContext);
        oneLL.setBackgroundColor(Color.RED);
        Button button1 = new Button(mContext);
        button1.setText("Button11111111");
        oneLL.addView(button1);
        addView(oneLL);

        LinearLayout twoLL = new LinearLayout(mContext);
        twoLL.setBackgroundColor(Color.YELLOW);
        Button button2 = new Button(mContext);
        button2.setText("Button2222222222");
        twoLL.addView(button2);
        addView(twoLL);

        LinearLayout threeLL = new LinearLayout(mContext);
        threeLL.setBackgroundColor(Color.BLUE);
        Button button3 = new Button(mContext);
        button3.setText("Button3333333");
        threeLL.addView(button3);
        addView(threeLL);
    }

    // measure过程
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureCout++;
        Log.i(TAG, "--- start onMeasure -- " + measureCout);

        // 设置该ViewGroup的大小
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);

        int childCount = getChildCount();
        Log.i(TAG, "--- onMeasure childCount is -->" + childCount);
        Log.i(TAG, "width == " + width + ", " + "height == " + height);
        Log.i(TAG, "onMeasure: getWidth() == " + getWidth() + ", " + "getHeight() == " + getHeight());
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            // 设置每个子视图的大小 ， 即全屏
            child.measure(MainActivity.screenWidth, MainActivity.scrrenHeight);
        }
    }

    // layout过程
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutCout++;
        Log.i(TAG, "--- start onLayout -- " + layoutCout);
        int startLeft = 0; // 每个子视图的起始布局坐标
        int startTop = 10; /// 间距设置为10px 相当于 android:marginTop= "10px"
        int childCount = getChildCount();
        Log.i(TAG, "--- onLayout childCount is -->" + childCount);
        Log.i(TAG, "onLayout: getWidth() == " + getWidth() + ", " + "getHeight() == " + getHeight());
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(startLeft, startTop,
                    startLeft + MainActivity.screenWidth,
                    startTop + MainActivity.scrrenHeight);
            startLeft = startLeft + MainActivity.screenWidth; //校准每个子View的起始布局位置
        }
    }

}
