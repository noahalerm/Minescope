package com.example.minescope.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MinescopeViewModel: ViewModel() {
    var mineralsList = mutableListOf<String>()
    var mineralsListLD = MutableLiveData<MutableList<String>>()
}