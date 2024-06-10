package com.bank.bankregistrationtask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bank.bankregistrationtask.util.DateValidater
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainViewModel:ViewModel() {
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    public suspend fun validatePanNumber(pan: String?): Boolean {
        return withContext(Dispatchers.IO) {
            // Simulate network call or any heavy task
            pan?.matches(Regex("[A-Z]{5}[0-9]{4}[A-Z]{1}")) == true
        }
    }

    fun isDateValid(day:String, month:String,year:String)=DateValidater.isValidDOB(day,month,year)

    fun onNextButtonClick() {
        viewModelScope.launch {
            _toastMessage.value = "Details submitted successfully"
            // Finish activity logic can be implemented in the observer in the activity/fragment
        }
    }



}