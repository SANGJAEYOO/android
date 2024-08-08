package alsatang.co.kr.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {

    private  val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    // tileView는 전역변수이지만 onCreate 에서 초기화 되므로 lateinit 처리 한다.
    private lateinit var tiltView: TileView

    override fun onCreate(savedInstanceState: Bundle?) {

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);   // 화면 안 꺼지게 한다.

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  // 가로모드 고정
        super.onCreate(savedInstanceState)

        tiltView = TileView(this)
        setContentView(tiltView)
    }

    // 센서값 변경 시 호출
    override fun onSensorChanged(event: SensorEvent) {

        // value[0]  X축
        // value[y]  y축

        event?.let {
            Log.d("MainActivity","onSensorChanged :  x : " +
                     "${event.values[0]},  y : ${event.values[1]},  z : ${event.values[2]}")
        }

        tiltView.onSensorEvent(event)
    }

    // 센서 정볼도 변경 시 호출
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    // 센서등록
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
}