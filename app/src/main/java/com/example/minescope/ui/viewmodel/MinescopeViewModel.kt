package com.example.minescope.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minescope.data.models.TransparentMineral
import com.example.minescope.data.models.OpaqueMineral
import com.example.minescope.data.models.OpaqueMineralSample
import com.example.minescope.data.models.TransparentMineralSample

class MinescopeViewModel: ViewModel() {
    //ATTRIBUTES
    //Lists
    var transparentMineralsList = mutableListOf<TransparentMineral>()
    var opaqueMineralsList = mutableListOf<OpaqueMineral>()
    var samplesOfTransparentMineralList = mutableListOf<TransparentMineralSample>()
    var samplesOfOpaqueMineralList = mutableListOf<OpaqueMineralSample>()

    //Live Data
    var transparentMineralsListLD = MutableLiveData<MutableList<TransparentMineral>>()
    var opaqueMineralsListLD = MutableLiveData<MutableList<OpaqueMineral>>()
    var samplesOfTransparentMineralListLD = MutableLiveData<MutableList<TransparentMineralSample>>()
    var samplesOfOpaqueMineralListLD = MutableLiveData<MutableList<OpaqueMineralSample>>()

    //Sample Images
    var currentLPA = ""
    var currentLPNA = ""
    
    //Booleans
    var shouldPlay: Boolean = false
    var isTransparentFilters: Boolean = true

    //INIT
    init {
        //Samples
        for(m in 1..20){
            samplesOfTransparentMineralList.add(TransparentMineralSample(m, m, "https://ddd.uab.cat/pub/minescope/seroli_lpa/seroli_lpa_", "https://ddd.uab.cat/pub/minescope/seroli_lpna/seroli_lpna_", "Serpentine with olivine", "Colorless", "Not present", 70F, "Olivine 30%", "Low", "Not appreciable", "Altering olivine", "1st order grays", "Not easily appreciable", "Not present", "Not present", "Fibrous aggregates in fractures altering olivine"))
            samplesOfTransparentMineralListLD.postValue(samplesOfTransparentMineralList)
            samplesOfOpaqueMineralList.add(OpaqueMineralSample(m,m,"https://ddd.uab.cat/pub/minescope/ansmas_lpa/ansmas_lpa_", "https://ddd.uab.cat/pub/minescope/ansmas_lpna/ansmas_lpna_","Massive arsenopyrite","White with a slight creamy tone","Weak, It is easier to see in the center of the image",80F,"Gangue 20%","Allotriomorphic","Not visible","Strong","Not visible","Strong","Blue, gray and brownish tones","Not present"))
            samplesOfOpaqueMineralListLD.postValue(samplesOfOpaqueMineralList)
        }

        //Minerals
        for(m in 1..20){
            transparentMineralsList.add(TransparentMineral(m,"MINERAL T $m","WHITELESS AND REDS","WITHOUT","H20", "LOW","0/1/2","RIGHT","1st","Right / Undulose / Total","Without / Simple / Polysynthetic","Uniaxial","Positive","Hypidiomorphic with rectangular longitudinal sections and allotriomorphic crystals","Not present, it is altering previous minerals","Not present",null, samplesOfTransparentMineralList))
            transparentMineralsListLD.postValue(transparentMineralsList)
            opaqueMineralsList.add(OpaqueMineral(m,"MINERAL O $m","WHITELESS AND REDS","WITHOUT","H20", "LOW","MUXA","RIGHT","uwu","Right / Undulose / Total","Without / Simple / Polysynthetic","Uniaxial", samplesOfOpaqueMineralList))
            opaqueMineralsListLD.postValue(opaqueMineralsList)
        }
    }
}
