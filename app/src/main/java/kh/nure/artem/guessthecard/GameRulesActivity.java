package kh.nure.artem.guessthecard;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class GameRulesActivity extends MainActivity {
    private TextView textRules;
    private static final String TAG = "MainActivity";

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        textRules = findViewById(R.id.textRules);
        myTypeFace(textRules);

    }

    public void onClickButtonBack(View view) {
        if (isPlaingSound) {
            mp.start();
        }
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void HideNavigation(View v) {
        super.HideNavigation(v);
    }
}


