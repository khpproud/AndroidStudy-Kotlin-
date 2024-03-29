package com.patrick.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {
    private val TAG = MainActivity::class.java.simpleName
    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    private lateinit var tiltView: TiltView


    override fun onCreate(savedInstanceState: Bundle?) {
        // 화면을 꺼지지 않게 함
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // 화면을 가로 모드로 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)

        tiltView = TiltView(this)
        setContentView(tiltView)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    // 센서 값이 변경되면 호출
    override fun onSensorChanged(event: SensorEvent?) {
        //values[0] : x축 값 : 위로 기울이면 -10 ~ 0, 아래로 기울이면 0 ~ 10
        //values[1] : y축 값 : 왼쪽으로 기울이면 -10 ~ 0, 오른쪽으로 기울이면 0 ~ 10
        //values[2] : z축 값 : 미사용
        event?.let {
            Log.d(TAG, "onSensorChanged: x : " +
                "${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")
        }

        event?.run {
            tiltView.onSensorEvent(event)
        }

    }

}
