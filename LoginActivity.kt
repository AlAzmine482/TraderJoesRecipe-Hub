package com.example.traderjoes20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val btnNavigateToRegisterActivity = findViewById<Button>(R.id.btnRegister)
        btnNavigateToRegisterActivity.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val buttonToLogin = findViewById<Button>(R.id.btnSignIn)

        buttonToLogin.setOnClickListener{
            performSignIn()
        }

        val buttonToMain = findViewById<Button>(R.id.btnContinue)
        buttonToMain.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        //let's get email and password from the user
    }
    private fun performSignIn() {
        val email = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Please fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, let move to the next activity i.e. MainActivity

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext, "Success",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error occurred ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
    }


    //unused code below
    private fun performSignUp(){
        val email = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Please fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, let move to the next activity i.e. MainActivity

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext, "Success",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error occurred ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }

    }
}