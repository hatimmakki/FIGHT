package com.kambashi.firstandroidgame;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView status1;
	private TextView status2;
	private ProgressBar mProgressBar;
	private int mProgressStatus = 0;
	
	private int player1 = 0;
	private int player2 = 0;

	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		status1 = (TextView) findViewById(R.id.status1);
		status2 = (TextView) findViewById(R.id.status2);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

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
							if (mProgressStatus == 100){
								endGame();
							}
						}
					});
					try {
						// Sleep for 200 milliseconds.
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

	public void fire1(View view) {
		
	}

	public void reload1(View view) {

	}

	public void block1(View view) {

	}

	public void fire2(View view) {

	}

	public void reload2(View view) {

	}

	public void block2(View view) {

	}
	
	public void endGame(){
		status1.setText("GAME OVER.");
		status2.setText("GAME OVER.");
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
	}
}
