package com.example.minescope.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MinescopeViewModel: ViewModel() {
    //ATTRIBUTES
    var mineralsList = mutableListOf<String>()
    var mineralsListLD = MutableLiveData<MutableList<String>>()

    //Booleans
    var shouldPlay: Boolean = false
}
