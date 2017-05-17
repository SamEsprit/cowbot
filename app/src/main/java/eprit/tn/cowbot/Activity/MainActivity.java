package eprit.tn.cowbot.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import eprit.tn.cowbot.ViewAdapter.SectionsPagerAdapter;
import eprit.tn.cowbot.R;

public class MainActivity extends AppCompatActivity {

    private SpaceNavigationView spaceNavigationView;
    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);

        spaceNavigationView.addSpaceItem(new SpaceItem(getString(R.string.cam), R.drawable.cam));
        spaceNavigationView.addSpaceItem(new SpaceItem(getString(R.string.progress), R.drawable.progress));
        spaceNavigationView.addSpaceItem(new SpaceItem(getString(R.string.weather),R.drawable.ic_filter_drama_black_24dp));
        spaceNavigationView.setCentreButtonIcon(R.drawable.settings);
       //spaceNavigationView.setSpaceBackgroundColor(getApplicationContext().getColor(R.color.colorPrimary));
        spaceNavigationView.showIconOnly();

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {

                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                mViewPager.setCurrentItem(itemIndex);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                mViewPager.setCurrentItem(itemIndex);
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                spaceNavigationView.changeCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }





}
