package br.iesb.medanvisa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SearchMedicineActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView profileEmail;
    private TextView profileName;
    private ImageView profilePicture;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser user;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_medicine);

        inicializaComponentes();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Button btnSearch = (Button) findViewById(R.id.search_button);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchMedicineActivity.this, ResultMedicineActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_medicine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_logout) {
            Conexao.deslogar();
            finish();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificaUser();
    }


    private void verificaUser() {
        if (user == null) {
            finish();
        } else {
            Toast.makeText(SearchMedicineActivity.this, "Tentando preencher", Toast.LENGTH_LONG).show();
            // TODO - Apresentar Email do usuário na gaveta SearchMedicineActivity - Null Pointer Exception fuck
            //            profileEmail.setText(user.getEmail());
        }
    }

    private void inicializaComponentes() {
        profileEmail = (TextView) findViewById(R.id.nav_header_search_medicine_profile_email);
        profileName = (TextView) findViewById(R.id.nav_header_search_medicine_profile_name);
        profilePicture = (ImageView) findViewById(R.id.nav_header_search_medicine_profile_picture);
    }

//    private void buscarDados() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://mobile-aceite.tcu.gov.br/mapa-da-saude/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        TCURemedios service = retrofit.create(TCURemedios.class);
//
//        Call<List<Remedio>> remedios = service.listarRemedios();
//
//        remedios.enqueue(new Callback<List<Remedio>>() {
//            @Override
//            public void onResponse(Call<List<Remedio>> call,
//                                   Response<List<Remedio>> response) {
//                List<Remedio> lista = response.body();
//
//                for (Remedio r : lista) {
//                    Log.d("RETROFIT", r.getProduto() + " " + r.getApresentacao());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Remedio>> call,
//                                  Throwable t) {
//            }
//        });
//    }
}
