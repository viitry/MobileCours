package com.flovit.td

fun main(){
    val student = StudentBean("Toto")
    student.note++
    println("${student.nom} : ${student.note}")
}

class StudentBean(val nom: String) {
    var note = 0
}

class UserBean(val name: String, var note: Int) {
    val id = name.hashCode()

    constructor(nom: String) : this(nom, 0)
}

//Sans constructeur secondaire
/*class UserBean(val name: String, var note: Int= 0) {
    val id = name.hashCode()
}*/

//////

class PlaneBean(name:String){
    var id = name.hashCode()
    private set

    var name = name
    set(value){
        field = value
        id = name.hashCode()
    }
}
/*data class WeatherBean(
    var coord: CoordBean,
    var visibility: Int,
    var name: String,
    @SerializedName("main")
    var temperature: TempBean
)*/
data class CoordBean(var lat: Double, var lon: Double)

//data class TempBean(var temp: Double, var temp_max: Double, var temp_min: Double)
//data class WeatherBean(var name: String, var visibility:Int)