package ru.sergeyivahnenko.lab88

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: LengthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextMeters = findViewById<EditText>(R.id.editTextMeters)
        val editTextKilometers = findViewById<EditText>(R.id.editTextKilometers)
        val editTextMiles = findViewById<EditText>(R.id.editTextMiles)
        val editTextFeet = findViewById<EditText>(R.id.editTextFeet)
        val editTextYards = findViewById<EditText>(R.id.editTextYards)

        val textViewMeters = findViewById<TextView>(R.id.textViewMeters)
        val textViewKilometers = findViewById<TextView>(R.id.textViewKilometers)
        val textViewMiles = findViewById<TextView>(R.id.textViewMiles)
        val textViewFeet = findViewById<TextView>(R.id.textViewFeet)
        val textViewYards = findViewById<TextView>(R.id.textViewYards)

        // Наблюдение за изменениями в LiveData
        viewModel.meters.observe(this) {
            textViewMeters.text = "Meters: $it"
        }

        viewModel.kilometers.observe(this) {
            textViewKilometers.text = "Kilometers: $it"
        }

        viewModel.miles.observe(this) {
            textViewMiles.text = "Miles: $it"
        }

        viewModel.feet.observe(this) {
            textViewFeet.text = "Feet: $it"
        }

        viewModel.yards.observe(this) {
            textViewYards.text = "Yards: $it"
        }

        // Обработка изменений в EditText
        editTextMeters.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateValues(s.toString(), "meters")
            }
        })

        editTextKilometers.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateValues(s.toString(), "kilometers")
            }
        })

        editTextMiles.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateValues(s.toString(), "miles")
            }
        })

        editTextFeet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateValues(s.toString(), "feet")
            }
        })

        editTextYards.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateValues(s.toString(), "yards")
            }
        })
    }
}