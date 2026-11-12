
package com.example.tenant_managementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tenant_managementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var lastTenant: Tenant? = null

    companion object {
        private const val TAG = "TENANT_LIFECYCLE"
        private const val KEY_TENANT_NAME = "tenant_name"
        private const val KEY_TENANT_PHONE = "tenant_phone"
        private const val KEY_TENANT_RENT = "tenant_rent"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate called")

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        // Restore the previously saved tenant after screen rotation
        if (savedInstanceState != null) {
            val name = savedInstanceState.getString(KEY_TENANT_NAME)
            val phone = savedInstanceState.getString(KEY_TENANT_PHONE)
            val rent = savedInstanceState.getString(KEY_TENANT_RENT)

            if (name != null && phone != null && rent != null) {
                val tenant = Tenant(
                    name = name,
                    phone = phone,
                    rent = rent
                )

                lastTenant = tenant
                binding.tenant = tenant
                binding.btnCallTenant.visibility = View.VISIBLE
            }
        }

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

            lastTenant = tenant
            binding.tenant = tenant
            binding.executePendingBindings()

            binding.btnCallTenant.visibility = View.VISIBLE

            Toast.makeText(
                this,
                "Tenant saved successfully",
                Toast.LENGTH_SHORT
            ).show()

            binding.etName.text.clear()
            binding.etPhone.text.clear()
            binding.etRent.text.clear()
        }

        // CALL TENANT button functionality
        binding.btnCallTenant.setOnClickListener {
            val tenant = lastTenant

            if (tenant != null) {
                val dialIntent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:${tenant.phone}")
                )

                startActivity(dialIntent)
            } else {
                Toast.makeText(
                    this,
                    "No tenant information available",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        lastTenant?.let { tenant ->
            outState.putString(KEY_TENANT_NAME, tenant.name)
            outState.putString(KEY_TENANT_PHONE, tenant.phone)
            outState.putString(KEY_TENANT_RENT, tenant.rent)
        }

        Log.d(TAG, "onSaveInstanceState called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
}
