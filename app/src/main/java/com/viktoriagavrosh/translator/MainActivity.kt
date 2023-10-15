package com.viktoriagavrosh.translator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.viktoriagavrosh.translator.databinding.ActivityMainBinding

const val SEARCH_PREFIX = "https://www.google.com/search?q="

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.translateButton.setOnClickListener {
            if (binding.wordEditText.text.toString() == "") {
                Toast.makeText(this, getString(R.string.no_text), Toast.LENGTH_SHORT).show()
            } else {
                goToGoogle()
                binding.wordEditText.text = null
            }
        }
    }

    // create query string
    private fun getQuery(): String {
        val word = binding.wordEditText.text.toString()
        return when (binding.translationGroup.checkedRadioButtonId) {
            R.id.translation_eng_to_rus -> getString(R.string.tranlate_to_rus, SEARCH_PREFIX, word)
            else -> getString(R.string.translate_to_eng, SEARCH_PREFIX, word)
        }
    }

    private fun goToGoogle() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getQuery()))
        startActivity(intent)
    }
}