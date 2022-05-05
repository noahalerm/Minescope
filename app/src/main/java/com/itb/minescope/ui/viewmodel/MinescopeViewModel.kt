package com.itb.minescope.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itb.minescope.data.models.TransparentMineral
import com.itb.minescope.data.models.OpaqueMineral
import com.itb.minescope.data.models.OpaqueMineralSample
import com.itb.minescope.data.models.TransparentMineralSample

class MinescopeViewModel: ViewModel() {
    //ATTRIBUTES
    //Lists
    var transparentMineralsList = mutableListOf<TransparentMineral>()
    var opaqueMineralsList = mutableListOf<OpaqueMineral>()
    var samplesOfTransparentMineralList = mutableListOf<TransparentMineralSample>()
    var samplesOfOpaqueMineralList = mutableListOf<OpaqueMineralSample>()
    var langList = mutableListOf<String>("Español","Ingles","Catalán")


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

    //Filters
    var transparentFilters: TransparentMineral? = null
    var opaqueFilters: OpaqueMineral? = null

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
            transparentMineralsList.add(TransparentMineral(m,"Serpentine $m"," Colorless / Yellow / Green ","Without / Soft","(Mg,Al,Fe,Mn,Ni,Zn)2-3(Si,Al,Fe)2O5(OH)4", "LOW","0/1",null,"1st / Anomalous","Right / Total","Without","Biaxial","Negative / Positive","In parallel aggregates of fibrous crystals (asbestos) in fractures or massive","It is an alteration mineral","Not present","Not appreciable", samplesOfTransparentMineralList))
            transparentMineralsListLD.postValue(transparentMineralsList)
            opaqueMineralsList.add(OpaqueMineral(m,"Arsenopyrite $m","White / Yellow / Pink","Without or weak","FeAsS", "Strong","Hard","Moderately anisotropic / Strongly anisotropic","Yellow / Blue / Green / Brown","No","Not visible","Chalcopyrite, cobaltite, cubanite, enargite, galena, gold, jamesonite, oleidite, pyrrhotite, sphalerite, stannite, wolframite, among others", samplesOfOpaqueMineralList))
            opaqueMineralsListLD.postValue(opaqueMineralsList)
        }
    }
}
