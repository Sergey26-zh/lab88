package ru.sergeyivahnenko.lab88

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LengthViewModel : ViewModel() {

    // Константы для конвертации
    private val METERS_TO_KILOMETERS = 0.001
    private val METERS_TO_MILES = 0.000621371
    private val METERS_TO_FEET = 3.28084
    private val METERS_TO_YARDS = 1.09361

    // LiveData для хранения значений
    private val _meters = MutableLiveData<String>()
    val meters: LiveData<String> get() = _meters

    private val _kilometers = MutableLiveData<String>()
    val kilometers: LiveData<String> get() = _kilometers

    private val _miles = MutableLiveData<String>()
    val miles: LiveData<String> get() = _miles

    private val _feet = MutableLiveData<String>()
    val feet: LiveData<String> get() = _feet

    private val _yards = MutableLiveData<String>()
    val yards: LiveData<String> get() = _yards

    // Флаг для предотвращения бесконечного погружения
    private var isUpdating = false

    // Метод для обновления значений
    fun updateValues(newValue: String, fromUnit: String) {
        if (isUpdating) return
        isUpdating = true

        val value = newValue.toDoubleOrNull() ?: 0.0

        when (fromUnit) {
            "meters" -> {
                _meters.value = newValue
                _kilometers.value = "%.3f".format(value * METERS_TO_KILOMETERS)
                _miles.value = "%.6f".format(value * METERS_TO_MILES)
                _feet.value = "%.2f".format(value * METERS_TO_FEET)
                _yards.value = "%.2f".format(value * METERS_TO_YARDS)
            }
            "kilometers" -> {
                val metersValue = value / METERS_TO_KILOMETERS
                _meters.value = "%.2f".format(metersValue)
                _kilometers.value = newValue
                _miles.value = "%.6f".format(metersValue * METERS_TO_MILES)
                _feet.value = "%.2f".format(metersValue * METERS_TO_FEET)
                _yards.value = "%.2f".format(metersValue * METERS_TO_YARDS)
            }
            "miles" -> {
                val metersValue = value / METERS_TO_MILES
                _meters.value = "%.2f".format(metersValue)
                _kilometers.value = "%.3f".format(metersValue * METERS_TO_KILOMETERS)
                _miles.value = newValue
                _feet.value = "%.2f".format(metersValue * METERS_TO_FEET)
                _yards.value = "%.2f".format(metersValue * METERS_TO_YARDS)
            }
            "feet" -> {
                val metersValue = value / METERS_TO_FEET
                _meters.value = "%.2f".format(metersValue)
                _kilometers.value = "%.3f".format(metersValue * METERS_TO_KILOMETERS)
                _miles.value = "%.6f".format(metersValue * METERS_TO_MILES)
                _feet.value = newValue
                _yards.value = "%.2f".format(metersValue * METERS_TO_YARDS)
            }
            "yards" -> {
                val metersValue = value / METERS_TO_YARDS
                _meters.value = "%.2f".format(metersValue)
                _kilometers.value = "%.3f".format(metersValue * METERS_TO_KILOMETERS)
                _miles.value = "%.6f".format(metersValue * METERS_TO_MILES)
                _feet.value = "%.2f".format(metersValue * METERS_TO_FEET)
                _yards.value = newValue
            }
        }

        isUpdating = false
    }
}