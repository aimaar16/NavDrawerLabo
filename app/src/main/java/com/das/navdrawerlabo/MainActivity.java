package com.das.navdrawerlabo;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 2. Primero declaramos las vistas para poder usarlas abajo
        final DrawerLayout elmenudesplegable = findViewById(R.id.drawer_layout);
        NavigationView elnavigation = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.labarra);

        // 3. Configuramos la Toolbar como la ActionBar de la actividad
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 4. Ahora sí, creamos el toggle usando las variables ya declaradas
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                elmenudesplegable,
                toolbar,
                android.R.string.ok,
                android.R.string.cancel);

        elmenudesplegable.addDrawerListener(toggle);
        toggle.syncState(); // Esto dibuja las 3 rayitas (hamburguesa)

        elnavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.microfono) {
                    // código micro
                } else if (id == R.id.localizacion) {
                    // código loc
                }
                elmenudesplegable.closeDrawers();
                return true; // Cambiado a true para que se vea la selección
            }
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                DrawerLayout elmenudesplegable = findViewById(R.id.drawer_layout);
                if (elmenudesplegable.isDrawerOpen(GravityCompat.START)) {
                    elmenudesplegable.closeDrawer(GravityCompat.START);
                }
                else {
                    finish();
                }
            }
        });

    }@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout elmenudesplegable = findViewById(R.id.drawer_layout);
        switch(item.getItemId()) {
            case android.R.id.home: // Este es el ID del icono de la Toolbar
                elmenudesplegable.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}