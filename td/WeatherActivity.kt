package com.flovit.td

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.td.databinding.ActivityWeatherBinding
import kotlin.concurrent.thread

/*class WeatherActivity : AppCompatActivity(), View.OnClickListener {
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater)}

    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btWeather.setOnClickListener(this)
        //Si l'activité a été recrée on a une donnée et on met à jour l'ihm
        /*if(model.data != null) {
            binding.txt.text = "${model.data?.name} : ${model.data?.visibility}"
        }*/
        refreshScreen()
    }
    override fun onClick(v: View) {
        binding.progressBar.visibility = View.VISIBLE
        thread {
            model.loadData()

            runOnUiThread {
                refreshScreen()
                binding.progressBar.visibility = View.GONE
            }
        }
    }
    fun refreshScreen(){
        if(model.data != null){
            binding.txt.text = model.data?.name + " " + model.data?.visibility
        }
        else{
            binding.txt.text = "-"
        }
        if(model.errorMessage != null){
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = model.errorMessage
        }
        else{
            binding.tvError.visibility = View.GONE
        }
    }

}*/
class WeatherActivity : AppCompatActivity(), View.OnClickListener {

    val binding by lazy {ActivityWeatherBinding.inflate(layoutInflater)}
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //je vais écouter mes liveData pour mettre à jour les composants graphiques
        model.data.observe(this) {
            //la nouvelle donnée s'appelle it
            if (it != null) {
                binding.tvVille.text = it.name
                binding.tvTemp.text = it.temperature.temp.toString()
                binding.tvDesc.text = it.weather[0].description
                binding.tvMinMax.text = "${it.temperature.temp_min.toString()} / ${it.temperature.temp_max.toString()}"
                binding.tvWind.text = it.wind.speed.toString()
            } else {
                binding.tvVille.text = "Pas de donnée"
            }
        }

        /*model.errorMessage.observe(this) {
            if(it != null) {
                binding.tvError.isVisible = true
                binding.tvError.text = it
            }
            else {
                binding.tvError.isVisible = false
            }
        }*/

        model.runInProgress.observe(this) {
            binding.progressBar.isVisible = it
        }

        binding.btWeather.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        model.loadData()
    }
}





