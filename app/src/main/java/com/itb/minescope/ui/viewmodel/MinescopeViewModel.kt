package com.itb.minescope.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itb.minescope.data.models.TransparentMineral
import com.itb.minescope.data.models.OpaqueMineral
import com.itb.minescope.data.models.OpaqueMineralSample
import com.itb.minescope.data.models.TransparentMineralSample
import com.itb.minescope.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MinescopeViewModel: ViewModel() {
    //ATTRIBUTES
    private val repository = Repository()

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

    //Filters
    var transparentFilters: TransparentMineral? = null
    var opaqueFilters: OpaqueMineral? = null

    //INIT
    //init {
    //    //Samples
    //    for(m in 1..20){
    //        samplesOfTransparentMineralList.add(TransparentMineralSample(m, m, "https://ddd.uab.cat/pub/minescope/seroli_lpa/seroli_lpa_", "https://ddd.uab.cat/pub/minescope/seroli_lpna/seroli_lpna_", "Serpentine with olivine", "Colorless", "Not present", 70F, "Olivine 30%", "Low", "Not appreciable", "Altering olivine", "1st order grays", "Not easily appreciable", "Not present", "Not present", "Fibrous aggregates in fractures altering olivine"))
    //        samplesOfTransparentMineralListLD.postValue(samplesOfTransparentMineralList)
    //        samplesOfOpaqueMineralList.add(OpaqueMineralSample(m,m,"https://ddd.uab.cat/pub/minescope/ansmas_lpa/ansmas_lpa_", "https://ddd.uab.cat/pub/minescope/ansmas_lpna/ansmas_lpna_","Massive arsenopyrite","White with a slight creamy tone","Weak, It is easier to see in the center of the image",80F,"Gangue 20%","Allotriomorphic","Not visible","Strong","Not visible","Strong","Blue, gray and brownish tones","Not present"))
    //        samplesOfOpaqueMineralListLD.postValue(samplesOfOpaqueMineralList)
    //    }
    //
    //    //Minerals
    //    for(m in 1..20){
    //        transparentMineralsList.add(TransparentMineral(m,"Serpentine $m"," Colorless / Yellow / Green ","Without / Soft","(Mg,Al,Fe,Mn,Ni,Zn)<sub>2-3</sub>(Si,Al,Fe)<sub>2</sub>O<sub>5</sub>(OH)<sub>4</sub>", "LOW","0/1",null,"1st / Anomalous","Right / Total","Without","Biaxial","Negative / Positive","In parallel aggregates of fibrous crystals (asbestos) in fractures or massive","It is an alteration mineral","Not present","Not appreciable", samplesOfTransparentMineralList))
    //        transparentMineralsListLD.postValue(transparentMineralsList)
    //        opaqueMineralsList.add(OpaqueMineral(m,"Arsenopyrite $m","White / Yellow / Pink","Without or weak","FeAsS", "Strong","Hard","Moderately anisotropic / Strongly anisotropic","Yellow / Blue / Green / Brown","No","Not visible","Chalcopyrite, cobaltite, cubanite, enargite, galena, gold, jamesonite, oleidite, pyrrhotite, sphalerite, stannite, wolframite, among others", samplesOfOpaqueMineralList))
    //        opaqueMineralsListLD.postValue(opaqueMineralsList)
    //    }
    //
    //    loadData()
    //}

    init {
        changeLanguage(lan())
    }

    //METHODS

    /**
     * This method is used to obtain the current device's language.
     */
    private fun lan(): String {
        val lan = Locale.getDefault().displayLanguage
        var lanStr = "en"

        when(lan){
            "English" -> lanStr = "en"
            "español" -> lanStr = "sp"
            "català" -> lanStr = "ca"
        }

        return lanStr
    }

    /**
     * This method is used to change the app's language.
     */
    fun changeLanguage(lan: String){
        viewModelScope.launch {
            opaqueMineralsList.clear()
            transparentMineralsList.clear()
            samplesOfOpaqueMineralList.clear()
            samplesOfTransparentMineralList.clear()

            val response = withContext(Dispatchers.IO) { repository.getOpaqueMinerals(lan)}
            val response2 = withContext(Dispatchers.IO) { repository.getTransparentMinerals(lan)}
            val response3 = withContext(Dispatchers.IO) { repository.getOpaqueMineralSamples(lan)}
            val response4 = withContext(Dispatchers.IO) { repository.getTransparentMineralSamples(lan)}

            //OPAQUE MINERALS
            if (response.isSuccessful) {
                val mineral = response.body()!!
                Log.d("MINERAL", mineral.toString())
                opaqueMineralsList.addAll(mineral)
                opaqueMineralsListLD.postValue(opaqueMineralsList)
                Log.d("LIST", opaqueMineralsList.toString())
            }
            else{
                Log.e("Error :", response.message())
            }

            //TRANSPARENT MINERALS
            if(response2.isSuccessful) {
                val mineral = response2.body()!!
                transparentMineralsList.addAll(mineral)
                transparentMineralsListLD.postValue(transparentMineralsList)
            }
            else{
                Log.e("Error :", response2.message())
            }

            //OPAQUE SAMPLES MINERALS
            if (response3.isSuccessful) {
                val mineral = response3.body()!!
                samplesOfOpaqueMineralList.addAll(mineral)
                samplesOfOpaqueMineralListLD.postValue(samplesOfOpaqueMineralList)
            }
            else{
                Log.e("Error :", response3.message())
            }

            //TRANSPARENT SAMPLES MINERALS
            if(response4.isSuccessful) {
                val mineral = response4.body()!!
                samplesOfTransparentMineralList.addAll(mineral)
                samplesOfTransparentMineralListLD.postValue(samplesOfTransparentMineralList)
            }
            else{
                Log.e("Error :", response4.message())
            }
        }
    }
}
