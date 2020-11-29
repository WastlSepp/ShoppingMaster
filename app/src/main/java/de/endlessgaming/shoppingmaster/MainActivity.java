package de.endlessgaming.shoppingmaster;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import de.endlessgaming.shoppingmaster.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    // TODO: remove in PV (= Productive Version) - jft (= just for testing)
    private static final String TAG = "xx MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

                Intent intent = new Intent(getApplication(),LoginActivity.class);
                startActivity(intent);

                // TODO: Grafik zu Plus-Zeichen abändern,
                //  bei Klick auf "+" während man im ListView ist: neue Liste anlegen,
                //  bei Klick auf "+" während man im StoreView ist: neuen Store anlegen,
                //  FAB in Tab3 ausblenden
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Falls User nicht eingeloggt: Redirect zum Login-Bildschirm
        // TODO: Redirect to Login if not logged on
    }

    /**
     *  ActionMenu am rechten oberen Bildschirmrand - für Einstellungen und Testaufrufe
     */

    /* Erzeugen des Menus aus der XML-Datei heraus */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /* Reaktion auf Clicks auf die Menu-Einträge */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //TODO: Clean up!
        switch(item.getItemId()) {
            case R.id.mainMenuManageAccount:
                Log.d(TAG, "Manage Account");
                //Intent intent = new Intent(getApplication(),ManageAccountActivity.class);
                //startActivity(intent);
                return true;
            case R.id.mainMenuTestAuthentication:
                Log.d(TAG, "Test Authentication");
                // Methodenaufruf
                return true;
            case R.id.mainMenuSignIn:
                Log.d(TAG, "Sign In");
                // Methodenaufruf
                return true;
            case R.id.mainMenuSignOut:
                Log.d(TAG, "Sign Out");
                // Methodenaufruf
                return true;
            case R.id.mainMenuDeleteUser:
                Log.d(TAG, "Delete User");
                // Methodenaufruf
                return true;
            case R.id.mainMenuSetDisplayName:
                Log.d(TAG, "Set Display Name");
                // Methodenaufruf
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *  TODO: Clean up!
     */

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

}