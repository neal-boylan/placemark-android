package ie.setu.placemark.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ie.setu.placemark.R
import ie.setu.placemark.databinding.ActivityPlacemarkBinding
import ie.setu.placemark.main.MainApp
import ie.setu.placemark.models.PlacemarkModel

class PlacemarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlacemarkBinding
    var placemark = PlacemarkModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlacemarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        if (intent.hasExtra("placemark_edit")) {
            binding.btnAdd.setText(R.string.button_savePlacemark)
            placemark = intent.extras?.getParcelable("placemark_edit")!!
            binding.placemarkTitle.setText(placemark.title)
            binding.placemarkDescription.setText(placemark.description)
        }

        binding.btnAdd.setOnClickListener() {
            placemark.title = binding.placemarkTitle.text.toString()
            placemark.description = binding.placemarkDescription.text.toString()

            if (placemark.title.isNotEmpty() && placemark.description.isNotEmpty()) {
                // app.placemarks.create(placemark.copy())
                // app.placemarks.update(placemark.copy())
/*
                if (app.placemarks.findOne(placemark)  != null) {
                    app.placemarks.update(placemark)
                }
                else {
                    app.placemarks.create(placemark.copy())
                }
*/
                if (intent.hasExtra("placemark_edit")) {
                    placemark = intent.extras?.getParcelable("placemark_edit")!!
                    app.placemarks.update(placemark)
                } else{
                    app.placemarks.create(placemark.copy())
                }

                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                    .make(it,getString(R.string.warning_addPlacemark), Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_placemark, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}