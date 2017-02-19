package com.example.light;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private SensorManager sensorManager;
	private TextView light;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		light = (TextView)findViewById(R.id.light);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		sensorManager.registerListener(listener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(sensorManager != null){
			sensorManager.unregisterListener(listener);
		}
	}
	private SensorEventListener listener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			//values数组中第一个下标的值就是当前的光照值
			float value = event.values[0];
			light.setText("光照强度：" +value +"Lx");
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
