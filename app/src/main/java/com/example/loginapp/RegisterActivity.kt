package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var  db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        continue_btn.setOnClickListener {
            if(checking()){
                val name_reg = name_ET.text.toString()
                val number_reg = number_ET.text.toString()
                val email_reg = email_ET.text.toString()
                val password_reg = password_ET.text.toString()
                val user = hashMapOf(
                    "Name" to name_reg,
                    "Phone" to number_reg,
                    "email" to email_reg
                )
                val Users = db.collection("USERS")
                val query = Users.whereEqualTo("email", email_reg).get()
                    .addOnSuccessListener {
                        it ->
                        if(it.isEmpty){
                            auth.createUserWithEmailAndPassword(email_reg, password_reg)
                                .addOnCompleteListener(this){
                                    task->
                                    if(task.isSuccessful){
                                        Users.document(email_reg).set(user)
                                        Toast.makeText(this, "All set", Toast.LENGTH_LONG).show()
                                        val intent = Intent(this, LoggedIn::class.java)
                                        intent.putExtra("email",email_reg)
                                        startActivity(intent)
                                        finish()
                                    }
                                    else{
                                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_LONG).show()
                                    }
                                }

                        }
                        else{
                            Toast.makeText(this, "User Already Registered", Toast.LENGTH_LONG).show()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
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