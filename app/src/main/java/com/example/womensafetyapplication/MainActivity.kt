package com.example.womensafetyapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton

class MainActivity : AppCompatActivity() {

    var call_polish : Button? = null
    var location :AppCompatImageButton? = null
    var sos :Button? = null
    var women_helpline : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        call_polish = findViewById(R.id.call_polish)
        location = findViewById(R.id.location)
        sos = findViewById(R.id.call_sos)
        women_helpline = findViewById(R.id.call_women_helpline)

        //change color of sos
        //sos?.setBackgroundColor()


        call_polish?.setOnClickListener {

            val dialIntentPolish = Intent(Intent.ACTION_DIAL)
            dialIntentPolish.data = Uri.parse("tel:" + "100")
            startActivity(dialIntentPolish)
        }

        location?.setOnClickListener {
            val intent = Intent(this, ShowLocation::class.java)
            startActivity(intent)

        }

        sos?.setOnClickListener {
            val dialIntentSos = Intent(Intent.ACTION_DIAL)
            dialIntentSos.data = Uri.parse("tel:" + "112")
            startActivity(dialIntentSos)
        }

        women_helpline?.setOnClickListener {
            val dialIntentWomenHelpline = Intent(Intent.ACTION_DIAL)
            dialIntentWomenHelpline.data = Uri.parse("tel:" + "1091")
            startActivity(dialIntentWomenHelpline)
        }

    }
}