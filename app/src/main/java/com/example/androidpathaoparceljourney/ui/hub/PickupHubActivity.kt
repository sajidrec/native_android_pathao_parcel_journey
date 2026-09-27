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
import com.example.androidpathaoparceljourney.databinding.ActivityPickupHubBinding
import com.example.androidpathaoparceljourney.model.Parcel
import com.example.androidpathaoparceljourney.ui.agent.PickupAgentActivity
import kotlinx.coroutines.delay

class PickupHubActivity : AppCompatActivity() {
    lateinit var binding: ActivityPickupHubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPickupHubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val parcel: Parcel =
            IntentCompat.getSerializableExtra<Parcel>(intent, "parcel", Parcel::class.java)!!

        binding.parcelInfo.recipientName.text = "Recipient Name : " + parcel.recipientName
        binding.parcelInfo.recipientPhone.text = "Phone number : " + parcel.recipientPhoneNumber
        binding.parcelInfo.deliveryAddress.text = "Address : " + parcel.deliveryAddress
        binding.parcelInfo.amountToCollect.text =
            "Totaal amount to collect : " + parcel.amountToCollect.toString()

        binding.assignPickupAgentButton.setOnClickListener {
            Toast.makeText(
                PickupHubActivity@ this,
                "Assigned to sajid going to pickup agent screen",
                Toast.LENGTH_LONG
            ).show()

            startActivity(Intent(this@PickupHubActivity, PickupAgentActivity::class.java).apply {
                putExtra("parcel", parcel)
            })
            finish()

        }


    }
}