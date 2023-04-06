package alsatang.co.kr.mycounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtCount = findViewById<TextView>(R.id.txtCount)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val btnPlus = findViewById<Button>(R.id.btnPlus)

        var number = 0

        btnReset.setOnClickListener {
            number = 0;
            Log.d("onClick","Resetnum ===> $number")
            txtCount.text = number.toString();
        }

        btnPlus.setOnClickListener {
            number += 1
            Log.d("onClick","Plusnum ===> $number")
            txtCount.text = number.toString();
        }

    }
}