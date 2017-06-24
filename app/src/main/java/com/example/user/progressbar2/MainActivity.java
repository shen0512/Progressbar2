package com.example.user.progressbar2;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar pBar;
    TextView textView;
    private static class StaticHandler extends Handler{
        private  WeakReference<MainActivity> mActivity=null;

        public StaticHandler(MainActivity activity){
            mActivity = new WeakReference<MainActivity>(activity);
        }
    }

    public final StaticHandler mHandler = new StaticHandler(this);
    //Handler mHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pBar = (ProgressBar)findViewById(R.id.progressBar);
        textView=(TextView)findViewById(R.id.textView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int count=((int)(Math.random()*10));

                while(true){
                    final int assign=count;

                    if(assign>=100){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                pBar.setProgress(100);
                                textView.setText("100%");
                            }
                        });
                        break;
                    }else {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                pBar.setProgress(assign);
                                textView.setText(assign + "%");
                            }
                        });
                    }

                    if(assign+20>=100) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                pBar.setSecondaryProgress(100);
                            }
                        });
                    }else{
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                pBar.setSecondaryProgress(assign+((int)(Math.random()*15)));
                            }
                        });
                    }

                    count+=((int)(Math.random()*10));
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){e.printStackTrace();}
                }
            }
        }).start();
    }
}

