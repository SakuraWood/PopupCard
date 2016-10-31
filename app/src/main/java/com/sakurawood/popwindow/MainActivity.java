package com.sakurawood.popwindow;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sakurawood.popupcard.ConvertUtils;
import com.sakurawood.popupcard.PopupCard;
import com.sakurawood.rcvbaseadapter.base.BaseViewHolder;
import com.sakurawood.rcvbaseadapter.base.RcvBaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PopupCard popupCardView;
    PopupCard popupCardView2;
    TextView textView;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    RcvBaseAdapter rcvBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        popupCardView = new PopupCard(this);
        popupCardView2 = new PopupCard(this);
        textView = (TextView) findViewById(R.id.text);
        linearLayout = (LinearLayout) LayoutInflater
                .from(getApplicationContext()).inflate(R.layout.layout_viewgroup, null);
        recyclerView = (RecyclerView) linearLayout.findViewById(R.id.list);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        List<String> list = new ArrayList<String>() {
            {
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
                add("22");
            }
        };
        rcvBaseAdapter = new RcvBaseAdapter<String>(getApplicationContext(), R.layout.text_layout, list) {
            @Override
            protected void convert(BaseViewHolder holder, String item, int position) {
                holder.setText(R.id.txt, item);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rcvBaseAdapter);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupCardView.dismiss();
            }
        });
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
//                ConvertUtils.dp2px(this, 150), ConvertUtils.dp2px(this, 250));
//        popupCardView.setLayoutParams(layoutParams);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                popupCardView.setHeight(250).setWidth(150).setX(50).setY(15)
//                        .setW(10).setRadis(120).setRound(10).setColor(Color.BLUE)
//                        .setLocationX(1).setLocationY(1)
//                        .showContentAt(textView, linearLayout, relativeLayout);

                popupCardView2.setHeight(250).setWidth(150).setX(50).setY(15)
                        .setW(10).setRadis(120).setRound(10).setColor(Color.BLUE)
                        .setLocationX(100).setLocationY(300).setDown(true)
                        .showContentAt(textView, linearLayout, relativeLayout);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int[] location = new int[2];
                textView.getLocationInWindow(location);
                int x = location[0];
                int y = location[1];
                Log.e("popupcard", ConvertUtils.px2dp(getApplicationContext(), textView.getLeft())
                        + "   " + ConvertUtils.px2dp(getApplicationContext(), textView.getTop())
                        + "   " + ConvertUtils.px2dp(getApplicationContext(), textView.getRight())
                        + "    " + ConvertUtils.px2dp(getApplicationContext(), textView.getBottom())
                        + "    " + ConvertUtils.px2dp(getApplicationContext(), x)
                        + "    " + ConvertUtils.px2dp(getApplicationContext(), y)
                        + "   " + ConvertUtils.px2dp(getApplicationContext(), textView.getWidth()));
            }
        }).start();


//        ViewGroup vg = new RelativeLayout(getApplicationContext());
//        ViewGroup.LayoutParams layoutParams = new ActionBar.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        vg.setLayoutParams(layoutParams);
//        vg.setPadding(ConvertUtils.dp2px(getApplicationContext(), 15),
//                ConvertUtils.dp2px(getApplicationContext(), 15),
//                ConvertUtils.dp2px(getApplicationContext(), 15),
//                ConvertUtils.dp2px(getApplicationContext(), 15));
//        vg.setBackgroundColor(Color.BLACK);
    }
}
