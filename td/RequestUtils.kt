package com.flovit.td
import android.os.SystemClock
import com.flovit.td.CoordBean
import com.flovit.td.WeatherActivity
import com.flovit.td.WeatherBean
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.Response
import java.io.InputStreamReader
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*

const val URL_API_WEATHER = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"

val client = OkHttpClient()

public data class TempBean (var temp :Double, var temp_min : Double, var temp_max : Double)
public data class WindBean (var speed : Double)
public data class WeatherBeanCollection(var description : String)
public data class WeatherBean (var name : String, var weather :List<WeatherBeanCollection>, @SerializedName("main") var temperature : TempBean, var wind : WindBean)

class RequestUtils {

    companion object {

        fun loadWeather(city: String): WeatherBean {
            val json = sendGet(URL_API_WEATHER.format(city))
            val data = Gson().fromJson(json, WeatherBean::class.java)
            return data
        }


        /*fun loadWeather2(city : String): WeatherBean {
            val json = sendGet(URL_API_WEATHER.format(city))
            SystemClock.sleep(3000)
            return if (Random().nextBoolean()) WeatherBean("WeatherWeb", 1000)
            else throw Exception("Je ne dirais pas que c'est un échec,je dirais que ca n'a pas marché")
        }*/

        fun sendGet(url: String): String {
            println("url : $url")
            //Création de la requete
            val request = Request.Builder().url(url).build()
            //Execution de la requête
            return client.newCall(request).execute().use {
                //Analyse du code retour
                if (!it.isSuccessful) {
                    throw Exception("Réponse du serveur incorrect :${it.code}")
                }
                //Résultat de la requete
                it.body?.string() ?: ""
            }
        }

        //Optimiser car il récupère un flux et non un String qu'il transmet directement à Gson
        //On a donc un seul parcourt du JSON et un seul stockage mémoire vu qu'il ne passe
        //pas par un String intermédiaire
        //use permet de fermer la réponse qu'il y ait ou non une exception
        /*fun loadWeatherOpti(city: String) = sendGetOpti(URL_API_WEATHER.format(city)).use { //it:Response
            var isr = InputStreamReader(it.body?.byteStream())
            gson.fromJson(isr, WeatherBean::class.java)
        }*/


        fun sendGetOpti(url: String): Response {
            println("url : $url")
            //Création de la requete
            val request = Request.Builder().url(url).build()
            //Execution de la requête
            val response = client.newCall(request).execute()
            //Analyse du code retour
            return if (response.isSuccessful) {
                //On ferme la réponse qui n'est plus fermé par le use
                response.close()
                throw Exception("Réponse du serveur incorrect : ${response.code}")
            } else {
                response
            }
        }
    }
}
