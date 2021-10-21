package fr.mbds.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.mbds.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*private lateinit var clickButton: Button
    private lateinit var clickButton2: Button
    private lateinit var tv: TextView*/
    private lateinit var binding: ActivityMainBinding
    private var nbClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvents()
    }
    /*private fun initView() {
        clickButton = findViewById(R.id.btn_click_me)
        clickButton2 = findViewById(R.id.btn2)
        tv = findViewById(R.id.txt_v)
    }*/

    private fun initEvents() {
        binding.btn2.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }

        binding.btnClickMe.setOnClickListener {
            Toast.makeText(baseContext, "Tu m'as cliqué", Toast.LENGTH_LONG).show()
            nbClick++
            val newText = "Cliquez moi $nbClick"
            binding.btnClickMe.text = newText
            binding.txtV.text = "vous avez cliqué n $nbClick"
        }
    }
}
