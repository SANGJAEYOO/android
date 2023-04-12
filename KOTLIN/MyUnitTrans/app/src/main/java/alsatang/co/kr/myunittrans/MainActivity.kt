package alsatang.co.kr.myunittrans

import alsatang.co.kr.myunittrans.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

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

        var  inputNumber:Int = 0
        var cmToM = true

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
}