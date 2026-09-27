package com.example.androidpathaoparceljourney.ui.hub

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidpathaoparceljourney.R
import com.example.androidpathaoparceljourney.databinding.ActivityCisdhubBinding
import com.example.androidpathaoparceljourney.model.Parcel

class CISDHubActivity : AppCompatActivity() {
    lateinit var binding: ActivityCisdhubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCisdhubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val parcel: Parcel =
            IntentCompat.getSerializableExtra<Parcel>(intent, "parcel", Parcel::class.java)!!

        var agentAssigned: Boolean = false

        binding.parcelInfo.recipientName.text = "Recipient Name : " + parcel.recipientName
        binding.parcelInfo.recipientPhone.text = "Phone number : " + parcel.recipientPhoneNumber
        binding.parcelInfo.deliveryAddress.text = "Address : " + parcel.deliveryAddress
        binding.parcelInfo.amountToCollect.text =
            "Totaal amount to collect : " + parcel.amountToCollect.toString()

        binding.parcelUpdateButton.setOnClickListener {
            if (!agentAssigned) {
                agentAssigned = true
                binding.parcelInfo.recipientName.text = "Recipient Name : " + parcel.recipientName
                binding.parcelInfo.recipientPhone.text =
                    "Phone number : " + parcel.recipientPhoneNumber
                binding.parcelInfo.deliveryAddress.text = "Address : " + parcel.deliveryAddress
                binding.parcelInfo.amountToCollect.text =
                    "Total amount to collect : " + parcel.amountToCollect.toString()

                binding.parcelUpdateButton.text = "Assign to agent sajid for LMH(Last Mile Hub) transfer 🚚"

                Toast.makeText(
                    this@CISDHubActivity,
                    "Hub Manager Received The Parcel",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this@CISDHubActivity,
                    "Assigned to sajid and he is taking this parcel to LMH(Last Mile Hub)",
                    Toast.LENGTH_LONG
                ).show()

                startActivity(Intent(this@CISDHubActivity, CISDHubActivity::class.java).apply {
                    putExtra("parcel", parcel)
                })
                finish()
            }


        }

    }
}