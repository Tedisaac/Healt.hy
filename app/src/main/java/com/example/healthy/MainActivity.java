package com.example.healthy;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;

        import android.app.Dialog;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.graphics.drawable.GradientDrawable;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.widget.Toolbar;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentTransaction;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.airbnb.lottie.LottieAnimationView;
        import com.github.ybq.android.spinkit.sprite.Sprite;
        import com.github.ybq.android.spinkit.style.DoubleBounce;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.android.material.navigation.NavigationView;

        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE = 0.7f;

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FloatingActionButton floatingActionButton;
    LinearLayout contentView;
    TextView gp,consultant;
    ProgressBar progressBar;

    RecyclerView infoRecycler;
    RecyclerView.Adapter adapter;

    LottieAnimationView doc,con;
    Dialog consult_dialog;
    Button agree_btn,disagree_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView =findViewById(R.id.nav_view);
        drawerLayout =findViewById(R.id.drawer_layout);
        floatingActionButton = findViewById(R.id.options);
        contentView = findViewById(R.id.content_view);
        gp = findViewById(R.id.contact_gp);
        consultant = findViewById(R.id.contact_consultant);

        converseMethod();
        navigationMethod();

        infoRecycler = findViewById(R.id.recycler_view);
        recyclerInfo();

        doc = findViewById(R.id.doctor);
        doc.playAnimation();
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doc.playAnimation();
            }
        });

        consult_dialog = new Dialog(this);





    }

    private void customDialogBox() {
        consult_dialog.setContentView(R.layout.consultant_fee_custom_dialog_box);
        consult_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        agree_btn = consult_dialog.findViewById(R.id.btn_agree);
        disagree_btn = consult_dialog.findViewById(R.id.btn_disagree);
        agree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoadScreenActivity.class));
                finish();
            }
        });
        disagree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consult_dialog.dismiss();
            }
        });
        con = consult_dialog.findViewById(R.id.con_lottie);
        con.playAnimation();
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con.playAnimation();
            }
        });
        consult_dialog.show();
    }

    private void recyclerInfo() {
        GradientDrawable gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        GradientDrawable gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        GradientDrawable gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        infoRecycler.setHasFixedSize(true);
        infoRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<infoHelper> info = new ArrayList<>();
        info.add(new infoHelper(gradient1, R.drawable.didyouknowremovebg, "Fever, cough and shortness of breath are the most common COVID-19 symptoms."));
        info.add(new infoHelper(gradient4, R.drawable.didyouknowremovebg, " COVID-19 and influenza viruses have a similar disease presentation."));
        info.add(new infoHelper(gradient2, R.drawable.didyouknowremovebg, " COVID-19 and influenza viruses have a similar disease presentation."));
        info.add(new infoHelper(gradient3, R.drawable.didyouknowremovebg, " COVID-19 and influenza viruses have a similar disease presentation."));



        adapter = new Information(info);
        infoRecycler.setAdapter(adapter);
    }

    private void converseMethod() {
        gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadScreenActivity.class));
                finish();

            }
        });
        consultant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogBox();
            }
        });
    }

    private void navigationMethod() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);

                } else {
                    drawerLayout.openDrawer(GravityCompat.START);

                }
            }
        });
        navigationanime();
    }

    private void navigationanime() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final  float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offSetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offSetScale);
                contentView.setScaleY(offSetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset/2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            case R.id.contact:
                startActivity(new Intent(MainActivity.this, ContacUsActivity.class));
                break;
            case R.id.log_out:
                startActivity(new Intent(MainActivity.this, SignIn.class));
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    public void recreatePage(){
        RecreateActivity.refreshPage(this);

    }

}