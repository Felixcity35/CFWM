package com.example.felixcity.cfwm;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

   private  WebView mywebView;
   private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        mywebView = findViewById(R.id.webview);


        if (!isConnected())
        {

           notConnected();

        }

        else
        {

           Connected();

        }

    }


    private Boolean isConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return  networkInfo != null && networkInfo.isConnected();
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void Connected()
    {

        progressDialog.setMessage("please wait...");
        progressDialog.show();

        WebSettings webSettings = mywebView.getSettings();
        mywebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setJavaScriptEnabled(true);
        mywebView.loadUrl("http://cfwm.org.ng");
        mywebView.setWebViewClient(new WebViewClient());


    }

    private void notConnected()
    {
        progressDialog.dismiss();

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Internet Connection Alert")
                .setMessage("Please check Your Internet Connection")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();

    }

    @Override
    public void onBackPressed() {
        if(mywebView.canGoBack())
        {
           mywebView.goBack();
        }
        else
         super.onBackPressed();
   }
}
