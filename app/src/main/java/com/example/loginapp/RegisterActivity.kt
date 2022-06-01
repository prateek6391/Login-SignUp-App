package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        continue_btn.setOnClickListener {
            if(checking()){
                val name_reg = name_ET.text.toString()
                val number_reg = number_ET.text.toString()
                val email_reg = email_ET.text.toString()
                val password_reg = password_ET.text.toString()
                auth.createUserWithEmailAndPassword(email_reg, password_reg)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(baseContext, "User Added", Toast.LENGTH_SHORT).show()

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "User not Added", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else{
                Toast.makeText(this, "Enter The Details", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun checking(): Boolean {
        if(name_ET.text.toString().trim{it<=' '}.isNotEmpty() && number_ET.text.toString().trim {it<=' '}.isNotEmpty() &&
            email_ET.text.toString().trim{it<=' '}.isNotEmpty() && password_ET.text.toString().trim {it<=' '}.isNotEmpty()){
            return true
        }
        return false
    }
}