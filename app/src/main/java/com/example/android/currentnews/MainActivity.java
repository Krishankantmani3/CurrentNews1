package com.example.android.currentnews;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.android.currentnews.ui.main.SectionsPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
     DrawerLayout drawerLayout;
     ActionBarDrawerToggle toggle;
     NavigationView navigationView;
     Toolbar toolbar;ViewPager viewPager;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HeadlinesFragment(), "Headlines");
        adapter.addFragment(new BusinessFragment(), "Business");
        adapter.addFragment(new HealthFragment(), "Health");
        adapter.addFragment(new SportsFragment(), "Sports");
        adapter.addFragment(new EntertainmentFragment(), "Entertainment");
        adapter.addFragment(new ScienceFragment(), "Science");
        adapter.addFragment(new TechnologyFragment(), "Technology");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.headlines:
            case R.id.home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.business:
                viewPager.setCurrentItem(1);
                break;
            case R.id.health:
                viewPager.setCurrentItem(2);
                break;
            case R.id.sports:
                viewPager.setCurrentItem(3);
                break;
            case R.id.entertainment:
                viewPager.setCurrentItem(4);
                break;
            case R.id.science:
                viewPager.setCurrentItem(5);
                break;
            case R.id.technology:
                viewPager.setCurrentItem(6);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}