package com.example.lab_terminal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab_terminal.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.vBindImage.setImageResource(R.drawable._280px_flag_of_pakistan_svg)
        binding.vBindName.text = "Yahya"
        binding.vBindButton.setOnClickListener {
            Toast.makeText(this, "View Binding button has been clicked", Toast.LENGTH_LONG).show()
        }
    }
}