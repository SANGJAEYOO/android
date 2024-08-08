package alsatang.co.kr.myunittrans

import alsatang.co.kr.myunittrans.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    var  inputNumber:Int = 0
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val edtInput = binding.edtInput
        val txtOutput = binding.txtOutput
        val txtInputUnit = binding.txtInputUnit
        val txtOutputUnit = binding.txtOutputUnit
        val btnSwitch = binding.btnSwitch


        edtInput.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()){
                0
            }
            else
            {
                text.toString().toInt()
            }
            Log.d("INPUTNUMBER", inputNumber.toString())

            var outNumber =
            if(cmToM){
                inputNumber.times(0.01)
            }else
            {
                inputNumber.times(100)
            }
            txtOutput.text = outNumber.toString()
        }

        btnSwitch.setOnClickListener {
            cmToM = cmToM.not()
            if(cmToM) {
                txtInputUnit.text = "cm"
                txtOutputUnit.text = "m"
                txtOutput.text = inputNumber.times(0.01).toString()
            }
            else
            {
                txtInputUnit.text = "m"
                txtOutputUnit.text = "Cm"
                txtOutput.text = inputNumber.times(100).toString()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM",cmToM)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle ) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.txtInputUnit.text = if(cmToM) "cm" else "m"
        binding.txtOutputUnit.text = if(cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }

}