package com.kristiania.lecture11_codealong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.kristiania.lecture11_codealong.databinding.ActivityMainBinding
import com.kristiania.lecture11_codealong.databinding.ActivityRecordBinding
import com.kristiania.lecture11_codealong.datastorage.RaceDatabase
import com.kristiania.lecture11_codealong.datastorage.RecordEntity
import com.kristiania.lecture11_codealong.viewmodel.RecordViewModel


class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    private lateinit var viewModel: RecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordBinding.inflate(layoutInflater)
        viewModel = RecordViewModel(this)

        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {

            //Add user input into sqlite DB
            var entity = RecordEntity(
                binding.editName.text.toString(),
                binding.editDistance.text.toString().toInt(),
                binding.editTime.text.toString().toInt()
            )

            //Accessing DB directly from UI thread is bad - dont do this on the exam
            //Use coroutines and Viewmodcel

            //Add to db
            viewModel.addRaceRecord(entity)

            finish() // activity method that will finish our activity (go to previous activity)
        }

        binding.editName.addTextChangedListener(textWatcher)
        binding.editDistance.addTextChangedListener(textWatcher)
        binding.editTime.addTextChangedListener(textWatcher)

    }

    var textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            binding.buttonSave.isEnabled = binding.editName.text.isNotEmpty()
                    && binding.editDistance.text.isNotEmpty()
                    && binding.editTime.text.isNotEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }


    }
}