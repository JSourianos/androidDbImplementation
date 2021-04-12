package com.kristiania.lecture11_codealong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kristiania.lecture11_codealong.databinding.ActivityMainBinding
import com.kristiania.lecture11_codealong.datastorage.RaceDatabase
import com.kristiania.lecture11_codealong.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = MainViewModel(this)

        var button = binding.buttonAdd
        button.setOnClickListener {
            //Start activity to add record
            Intent(MainActivity@ this, RecordActivity::class.java).also { intent ->
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getRecords().observe(this, { list ->
            list.map { entity ->
                Log.d(this.javaClass.simpleName, entity.toString())
            }
        })
    }
}