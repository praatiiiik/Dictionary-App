package com.example.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.dictionaryapp.data.viewModel.AppViewModel
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val model : AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.but.setOnClickListener {
            val word = binding.wordEditText.text.toString()
            if(word.isEmpty()){
                showToast("Enter Word")
            }else{
                model.getMeaning(word)
            }

        }

        model.meaning.observe(this){
            when(it){
                is Status.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Status.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    showToast("Something Went Wrong")
                }
                is Status.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.meaningTextView.text = it.data[0].meanings?.get(0)?.definitions?.get(0)?.definition.toString()
                }
            }
        }

    }

    private fun showToast(msg : String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}