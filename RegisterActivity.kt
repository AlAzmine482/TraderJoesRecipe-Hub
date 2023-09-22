package com.example.traderjoes20
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.traderjoes20.R.id.backToLogin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //back button
        val buttonToLogin = findViewById<Button>(backToLogin)
        buttonToLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        //end of button

        //register btn to create acc
        val buttonRegisterCreateAccount = findViewById<Button>(R.id.btnCreateAccount)

        buttonRegisterCreateAccount.setOnClickListener{
            performSignUp()
            //  val intent = Intent(this, RegisterActivity::class.java)
            // startActivity(intent)
        }

    }
    private fun performSignUp() {
        val name = findViewById<EditText>(R.id.editTextUsername)
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = findViewById<EditText>(R.id.reg_password)

        if (name.text.isEmpty() || email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val inputName = name.text.toString()
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
                Toast.makeText(this, "Error occurred ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}