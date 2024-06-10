package com.bank.bankregistrationtask

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.bank.bankregistrationtask.databinding.ActivityMainBinding
import com.bank.bankregistrationtask.viewmodels.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val mainViewModel: MainViewModel by viewModels()
    var ispanValid = false
    var isDateValid = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding!!.btnNext.isEnabled = false

        binding!!.edPan.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do Nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    if (!mainViewModel.validatePanNumber(s.toString())) {
                        binding!!.edPan.setTextColor(getResources().getColor(R.color.red))
                        ispanValid = false
                        binding!!.btnNext.isEnabled =( ispanValid && isDateValid)

                    }
                    else {
                        ispanValid = true
                        (ispanValid && isDateValid).also { binding!!.btnNext.isEnabled = it }
                        binding!!.edPan.setTextColor(getResources().getColor(R.color.black))
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

                // Do Nothing
            }
        })

        binding!!.edYear.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do Nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var year = s.toString()
               dateChange(year)

            }

            override fun afterTextChanged(s: Editable?) {
                // Do Nothing
            }
        })



        binding!!.txNoPan.setOnClickListener {
            noPanFinishActivity()
        }

        binding!!.btnNext.setOnClickListener {
            Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_LONG).show()
            this.finish()
        }
    }

    private fun dateChange(year:String) {
        var day = binding!!.edDay.text.toString()
        var month = binding!!.edMonth.text.toString()
        if( mainViewModel.isDateValid(day, month, year)){
            changeColor(getResources().getColor(R.color.black))

            isDateValid = true
            binding!!.btnNext.isEnabled =( ispanValid && isDateValid)
        }
        else{
            changeColor(getResources().getColor(R.color.red))
            isDateValid = false
            binding!!.btnNext.isEnabled =( ispanValid && isDateValid)
        }
    }


    private fun changeColor(code:Int){
        binding!!.edMonth.setTextColor(code)
        binding!!.edYear.setTextColor(code)
        binding!!.edDay.setTextColor(code)
    }



    private fun noPanFinishActivity() {
        this.finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}