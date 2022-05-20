/*package com.example.td

/*fun main(){
  //  val res = RequestUtils.sendGet("htttps://google.fr")
  //  println("res=$res")

}*/
fun main() {
    val weather = RequestUtils.loadWeather("Toulouse")
    println("Il fait ${weather.temperature.temp} ° à ${weather.name}")
}*/