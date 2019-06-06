package kh.nure.artem.guessthecard;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SettingsActivity extends MainActivity {

    private TextView musicOnOff;
    private TextView soundOnOff;
    private TextView textSound;
    private TextView textMusic;
    public boolean MUSIC_ON = true;
    public boolean SOUND_ON = true;
    ImageButton imageButton;
    private static final String TAG = "MainActivity";

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        textMusic = findViewById(R.id.textMusic);
        textSound = findViewById(R.id.textSound);
        musicOnOff = findViewById(R.id.MusicOnOff);
        soundOnOff = findViewById(R.id.SoundOnOff);
        myTypeFace(musicOnOff);
        myTypeFace(soundOnOff);
        myTypeFace(textMusic);
        myTypeFace(textSound);

        imageButton = findViewById(R.id.backbutton);


    }


    @Override
    public void HideNavigation(View v) {
        super.HideNavigation(v);
    }

    public void myTypeFace(TextView v) {
        v.setTypeface(Typeface.createFromAsset(getAssets(), "Brushcrazy DEMO.otf"));
    }

    public void onClickMusic(View v) {
        MUSIC_ON = !MUSIC_ON;
        onChangeState(musicOnOff, MUSIC_ON);
        if (MUSIC_ON) {
            isPlaingMusic = true;
            startService(new Intent(this, PlayService.class));
        } else {
            isPlaingMusic = false;
            stopService(new Intent(this, PlayService.class));
        }

    }

    public void onClickSound(View v) {
        if(isPlaingSound){
            mp.start();
        }
        SOUND_ON = !SOUND_ON;
        onChangeState(soundOnOff, SOUND_ON);
        isPlaingSound = SOUND_ON;

    }

    private void onChangeState(TextView tv, boolean state) {
        if (state) {
            tv.setText("On");
            tv.setTextColor(getResources().getColor(R.color.OnGreenColor));
        } else {
            tv.setText("Off");
            tv.setTextColor(getResources().getColor(R.color.OffRedColor));
        }

    }

    public void onClickButtonBack(View view) {
        if(isPlaingSound){
            mp.start();
        }
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
