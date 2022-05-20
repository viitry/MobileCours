package com.flovit.td

fun sanslese(texte: String): String{
    var sortie = ""
    for (c in texte){
        //V1
        if (c != 'e' && c != 'E'){
            sortie += c
        }
        //v2
        //if (c !in arrayOf('e', 'E')){
        // sortie += c
        //}
    }
    return sortie
}


fun main(){
    val texte = "Une phrase avec des e"
    println("sanslese : ##" + sanslese(texte) + "##")
}

