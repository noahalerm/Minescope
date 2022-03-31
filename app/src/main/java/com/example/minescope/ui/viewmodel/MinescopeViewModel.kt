package com.example.minescope.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minescope.data.models.TransparentMineral
import com.example.minescope.data.models.OpaqueMineral
import com.example.minescope.data.models.OpaqueMineralSample
import com.example.minescope.data.models.TransparentMineralSample

class MinescopeViewModel: ViewModel() {
    //ATTRIBUTES
    var transparentMineralsList = mutableListOf<TransparentMineral>()
    var opaqueMineralsList = mutableListOf<OpaqueMineral>()
    var transparentMineralsListLD = MutableLiveData<MutableList<TransparentMineral>>()
    var opaqueMineralsListLD = MutableLiveData<MutableList<OpaqueMineral>>()
    var samplesOfTransparentMineralList = mutableListOf<TransparentMineralSample>()
    var samplesOfTransparentMineralListLD = MutableLiveData<MutableList<TransparentMineralSample>>()
    var samplesOfOpaqueMineralList = mutableListOf<OpaqueMineralSample>()
    var samplesOfOpaqueMineralListLD = MutableLiveData<MutableList<OpaqueMineralSample>>()
    var actualLPA = ""
    var actualLPNA = ""
    //Booleans
    var shouldPlay: Boolean = false

    init {
        for(m in 1..20){
            samplesOfTransparentMineralList.add(TransparentMineralSample(m,m,"https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/LPA/IMG_", "https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/LPNA/IMG_","SAMPLE T $m","A","A",33.03F,"A","A","A","A","A","A","A","A","A"))
            samplesOfTransparentMineralListLD.postValue(samplesOfTransparentMineralList)
            samplesOfOpaqueMineralList.add(OpaqueMineralSample(m,m,"https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/LPA/IMG_", "https://ddd.uab.cat/pub/minescope/Serpentina_amb_olivina/LPNA/IMG_","SAMPLE O $m","A","A",33.03F,"A","A","A","A","A","A","A","A"))
            samplesOfOpaqueMineralListLD.postValue(samplesOfOpaqueMineralList)
        }
        for(m in 1..20){
            transparentMineralsList.add(TransparentMineral(m,"MINERAL T $m","WHITELESS AND REDS","WITHOUT","H20", "LOW","0/1/2","RIGHT","1st","Right / Undulose / Total","Without / Simple / Polysynthetic","Uniaxial","Positive","Hypidiomorphic with rectangular longitudinal sections and allotriomorphic crystals","Not present, it is altering previous minerals","Not present",null, samplesOfTransparentMineralList))
            transparentMineralsListLD.postValue(transparentMineralsList)
            opaqueMineralsList.add(OpaqueMineral(m,"MINERAL O $m","WHITELESS AND REDS","WITHOUT","H20", "LOW","MUXA","RIGHT","uwu","Right / Undulose / Total","Without / Simple / Polysynthetic","Uniaxial", samplesOfOpaqueMineralList))
            opaqueMineralsListLD.postValue(opaqueMineralsList)
        }
    }
}
