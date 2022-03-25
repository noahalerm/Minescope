package com.example.minescope.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minescope.data.models.TransparentMineral
import com.example.minescope.data.models.OpaqueMineral

class MinescopeViewModel: ViewModel() {
    //ATTRIBUTES
    var transparentMineralsList = mutableListOf<TransparentMineral>()
    var opaqueMineralsList = mutableListOf<OpaqueMineral>()
    var transparentMineralsListLD = MutableLiveData<MutableList<TransparentMineral>>()
    var opaqueMineralsListLD = MutableLiveData<MutableList<OpaqueMineral>>()

    //Booleans
    var shouldPlay: Boolean = false

    init {
        for(m in 1..20){
            transparentMineralsList.add(TransparentMineral(m,"MINERAL T $m","WHITELESS AND REDS","WITHOUT","H20", "LOW","0/1/2","RIGHT","1st","Right / Undulose / Total","Without / Simple / Polysynthetic","Uniaxial","Positive","Hypidiomorphic with rectangular longitudinal sections and allotriomorphic crystals","Not present, it is altering previous minerals","Not present",null))
            transparentMineralsListLD.postValue(transparentMineralsList)
            opaqueMineralsList.add(OpaqueMineral(m,"MINERAL O $m","WHITELESS AND REDS","WITHOUT","H20", "LOW","MUXA","RIGHT","uwu","Right / Undulose / Total","Without / Simple / Polysynthetic","Uniaxial"))
            opaqueMineralsListLD.postValue(opaqueMineralsList)
        }
    }
}
