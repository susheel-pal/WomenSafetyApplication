package com.example.womensafetyapplication.doas

import com.example.womensafetyapplication.models.Parent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ParentDao {
    val db = FirebaseFirestore.getInstance()
    val parentCollections = db.collection("parents")
    //val auth = Firebase.auth

    fun addParent(text: String, number: String){

        val parent = Parent(text, number)                 //add number
        parentCollections.document().set(parent)

    }
}