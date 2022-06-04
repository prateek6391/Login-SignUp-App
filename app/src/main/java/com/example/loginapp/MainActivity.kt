package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        login.setOnClickListener {
            if(checking()){
                val email = editTextEmail.text.toString()
                val password = editTextPassword.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val intent = Intent(this, LoggedIn::class.java)
                            intent.putExtra("email",email)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else{
                Toast.makeText(this, "Enter The Details", Toast.LENGTH_LONG).show()
            }
        }





        register.setOnClickListener {
            val regIntent = Intent(this, RegisterActivity::class.java)
            startActivity(regIntent)
            finish()
        }

    }

    private fun checking(): Boolean {
        if(editTextEmail.text.toString().trim{it<=' '}.isNotEmpty() &&
            editTextPassword.text.toString().trim {it<=' '}.isNotEmpty()){
            return true
        }
        return false
    }
}