package com.example.tenant_managementsystem

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tenant_managementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.btnSave.setOnClickListener {

            val name = binding.etName.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val rent = binding.etRent.text.toString().trim()

            if (name.isEmpty()) {
                binding.etName.error = "Enter tenant name"
                binding.etName.requestFocus()
                return@setOnClickListener
            }

            if (phone.isEmpty()) {
                binding.etPhone.error = "Enter phone number"
                binding.etPhone.requestFocus()
                return@setOnClickListener
            }

            if (rent.isEmpty()) {
                binding.etRent.error = "Enter rent paid"
                binding.etRent.requestFocus()
                return@setOnClickListener
            }

            val tenant = Tenant(
                name = name,
                phone = phone,
                rent = rent
            )

            binding.tenant = tenant
            binding.executePendingBindings()

            Toast.makeText(
                this,
                "Tenant saved successfully",
                Toast.LENGTH_SHORT
            ).show()

            binding.etName.text.clear()
            binding.etPhone.text.clear()
            binding.etRent.text.clear()
        }
    }
}