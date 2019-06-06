package kh.nure.artem.guessthecard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    public static boolean isPlaingMusic = true;
    public static boolean isPlaingSound = true;
    private TextView textStart;
    private TextView textSettings;
    private TextView textGameRules;
    private TextView textExit;
    private Intent intent;
    public MediaPlayer mp;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        textStart = findViewById(R.id.textViewStart);
        textSettings = findViewById(R.id.textViewSettings);
        textGameRules = findViewById(R.id.textViewGameRules);
        textExit = findViewById(R.id.textViewExit);
        myTypeFace(textStart);
        myTypeFace(textSettings);
        myTypeFace(textGameRules);
        myTypeFace(textExit);
        mp = MediaPlayer.create(this, R.raw.clickb5);
        if(isPlaingMusic){
            startService(new Intent(MainActivity.this, PlayService.class));
        }
        mp.setLooping(false);

    }



    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, PlayService.class));
        mAdView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdView.destroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        startService(new Intent(this, PlayService.class));
        mAdView.resume();
    }

    public void myTypeFace(TextView v) {
        v.setTypeface(Typeface.createFromAsset(getAssets(), "Brushcrazy DEMO.otf"));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }


    public void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void HideNavigation(View v) {
        onWindowFocusChanged(true);
    }

    public void onClickButtonStart(View view) {
        if(isPlaingSound){
            mp.start();
        }
        intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onClickSettings(View view) {
        if(isPlaingSound){
            mp.start();
        }
        intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onClickButtonGameRules(View view){
        if(isPlaingSound){
            mp.start();
        }
        intent = new Intent(this, GameRulesActivity.class);
        startActivity(intent);
    }

    public void onClickButtonExit(View view) {
        if(isPlaingSound){
            mp.start();
        }
        openQuitDialog();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                this, R.style.MyAlertDialogStyle);

        quitDialog.setTitle("Exit: Are you sure?");

        quitDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });

        quitDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        quitDialog.show();
    }

}
