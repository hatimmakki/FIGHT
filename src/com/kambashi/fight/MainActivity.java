package com.kambashi.fight;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView status1;
	private TextView status2;
	private ProgressBar mProgressBar;
	private int mProgressStatus = 0;

	// default player status is 0
	// 1 is for block
	// 2 is for reload
	// 3 is for fire
	private int player1 = 0;
	private int player2 = 0;
	
	private int timesHit1 = 0;
	private int timesHit2 = 0;

	// max of 6 bullets
	private int bullets1 = 0;
	private int bullets2 = 0;
	
	ImageView b26;
	ImageView b25;
	ImageView b24;
	ImageView b23;
	ImageView b22;
	ImageView b21;

	ImageView b16;
	ImageView b15;
	ImageView b14;
	ImageView b13;
	ImageView b12;
	ImageView b11;

	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		status1 = (TextView) findViewById(R.id.status1);
		status2 = (TextView) findViewById(R.id.status2);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		b26 = (ImageView) findViewById(R.id.b26);
		b25 = (ImageView) findViewById(R.id.b25);
		b24 = (ImageView) findViewById(R.id.b24);
		b23 = (ImageView) findViewById(R.id.b23);
		b22 = (ImageView) findViewById(R.id.b22);
		b21 = (ImageView) findViewById(R.id.b21);

		b16 = (ImageView) findViewById(R.id.b16);
		b15 = (ImageView) findViewById(R.id.b15);
		b14 = (ImageView) findViewById(R.id.b14);
		b13 = (ImageView) findViewById(R.id.b13);
		b12 = (ImageView) findViewById(R.id.b12);
		b11 = (ImageView) findViewById(R.id.b11);

		// Start long running operation in a background thread
		new Thread(new Runnable() {
			public void run() {
				while (mProgressStatus < 100) {
					mProgressStatus++;
					// Update the progress bar and display the
					// current value in the text view
					mHandler.post(new Runnable() {
						public void run() {
							mProgressBar.setProgress(mProgressStatus);
							if (mProgressStatus == 100) {
								endGame();
							}
						}
					});
					try {
						// Sleep for 100 milliseconds.
						// Just to display the progress slowly
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fire1:
			player1 = 3;
			break;
		case R.id.reload1:
			player1 = 2;
			if(bullets1 < 6){
				bullets1++;
			}
			break;
		case R.id.block1:
			player1 = 1;
			break;
		case R.id.fire2:
			player2 = 3;
			break;
		case R.id.reload2:
			player2 = 2;
			if(bullets2 < 6){
				bullets2++;
			}
			break;
		case R.id.block2:
			player2 = 1;
			break;
		}
		fight();
		update();
	}

	public void fight() {
		if((player1 == 3 && player2 == 2 || player1 == 3 && player2 == 0) && bullets1 > 0){
			timesHit2++;
			bullets1--;
		}else if((player1 == 2 && player2 == 3 || player1 == 0 && player2 == 3) && bullets2 > 0){
			timesHit1++;
			bullets2--;
		}else if(player1 == 3 && player2 == 3){
			if(bullets1 > 0 && bullets2 > 0){
				timesHit1++;
				timesHit2++;
				bullets1--;
				bullets2--;
			}else if(bullets1 > 0){
				timesHit2++;
				bullets1--;
			}else if(bullets2 > 0){
				timesHit1++;
				bullets2--;
			}
		}
		player1 = 0;
		player2 = 0;
	}

	public void update() {
		clearBullets();
		switch(bullets1){
		case 6:
			b16.setVisibility(View.VISIBLE);
		case 5:
			b15.setVisibility(View.VISIBLE);
		case 4:
			b14.setVisibility(View.VISIBLE);
		case 3:
			b13.setVisibility(View.VISIBLE);
		case 2:
			b12.setVisibility(View.VISIBLE);
		case 1:
			b11.setVisibility(View.VISIBLE);
		}
		
		switch(bullets2){
		case 6:
			b26.setVisibility(View.VISIBLE);
		case 5:
			b25.setVisibility(View.VISIBLE);
		case 4:
			b24.setVisibility(View.VISIBLE);
		case 3:
			b23.setVisibility(View.VISIBLE);
		case 2:
			b22.setVisibility(View.VISIBLE);
		case 1:
			b21.setVisibility(View.VISIBLE);
		}
	}
	
	public void clearBullets(){
		b16.setVisibility(View.INVISIBLE);
		b15.setVisibility(View.INVISIBLE);
		b14.setVisibility(View.INVISIBLE);
		b13.setVisibility(View.INVISIBLE);
		b12.setVisibility(View.INVISIBLE);
		b11.setVisibility(View.INVISIBLE);
		b26.setVisibility(View.INVISIBLE);
		b25.setVisibility(View.INVISIBLE);
		b24.setVisibility(View.INVISIBLE);
		b23.setVisibility(View.INVISIBLE);
		b22.setVisibility(View.INVISIBLE);
		b21.setVisibility(View.INVISIBLE);
	}

	public void endGame() {
		status1.setText("GAME OVER.\nYou were shot: " + timesHit1 + " time(s)\nOpponent was shot: " + timesHit2 + " time(s)");
		status2.setText("GAME OVER.\nYou were shot: " + timesHit2 + " time(s)\nOpponent was shot: " + timesHit1 + " time(s)");
		Button button = (Button) findViewById(R.id.fire1);
		button.setVisibility(View.GONE);
		button = (Button) findViewById(R.id.reload1);
		button.setVisibility(View.GONE);
		button = (Button) findViewById(R.id.block1);
		button.setVisibility(View.GONE);
		button = (Button) findViewById(R.id.fire2);
		button.setVisibility(View.GONE);
		button = (Button) findViewById(R.id.reload2);
		button.setVisibility(View.GONE);
		button = (Button) findViewById(R.id.block2);
		button.setVisibility(View.GONE);
		clearBullets();
	}
}
