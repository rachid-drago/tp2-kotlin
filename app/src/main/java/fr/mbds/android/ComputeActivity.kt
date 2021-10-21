package fr.mbds.android

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import fr.mbds.android.databinding.ComputeActivityBinding

class ComputeActivity : AppCompatActivity() {
    /*private lateinit var clickButton: Button
    private lateinit var tv: TextView
    private lateinit var et1: EditText
    private lateinit var et2: EditText*/
    private lateinit var binding: ComputeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)
        binding = ComputeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculer.isEnabled = false

        setInput()
        initEvents()
    }

  /*  private fun initView() {
        clickButton = findViewById(R.id.btn_calculer)
        tv = findViewById(R.id.resultat)
        et1 = findViewById(R.id.field_1)
        et2 = findViewById(R.id.field_2)
    }*/

    private fun initEvents() {
        binding.btnCalculer.setOnClickListener {

            val sum: Int = binding.field1.text.toString().toInt() + binding.field2.text.toString().toInt()
            binding.resultat.text = "${binding.field1.text} + ${binding.field2.text} = $sum"
        }
    }

    private fun setInput() {
        binding.field1.inputType = InputType.TYPE_CLASS_NUMBER
        binding.field2.inputType = InputType.TYPE_CLASS_NUMBER

        binding.field1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.btnCalculer.isEnabled = binding.field1.text.isNotBlank() && binding.field2.text.isNotBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.field2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.btnCalculer.isEnabled = binding.field1.text.isNotBlank() && binding.field2.text.isNotBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}
