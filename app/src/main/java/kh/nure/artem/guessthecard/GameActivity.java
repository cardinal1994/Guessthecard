package kh.nure.artem.guessthecard;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends MainActivity {

    ImageView imageView;
    String lastCard;
    String nextCard;
    int scoreCounter = 0;
    int cardCounter = 51;
    List<String> cards;
    int randomElem;
    int higherOrLower;
    int lastCardValue;
    int nextCardValue;
    TextView score;
    TextView countOfCards;
    Button btnHigher;
    Button btnEquals;
    Button btnLower;
    Button btnNewGame;
    MediaPlayer playWin;
    MediaPlayer playLose;
    private static final String TAG = "MainActivity";

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        btnHigher = findViewById(R.id.buttonHigher);
        btnEquals = findViewById(R.id.buttonEquals);
        btnLower = findViewById(R.id.buttonLower);
        btnNewGame = findViewById(R.id.newGame);
        btnNewGame.setVisibility(View.GONE);
        imageView = findViewById(R.id.imageCard);
        score = findViewById(R.id.score);
        cards = new ArrayList<>();
        create52cards(cards);
        lastCard = getRandomCard(cards);
        lastCardValue = Integer.parseInt(lastCard.substring(0, 2));
        getViewFromCards(lastCard);
        countOfCards = findViewById(R.id.TVcountOfCards);
        playWin = MediaPlayer.create(this, R.raw.win);
        playLose = MediaPlayer.create(this, R.raw.loose);
        playWin.setLooping(false);
        playLose.setLooping(false);

    }

    private void getViewFromCards(String card) {
        InputStream ims = null;
        try {
            ims = getAssets().open("cards/" + card);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // загружаем как Drawable
        Drawable d = Drawable.createFromStream(ims, null);
        // выводим картинку в ImageView
        imageView.setImageDrawable(d);
    }


    private String getRandomCard(List<String> cards) {

        randomElem = (int) (Math.random() * cards.size());
        nextCard = cards.get(randomElem);
        cards.remove(randomElem);
        return nextCard;
    }

    private void valueComparison() {


        if (nextCardValue > lastCardValue) {
            higherOrLower = 1;
        }
        if (nextCardValue == lastCardValue) {
            higherOrLower = 0;
        }
        if (nextCardValue < lastCardValue) {
            higherOrLower = -1;
        }

    }

    private void create52cards(List<String> cards) {
        for (int i = 2; i < 10; i++)
            for (int j = 0; j < 4; j++)
                switch (j) {
                    case 0:
                        cards.add("0" + i + "b.png");
                        break;

                    case 1:
                        cards.add("0" + i + "c.png");
                        break;
                    case 2:
                        cards.add("0" + i + "k.png");
                        break;
                    case 3:
                        cards.add("0" + i + "p.png");
                        break;
                }


        for (int i = 10; i < 15; i++)
            for (int j = 0; j < 4; j++)
                switch (j) {
                    case 0:
                        cards.add(i + "b.png");
                        break;

                    case 1:
                        cards.add(i + "c.png");
                        break;
                    case 2:
                        cards.add(i + "k.png");
                        break;
                    case 3:
                        cards.add(i + "p.png");
                        break;
                }
    }

    @Override
    public void HideNavigation(View v) {
        super.HideNavigation(v);
    }

    public void onClickButtonHigher(View v) {
        if (isPlaingSound) {
            mp.start();
        }
        int higher = 1;
        comparisonResult(higher);
    }

    public void onClickButtonEquals(View v) {
        if (isPlaingSound) {
            mp.start();
        }
        int equals = 0;
        comparisonResult(equals);
    }

    public void onClickButtonLower(View v) {
        if (isPlaingSound) {
            mp.start();
        }
        int lower = -1;
        comparisonResult(lower);
    }

    public void comparisonResult(int higherOrLowerValue) {
        if (cards.size() != 0) {

            nextCard = getRandomCard(cards);
            getViewFromCards(nextCard);

            nextCardValue = Integer.parseInt(nextCard.substring(0, 2));
            valueComparison();
            if (higherOrLower == higherOrLowerValue) {
                score.setText(String.valueOf(++scoreCounter));
            } else {
                score.setText(String.valueOf(--scoreCounter));
            }
            countOfCards.setText(String.valueOf(--cardCounter));
            lastCardValue = nextCardValue;

            if (cards.size() == 0) {
                if (isPlaingSound) {
                    if (scoreCounter > 0) {
                        playWin.start();
                    } else {
                        playLose.start();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                btnEquals.setVisibility(View.GONE);
                btnHigher.setVisibility(View.GONE);
                btnLower.setVisibility(View.GONE);
                btnNewGame.setVisibility(View.VISIBLE);
            }

        }
    }

    public void onClickButtonNewGame(View view) {
        if (isPlaingSound) {
            mp.start();
        }
        scoreCounter = 0;
        score.setText(String.valueOf(scoreCounter));
        cardCounter = 51;
        countOfCards.setText(String.valueOf(cardCounter));
        cards = new ArrayList<>();
        create52cards(cards);
        lastCard = getRandomCard(cards);
        lastCardValue = Integer.parseInt(lastCard.substring(0, 2));
        getViewFromCards(lastCard);
        btnNewGame.setVisibility(View.GONE);
        btnEquals.setVisibility(View.VISIBLE);
        btnHigher.setVisibility(View.VISIBLE);
        btnLower.setVisibility(View.VISIBLE);
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

}



