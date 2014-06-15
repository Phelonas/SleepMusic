package com.example.sleepmusic;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

	private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();
	
    
    // MOIN MOIN 
    
    //NOCH MEHR
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.avicii);
		
		TextView track = (TextView)findViewById(R.id.textView1);
		track.setText("billy");
		
		Button play = (Button)findViewById(R.id.button1);
		Button pause = (Button)findViewById(R.id.button4);
		Button reset = (Button)findViewById(R.id.button2);
		TextView lichtWert = (TextView) findViewById(R.id.textView2);
		mProgress = (ProgressBar) findViewById(R.id.progressBar1);
		
		new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = doWork();

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                }
            }

			private int doWork() {
				// TODO Auto-generated method stub
				return (mediaPlayer.getCurrentPosition()/1000);
			}
        }).start();
		
		
		
	
	
		SensorManager manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

		Sensor sensorLight = manager.getDefaultSensor(Sensor.TYPE_LIGHT);

		SensorEventListener listener = new SensorEventListener(){
			
		
			
			
				@Override
				public void onAccuracyChanged(Sensor sensor,int accuracy) {}
							
				@Override
				public void onSensorChanged(SensorEvent event) {
					float wert1 = (event.values[0]/10000);
					
					
				
					if (wert1 ==0.0f && mediaPlayer.isPlaying()==true){
						mediaPlayer.pause();
					}
					else if (wert1>0.04f){
						
					mediaPlayer.start();
					mediaPlayer.setVolume(wert1, wert1);
					}
				}
				
			
			};
			
		
			
		String s = Float.toString(sensorLight.getMaximumRange());
		
		lichtWert.setText(s);
		
		
		
		
		play.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				mediaPlayer.start();
				
			}
		});
		
		pause.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				mediaPlayer.pause();
				
			}
		});
		
		reset.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				mediaPlayer.seekTo(00);
				
				
			}
		});
		
		manager.registerListener(listener, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}





}



//int letzeLichtstaerke =0;
//boolean tag = true;
//
//while (tag = true){
//	if (aktuelleLichtstaerke != letzeLichtstaerke){
//		float neuLinks, neuRechts = aktuelleLichtstaerke / 10;
//		mediaPlayer.setVolume(neuLinks, neuRechts);
//		letzeLichtstaerke = aktuelleLichtstaerke;
//	}
//	if else (aktuelleLichtstaerke == 0){
//		tag = false;
//	}
//}



