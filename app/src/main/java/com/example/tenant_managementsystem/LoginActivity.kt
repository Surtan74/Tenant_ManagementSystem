
package com.example.tenant_managementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenant_managementsystem.databinding.ActivityLoginBinding
import com.example.tenant_managementsystem.ui.theme.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Automatically fill email after registration
        val registeredEmail = intent.getStringExtra("EMAIL")

        if (registeredEmail != null) {
            binding.emailEditText.setText(registeredEmail)
        }

        // Login button
        binding.loginButton.setOnClickListener {

            val email = binding.emailEditText.text.toString().trim()

            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter your email and password",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()
        }

        // Open registration screen
        binding.registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Open website when Help link is clicked
        binding.helpTextView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.strathmore.edu")
            )
            startActivity(intent)
        }
    }
}
