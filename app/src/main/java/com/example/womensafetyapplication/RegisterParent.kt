package com.example.womensafetyapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.womensafetyapplication.doas.ParentDao

class RegisterParent : AppCompatActivity() {

    var postButton: Button? = null
    var postInput: EditText? = null
    var postNumber: EditText? = null

    private lateinit var parentDao: ParentDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_parent)

        postButton = findViewById(R.id.postButton)
        postInput = findViewById(R.id.postInput)
        postNumber = findViewById(R.id.postNumber)

        parentDao = ParentDao()

        postButton?.setOnClickListener{
            val input = postInput?.text.toString().trim()
            val number = postNumber?.text.toString().trim()
            if(input.isNotEmpty()){
                parentDao.addParent(input, number)
                finish()
            }
        }
    }
}