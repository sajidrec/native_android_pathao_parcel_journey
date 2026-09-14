package com.example.androidpathaoparceljourney.ui.hub

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidpathaoparceljourney.R
import com.example.androidpathaoparceljourney.databinding.ActivityPickupReceiveBinding
import com.example.androidpathaoparceljourney.model.Parcel

class PickupReceiveActivity : AppCompatActivity() {

    lateinit var binding: ActivityPickupReceiveBinding
    var agentAssigned: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPickupReceiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val parcel: Parcel =
            IntentCompat.getSerializableExtra<Parcel>(intent, "parcel", Parcel::class.java)!!

        binding.parcelUpdateButton.setOnClickListener {
            if (!agentAssigned) {
                agentAssigned = true;
                binding.parcelInfo.recipientName.text = "Recipient Name : " + parcel.recipientName
                binding.parcelInfo.recipientPhone.text =
                    "Phone number : " + parcel.recipientPhoneNumber
                binding.parcelInfo.deliveryAddress.text = "Address : " + parcel.deliveryAddress
                binding.parcelInfo.amountToCollect.text =
                    "Total amount to collect : " + parcel.amountToCollect.toString()

                binding.parcelUpdateButton.text = "Assign to agent sajid for CISD Hub transfer 🚚"

                Toast.makeText(
                    this@PickupReceiveActivity,
                    "Hub Manager Received The Parcel",
                    Toast.LENGTH_LONG
                ).show()
            } else {

            }


        }


    }
}