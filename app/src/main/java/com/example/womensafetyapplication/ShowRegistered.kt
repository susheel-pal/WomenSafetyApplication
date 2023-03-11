package com.example.womensafetyapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.womensafetyapplication.doas.ParentDao
import com.example.womensafetyapplication.models.Parent
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.Query
import java.io.IOException
import java.util.*

class ShowRegistered : AppCompatActivity() {

    var fab: FloatingActionButton? = null
    var recyclerView: RecyclerView? = null

    private lateinit var parentDao: ParentDao
    private lateinit var adapter: ParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_registered)

        fab = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recyclerView)

        fab?.setOnClickListener{
            val intent = Intent (this, RegisterParent::class.java)
            startActivity (intent)

        }


        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){
        parentDao = ParentDao()
        val parentCollections = parentDao.parentCollections
        val query = parentCollections.orderBy("text", Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Parent>().setQuery(query, Parent::class.java).build()

        adapter = ParentAdapter(recyclerViewOptions)

        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}