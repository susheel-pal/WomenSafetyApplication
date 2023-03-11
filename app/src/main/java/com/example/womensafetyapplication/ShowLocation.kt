package com.example.womensafetyapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException
import java.util.*
import com.example.womensafetyapplication.ParentAdapter.ParentViewHolder
import com.example.womensafetyapplication.doas.ParentDao
import com.example.womensafetyapplication.models.Parent
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlin.collections.ArrayList

class ShowLocation : AppCompatActivity() {

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null

    var country: TextView? = null
    var city: TextView? = null
    var address: TextView? = null
    var longitude: TextView? = null
    var latitude: TextView? = null
    var getLocation: Button? = null
    //var fab1: FloatingActionButton? = null
    var fab2: FloatingActionButton? = null

    private val REQUEST_CODE = 100

    //for number intent
    private lateinit var parentDao: ParentDao
    private lateinit var adapter: ParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_location)

        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        address = findViewById(R.id.address)
        city = findViewById(R.id.city)
        country = findViewById(R.id.country)
        getLocation = findViewById(R.id.getLocation)
        //fab1 = findViewById(R.id.fab1)
        fab2 = findViewById(R.id.fab2)



        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getLocation?.setOnClickListener {
            getLastLocation()
        }

        //fab1?.setOnClickListener {
            //val intent = Intent(this, ShowRegistered::class.java)
            //startActivity(intent)
        //}

        //for number message
        fab2?.setOnClickListener {
            //parentDao = ParentDao()
            //val parentCollections = parentDao.parentCollections
            //val query = parentCollections.orderBy("text", Query.Direction.DESCENDING)
            //val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Parent>().setQuery(query, Parent::class.java).build()

            //adapter = ParentAdapter(recyclerViewOptions)
            //val position = adapter.itemCount.dec()
            //val postNumber = adapter.getItem(position).number
            val postNumber = ArrayList<String>()
            postNumber.add("9335443679")
            postNumber.add("9026074826")

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$postNumber"))
            //val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "9335443679"))
            intent.putExtra("sms_body", "Position: ${latitude?.text.toString()}, ${longitude?.text.toString()}, ${address?.text.toString()}, ${city?.text.toString()}, ${country?.text.toString()}")
            startActivity(intent)

        }
    }

    private fun getLastLocation() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient?.lastLocation
                ?.addOnSuccessListener(OnSuccessListener<Location> {
                    if (it != null) {
                        val geocoder = Geocoder(this@ShowLocation, Locale.getDefault())
                        var addresses: List<Address>? = null
                        try {
                            addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                            latitude?.text = "Latitude: ${addresses?.get(0)?.latitude}"
                            longitude?.text = "Longitude: ${addresses?.get(0)?.longitude}"
                            address?.text = "Address: ${addresses?.get(0)?.getAddressLine(0)}"
                            city?.text = "City: ${addresses?.get(0)?.locality}"
                            country?.text = "Country: ${addresses?.get(0)?.countryName}"

                        } catch (e : IOException){
                            e.printStackTrace()
                        }

                    }
                })
        }
        else {
            askPermission();
        }
    }

    private fun askPermission(){
        ActivityCompat.requestPermissions(this@ShowLocation,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation()
            }
            else{
                Toast.makeText(this, "Permission Required", Toast.LENGTH_LONG).show()
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}