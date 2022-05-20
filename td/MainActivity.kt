package com.flovit.td

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.td.R
import com.example.td.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

private const val MENU_ID_TIME_PICKER = 56
private val MENU_ID_DATE_PICKER = 57
private val MENU_ID_ALERT_DIALOG = 58
private val MENU_ID_MAP = 59
private val MENU_ID_WEATHER = 60
private val MENU_ID_MYAPI = 61

val SDF = SimpleDateFormat("dd/MM/yy HH:mm")

class MainActivity : AppCompatActivity(), View.OnClickListener, TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {

    //Permet d'instancier les composants graphiques à la 1er utilisation
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    val calendar = Calendar.getInstance()

    //création écran
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //on écoute les clics des boutons -> demandera l'implémentation du Listener
        binding.btValidate.setOnClickListener(this)
        binding.btCancel.setOnClickListener(this)
    }

    //Création du Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_ID_TIME_PICKER,0,"TimePicker")
        menu.add(0, MENU_ID_DATE_PICKER,0,"Datepicker")
        menu.add(0, MENU_ID_ALERT_DIALOG,0,"AlertDialog")
        menu.add(0, MENU_ID_WEATHER,0,"Météo")
        menu.add(0, MENU_ID_MAP, 0, "Maps")
        menu.add(0, MENU_ID_MYAPI, 0, "MyAPI")


        return super.onCreateOptionsMenu(menu)
    }

    //Click sur les menus
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == MENU_ID_TIME_PICKER) {
            TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE), true).show()
        }
        else if(item.itemId == MENU_ID_DATE_PICKER) {
            DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        else if(item.itemId == MENU_ID_ALERT_DIALOG) {
            //Préparation de la fenêtre
            val adb = AlertDialog.Builder(this)
            //Message / titre
            adb.setMessage("AlertDialog").setTitle("Mon titre")
            //bouton ok
            adb.setPositiveButton("ok") { dialog, which ->
                //Affiche un toast apres le click sur le bouton ok
                Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
            }
            //Icone
            adb.setIcon(R.mipmap.ic_launcher);
            //Afficher la fenêtre
            adb.show();
        }
        else if(item.itemId == MENU_ID_WEATHER){
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)

        }
        else if(item.itemId == MENU_ID_MAP){
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)

        }
        else if(item.itemId == MENU_ID_MYAPI){
            val intent = Intent(this, ApiActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

    //Callback du TimePicker
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        Toast.makeText(this, SDF.format(calendar.time), Toast.LENGTH_SHORT).show()
    }

    //Callback du TimePicker
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        Toast.makeText(this, SDF.format(calendar.time), Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        if(v === binding.btValidate ){
            if(binding.rbLike.isChecked) {
                binding.et.setText(binding.rbLike.text)
            }
            else if(binding.rbDislike.isChecked) {
                binding.et.setText(binding.rbDislike.text)
            }
            binding.iv.setImageResource(R.drawable.new_asset)
            binding.iv.setColorFilter(Color.CYAN)
        }
        else if(v === binding.btValidate ){
            binding.et.setText("")
            binding.rg.clearCheck() //clear les radiobutton du radiogroupe
            binding.iv.setImageResource(R.drawable.ic_android_black_24dp)
        }
    }

}
