package com.example.charlie.interviewtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.charlie.interviewtest.Content.Content_Contract;
import com.example.charlie.interviewtest.Content.Content_Presenter;
import com.example.charlie.interviewtest.Models.Session;
import com.example.charlie.interviewtest.Models.loginRequest;
import com.example.charlie.interviewtest.Models.userdetails;
import com.example.charlie.interviewtest.Utility.GalleryAlert;

import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements Content_Contract.View {

    @Bind(R.id.tvEmail)
    TextView email;

    @Bind(R.id.tvPassword)
    TextView password;

    @Bind(R.id.ivProfileImage)
    ImageView profileImage;

    Content_Presenter mPresenter;
    private static final int REQUEST_LOGIN = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private static final int REQUEST_IMAGE_FROM_GALLERY = 3;
    Realm realm;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new Content_Presenter(this);

    if (profileImage!=null)
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userid != null)
                {
                    GalleryAlert GDialog = new GalleryAlert();
                    GDialog.show(getFragmentManager(), "getPhoto");


                }
            }
        });
        realm = Realm.getDefaultInstance();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_LOGIN){
            final Session session = realm.where(Session.class).findFirst();
            mPresenter.returnUser(session.getUserid());
        }

        if ((requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.i("IntTest", "IMAGE DATA "+imageBitmap.getByteCount());
            profileImage.setImageBitmap(imageBitmap);
           // Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),  R.drawable.logo);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
            byte [] ba = bao.toByteArray();
            String ba1= Base64.encodeToString(ba,Base64.DEFAULT);
            mPresenter.returnAvatar(ba1,userid);
        }

        if ((requestCode == REQUEST_IMAGE_FROM_GALLERY && resultCode == RESULT_OK)) {
            Uri selectedImage = data.getData();
            profileImage.setImageURI(selectedImage);

            try
            {
                Bitmap bitmapOrg = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                byte [] ba = bao.toByteArray();
                String ba1= Base64.encodeToString(ba,Base64.DEFAULT);
                mPresenter.returnAvatar(ba1,userid);
            }
            catch (Exception e){}


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void setPresenter(Content_Contract.Presenter presenter) {

    }

    @Override
    public void startSession(Session session) {

    }

    @Override
    public void receiveUser(userdetails user) {
        if (user!=null)
        {
            //profileImage.setImageBitmap();
            if (user.getAvatarurl() != null) {
                Glide.with(this)
                        .load(user.getAvatarurl())
                        .into(profileImage);
            }
            //Set Email
            email.setText(user.getEmail());

            final loginRequest login = realm.where(loginRequest.class).contains("email", user.getEmail()).findFirst();
            if (login!=null)
            password.setText(login.getPassword());

            final Session session = realm.where(Session.class).findFirst();
            userid = session.getUserid();

        }
    }

    @Override
    public void receiveAvatar(String avatarUrl) {
        if (avatarUrl != null)
        Glide.with(this)
                .load(avatarUrl)
                .into(profileImage);
    }
}
