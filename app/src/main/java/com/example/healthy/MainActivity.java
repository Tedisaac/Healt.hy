package com.example.healthy;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;

        import android.app.Dialog;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.graphics.drawable.GradientDrawable;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Handler;
        import android.provider.MediaStore;
        import android.view.LayoutInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
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
        import com.bumptech.glide.Glide;
        import com.github.ybq.android.spinkit.sprite.Sprite;
        import com.github.ybq.android.spinkit.style.DoubleBounce;
        import com.google.android.gms.auth.api.signin.GoogleSignIn;
        import com.google.android.gms.auth.api.signin.GoogleSignInClient;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.gms.tasks.Task;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.android.material.navigation.NavigationView;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.auth.UserProfileChangeRequest;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.StorageReference;
        import com.google.firebase.storage.UploadTask;

        import java.io.IOException;
        import java.util.ArrayList;

        import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE = 0.7f;
    private static final int PICK_CODE = 100;

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FloatingActionButton floatingActionButton;
    LinearLayout contentView;
    TextView gp,consultant,profileName,factTV;
    ImageView userImage;
    Uri uriProfileImage;
    String imageUrl;
    String user_name;
    String fact;
    ProgressBar progressBar;

   /* RecyclerView infoRecycler;
    Information adapter;
    ArrayList<infoHelper> info;*/

    LottieAnimationView doc,con;
    Dialog consult_dialog;
    Button agree_btn,disagree_btn;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://healthy-9225b.appspot.com");
    String userId;
    GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userId = firebaseUser.getUid();
        reference = database.getReference("Users");
        logOutUser();

        navigationView =findViewById(R.id.nav_view);
         View headerView = navigationView.getHeaderView(0);
        profileName = headerView.findViewById(R.id.profile_name);//add profile name here
        userImage = headerView.findViewById(R.id.user_image_view);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageSelector();
            }
        });
         if (firebaseUser != null){
             if (firebaseUser.getDisplayName() != null && firebaseUser.getPhotoUrl() != null){
                 profileName.setText(firebaseUser.getDisplayName());
                 Glide.with(MainActivity.this)
                         .load(firebaseUser.getPhotoUrl())
                         .into(userImage);
             }else
             {
                 reference.child("Patients").child(userId).addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                         if (snapshot.exists()) {
                             user_name = snapshot.child("Username").getValue(String.class);
                             Toast.makeText(MainActivity.this, user_name, Toast.LENGTH_LONG).show();
                             //profileName.setText(user_name);
                             UserProfileChangeRequest uname =  new UserProfileChangeRequest
                                     .Builder()
                                     .setDisplayName(user_name)
                                     .build();
                             firebaseUser.updateProfile(uname)
                                     .addOnCompleteListener(new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull Task<Void> task) {
                                             if(task.isSuccessful()){
                                                 //Toast.makeText(MainActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                                                 profileName.setText(firebaseUser.getDisplayName());
                                             } else{
                                                 Toast.makeText(MainActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                                             }

                                         }
                                     });


                         } else {
                             Toast.makeText(MainActivity.this, "no Snapshot", Toast.LENGTH_LONG).show();
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });

             }
        }



        drawerLayout =findViewById(R.id.drawer_layout);

        floatingActionButton = findViewById(R.id.options);

        contentView = findViewById(R.id.content_view);

        gp = findViewById(R.id.contact_gp);
        consultant = findViewById(R.id.contact_consultant);
        factTV = findViewById(R.id.facts_title);
        showFact();

        //Conversation Method
        converseMethod();

        //NavigationDrawer Method
        navigationMethod();

        //Did you know
       /* infoRecycler = findViewById(R.id.recycler_view);
        recyclerInfo();*/

        //Dialog Box
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

    private void showFact() {
        reference = database.getReference("Facts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    fact = snapshot.child("Message").getValue(String.class);
                    factTV.setText(fact);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void imageSelector() {
        Intent intent = new Intent();
        intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent,"Choose Profile Picture"),PICK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            uriProfileImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                userImage.setImageBitmap(bitmap);
                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {

        if (uriProfileImage != null){
            StorageReference childRef = storageRef.child("Image"+System.currentTimeMillis() + ".jpg");
            UploadTask uploadTask = childRef.putFile(uriProfileImage);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                   // Toast.makeText(MainActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                    imageUrl = taskSnapshot.getStorage().getDownloadUrl().toString();

                    if (firebaseUser != null){


                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(MainActivity.this, "Select an image", Toast.LENGTH_SHORT).show();
        }
    }

    private void logOutUser() {
        googleSignInClient = GoogleSignIn.getClient(MainActivity.this, GoogleSignInOptions.DEFAULT_SIGN_IN);
    }


    private void customDialogBox() {
        consult_dialog.setContentView(R.layout.consultant_fee_custom_dialog_box);
        consult_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        agree_btn = consult_dialog.findViewById(R.id.btn_agree);
        disagree_btn = consult_dialog.findViewById(R.id.btn_disagree);
        agree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PaymentActivity.class));
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
/*

    private void recyclerInfo() {
       */
/* GradientDrawable gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff00838f, 0xff00838f});
        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff00838f, 0xff00838f});
        GradientDrawable gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff00838f, 0xff00838f});
        GradientDrawable gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff00838f, 0xff00838f});*//*


         */
/* info.add(new infoHelper(gradient1, R.drawable.didyouknowremovebg, "Hey"));
        info.add(new infoHelper(gradient4, R.drawable.didyouknowremovebg, " COVID-19 and influenza viruses have a similar disease presentation."));
        info.add(new infoHelper(gradient2, R.drawable.didyouknowremovebg, " COVID-19 and influenza viruses have a similar disease presentation."));
        info.add(new infoHelper(gradient3, R.drawable.didyouknowremovebg, " COVID-19 and influenza viruses have a similar disease presentation."));*//*

        //adapter = new Information(info);

        infoRecycler.setHasFixedSize(true);
        infoRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        info = new ArrayList<>();
        adapter = new Information(this,info);
        infoRecycler.setAdapter(adapter);
        getFactInfo();
    }

    private void getFactInfo() {
        reference = database.getReference("Facts");
        info.clear();
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot snapshot1 : snapshot.getChildren()){
                   infoHelper facts = snapshot1.getValue(infoHelper.class);
                   info.add(facts);
                    Toast.makeText(MainActivity.this, (CharSequence) facts, Toast.LENGTH_SHORT).show();
               }
               adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
*/

    private void converseMethod() {
        gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConversationActivity.class));
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
                finish();
                break;
            case R.id.settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                finish();
                break;
            case R.id.contact:
                startActivity(new Intent(MainActivity.this, ContacUsActivity.class));
                finish();
                break;
            case R.id.log_out:
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            firebaseAuth.signOut();
                            Toast.makeText(getApplicationContext(), "LogOut Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "LogOut Failed", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    public void recreatePage(){
        RecreateActivity.refreshPage(this);

    }

}