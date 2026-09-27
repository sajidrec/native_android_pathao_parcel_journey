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
import com.example.androidpathaoparceljourney.databinding.ActivityLmhactivityBinding
import com.example.androidpathaoparceljourney.model.Parcel
import com.example.androidpathaoparceljourney.ui.delivery.ParcelReceiverActivity

class LMHActivity : AppCompatActivity() {
    lateinit var binding : ActivityLmhactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLmhactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val parcel: Parcel =
            IntentCompat.getSerializableExtra<Parcel>(intent, "parcel", Parcel::class.java)!!

        var agentAssigned: Boolean = false

        binding.parcelUpdateButton.setOnClickListener {
            if (!agentAssigned) {
                agentAssigned = true
                binding.parcelInfo.recipientName.text = "Recipient Name : " + parcel.recipientName
                binding.parcelInfo.recipientPhone.text =
                    "Phone number : " + parcel.recipientPhoneNumber
                binding.parcelInfo.deliveryAddress.text = "Address : " + parcel.deliveryAddress
                binding.parcelInfo.amountToCollect.text =
                    "Total amount to collect : " + parcel.amountToCollect.toString()

                binding.parcelUpdateButton.text = "Assign to agent sajid for Delivery 🚚"

                Toast.makeText(
                    this@LMHActivity,
                    "Hub Manager Received The Parcel",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this@LMHActivity,
                    "Assigned to sajid and he is taking the parcel for delivery",
                    Toast.LENGTH_LONG
                ).show()

                startActivity(Intent(this@LMHActivity, ParcelReceiverActivity::class.java).apply {
                    putExtra("parcel", parcel)
                })
                finish()
            }


        }
    }
}