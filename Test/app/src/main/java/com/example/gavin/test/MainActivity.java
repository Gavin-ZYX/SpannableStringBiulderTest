package com.example.gavin.test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * TabLayout结合Viewpager使用
 */
public class MainActivity extends AppCompatActivity {

    private CustomViewPager mViewPager;
    private TabLayout mTabLayout;
    private SimpleFragmentPagerAdapter mAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private String [] mTitles = { "title1", "title2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            initView();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化viewpager
     */
    private void initView() throws Exception {
        mViewPager = (CustomViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.silding_tab);
        mFragmentList.add(PageFragment.newInstance(1));
        mFragmentList.add(PageFragment.newInstance(2));
        FragmentManager fm = getSupportFragmentManager();
        mAdapter = new SimpleFragmentPagerAdapter(fm, mFragmentList, mTitles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setPagingEnabled(false);
    }

    /**
     * viewpager适配器
     */
    class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        private String [] tabTitles;
        private List<Fragment> mFragmentList;
        private int count;

        public SimpleFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String [] titles) throws Exception {
            super(fm);
            this.mFragmentList = fragmentList;
            this.tabTitles = titles;
            if (fragmentList == null || tabTitles == null) throw new Exception("fragmentList not allow null");
            if (fragmentList.size() != titles.length) throw new Exception("titleList`s size not equal fragmentList`s size ");
            count = fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.size() <= position ? null : mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles.length <= position? "no title" : tabTitles[position];
        }
    }
}
