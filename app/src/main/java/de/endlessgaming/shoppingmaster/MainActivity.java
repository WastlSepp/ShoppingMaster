package de.endlessgaming.shoppingmaster;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import de.endlessgaming.shoppingmaster.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Tab Layout mit Fragmenten
         */

        // Viewpager zum Laden von Fragments aufsetzen
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        // Tab-Layout aufsetzen
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Listen"));
        tabs.addTab(tabs.newTab().setText("Stores"));
        tabs.addTab(tabs.newTab().setText("Tab 3"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        // Tablayout mit ViewPager verbinden
        tabs.setupWithViewPager(viewPager);
        // Listener setzen
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        // TabSelectedListener - Event für Abfrage eines Wechsel von Tabs
        tabs.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        // TODO: write code for the selected tabs here
                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });

        /*
            Floating Action Button
         */

        // FAB aus Layout abfassen
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
                //Intent intent=new Intent(^^LoginActivity.this,MainActivity.class);
                //startActivity(intent);
                Intent intent = new Intent(getApplication(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Falls User nicht eingeloggt: Redirect zum Login-Bildschirm
        // TODO: Redirect to Login if not logged on
    }

}