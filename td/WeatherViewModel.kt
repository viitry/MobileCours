package com.flovit.td

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception
import kotlin.concurrent.thread


/*class WeatherViewModel : ViewModel(){
    var data : WeatherBean? = null
    var errorMessage : String? = null
    fun loadData() {
        errorMessage = null
        try {
            data = RequestUtils.loadWeather2()
        }
        catch (e:Exception){
            e.printStackTrace()
            errorMessage = e.message
        }
    }
}*/
class WeatherViewModel : ViewModel() {

    //permet d'observer la donnée qu'il contient.
    //Si on le modifie avec PostValue déclanche l'observer
    val data = MutableLiveData<WeatherBean?>()
    val errorMessage = MutableLiveData<String?>()
    val runInProgress = MutableLiveData<Boolean>(false)

    fun loadData(){
        //On reste l'écran sur un clic du bouton
        runInProgress.postValue(true)
        errorMessage.postValue(null)
        data.postValue(null)

        //Déclenche une tache asynchrone
        thread {
            try {
                //Chercher mes données
                data.postValue(RequestUtils.loadWeather("Toulouse"))
            } catch (e: Exception) {
                e.printStackTrace()
                //je fais évoluer mon message d'erreur
                errorMessage.postValue("Erreur : " + e.message)
            }
            runInProgress.postValue(false)
        }
    }
}
