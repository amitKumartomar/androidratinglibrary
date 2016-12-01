package smartapplab.com.ratinglibrary;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import smartapplab.com.ratinglibrary.extra.Constant;


public class RatingDialog implements  OnClickListener{

    private static final String TITLE = "Rate Us!!";
    private static final String TEXT = "If you Like this app,Give Us 5 star Rating!!,Thanks!!";
    private static final String POSITIVE = "Ok";
    private static final String NEGATIVE = "Not Now";
    private static final String NEUTRAL = "Never";

    private SharedPreferences sharedPrefs;

    private RatingBar ratingBar;
    private String ratingtitle = null;
    private String ratingtext = null;
    private AlertDialog dialog;
    private View ratingview;
    private Context context;
    private Boolean showstar=true;

    private ReviewFeedbackListener reviewfeedbacklistener;

    public RatingDialog(Context _context)
    {
        this.context=_context;
        this.sharedPrefs = context.getSharedPreferences(Constant.PREFS_NAME, 0);
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        if(i == -1) {
            this.openplayMarket();
            this.disablerating();
            if(RatingDialog.this.reviewfeedbacklistener != null) {
                RatingDialog.this.reviewfeedbacklistener.onReview((int)ratingBar.getRating());
            }
        }

        if(i == -3) {
            this.disablerating();
        }

        if(i == -2) {
            SharedPreferences.Editor editor = this.sharedPrefs.edit();
            editor.putInt("numberCounter", 0);
            editor.apply();
        }

        this.dialog.hide();

    }


    public void setShowAfter(int _numberCounter) {
        boolean disable = this.sharedPrefs.getBoolean("disable", false);
        SharedPreferences.Editor editor = this.sharedPrefs.edit();
        int numberCounter = this.sharedPrefs.getInt("numberCounter", 0);
        editor.putInt("numberCounter", numberCounter + 1);
        editor.apply();
        if(numberCounter + 1 >= _numberCounter) {
            if(disable==false) {
                this.buildRating();
                this.dialog.show();
            }
        }

    }

    private void buildRating()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        this.ratingview = inflater.inflate(R.layout.rating_dialog,(ViewGroup)null);


        String title = this.ratingtitle == null?TITLE:this.ratingtitle;
        String text = this.ratingtext == null?TEXT:this.ratingtext;
        ((TextView)this.ratingview.findViewById(R.id.textView)).setText(text);
        this.ratingBar = (RatingBar)this.ratingview.findViewById(R.id.ratingBar);
        if(showstar) {
            this.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    RatingDialog.this.openplayMarket();
                    RatingDialog.this.dialog.hide();
                    if (RatingDialog.this.reviewfeedbacklistener != null) {
                        RatingDialog.this.reviewfeedbacklistener.onReview((int) ratingBar.getRating());
                    }

                }

            });
        }
        else
            this.ratingBar.setVisibility(View.GONE);

        builder.setTitle(title);
        builder.setView(this.ratingview);
        builder.setNegativeButton(NEGATIVE, this);
        builder.setPositiveButton(POSITIVE, this);
        builder.setNeutralButton(NEUTRAL, this);
        this.dialog=builder.create();

    }
    private void disablerating() {
        SharedPreferences shared = this.context.getSharedPreferences(Constant.PREFS_NAME, 0);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("disable", true);
        editor.apply();
    }

    private void openplayMarket()
    {
        String packageName = this.context.getPackageName();

        try {
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
        } catch (ActivityNotFoundException ex) {
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }

    public void setratingTitle(String title) {
        this.ratingtitle = title;
    }
    public void setratingText(String rateText) {
        this.ratingtext = rateText;

    }
    public void setReviewFeedbackListener(ReviewFeedbackListener listener) {
        this.reviewfeedbacklistener = listener;
    }

    public void setShowStar(Boolean _showstar) {
        this.showstar =_showstar;

    }


}

