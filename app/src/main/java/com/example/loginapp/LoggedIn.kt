package com.example.loginapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore

class LoggedIn : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)?:return
        val isLogin = sharedPref.getString("Email", "1")
        /*val email = intent.getStringExtra("email")
        if (isLogin=="1"){
            with(sharedPref.edit()){
                putString("Email", email)
                apply()
            }
        }else{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
*/
        if (isLogin == "1"){

        }else{
            setText(isLogin)
        }

    }

    private fun setText(email: String?) {
        db = FirebaseFirestore.getInstance()
        db.collection("USERS").document(email.toString()).get()
            .addOnSuccessListener {
                tasks->
            }
    }
}