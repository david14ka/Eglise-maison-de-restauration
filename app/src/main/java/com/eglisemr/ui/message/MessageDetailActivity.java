package com.eglisemr.ui.message;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import com.eglisemr.R;
import com.eglisemr.model.Sermon;
import com.eglisemr.ui.webveiw.WebActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import static java.security.AccessController.getContext;

public class MessageDetailActivity extends AppCompatActivity {
    private static final String TAG = MessageDetailActivity.class.getName();
    public static Sermon mSermon;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageVideo = findViewById(R.id.image);
        TextView title = findViewById(R.id.video_title);
        TextView subtitle = findViewById(R.id.video_subtitle);
        TextView author = findViewById(R.id.video_date_author);
        ImageView playButton = findViewById(R.id.play_video);

        if (mSermon == null) finish();

        title.setText(mSermon.getTitle());
        subtitle.setText(mSermon.getSubtitle());
        author.setText(String.format("%s - %s", mSermon.getDate(), mSermon.getAuthor()));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap = Picasso.get().load(mSermon.getImageUrl()).get();
                    imageVideo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtra("url", mSermon.getVideoUrl());
                startActivity(intent);
            }
        });

    }

    // Set the background and text colors of a toolbar given a
// bitmap image to match
    public void setAppbarColor(Bitmap bitmap) {
        // Generate the palette and get the vibrant swatch
        // See the createPaletteSync() method
        // from the code snippet above

        Palette p = createPaletteSync(bitmap);
        Palette.Swatch vibrantSwatch = p.getVibrantSwatch();

        appBarLayout = findViewById(R.id.app_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Load default colors
        int backgroundColor = ContextCompat.getColor(this,
                R.color.default_title_background);
        int textColor = ContextCompat.getColor(this,
                R.color.default_title_color);

        // Check that the Vibrant swatch is available
        if (vibrantSwatch != null) {
            backgroundColor = vibrantSwatch.getRgb();
            textColor = vibrantSwatch.getTitleTextColor();
        }

        // Set the toolbar background and text colors
        appBarLayout.setBackgroundColor(backgroundColor);
        toolbar.setTitleTextColor(textColor);
    }

    // Generate palette synchronously and return it
    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }
}
