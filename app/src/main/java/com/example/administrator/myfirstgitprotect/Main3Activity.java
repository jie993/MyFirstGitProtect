package com.example.administrator.myfirstgitprotect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myfirstgitprotect.Utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


public class Main3Activity extends AppCompatActivity {

    private ViewPager viewPager1;
    private View viewOne;
    private View viewTwo;
    private View viewThree;
    private List<View> vlist = new ArrayList<>();
    public int startX,endX = 0;
    private int currentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        viewPager1 = (ViewPager) findViewById(R.id.viewPager1);
        viewOne = View.inflate(this,R.layout.guide_one,null);
        viewTwo = View.inflate(this,R.layout.guide_two,null);
        viewThree = View.inflate(this,R.layout.guide_three,null);
        vlist.add(viewOne);
        vlist.add(viewTwo);
        vlist.add(viewThree);
        viewPager1.setAdapter(new GuideViewPagerAdapter());
        viewPager1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = (int) motionEvent.getX();
                        if (currentPage == vlist.size()-1){
                            if(startX-endX> ScreenUtils.width/4){
                                startActivity(new Intent(Main3Activity.this,Main2Activity.class));
                            }
                        }
                        break;
                }
                return false;
            }
        });
        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    class GuideViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return vlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position){
            ((ViewPager)container).addView(vlist.get(position));
            return vlist.get(position);
        }
        public void destroyItem(ViewGroup container , int position ,Object object){
            ((ViewPager)container).removeView(vlist.get(position));
        }
    }
}
