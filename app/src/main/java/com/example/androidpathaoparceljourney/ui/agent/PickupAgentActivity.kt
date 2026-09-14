package com.example.androidpathaoparceljourney.ui.agent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getSerializableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidpathaoparceljourney.R
import com.example.androidpathaoparceljourney.databinding.ActivityPickupAgentBinding
import com.example.androidpathaoparceljourney.databinding.ActivityPickupAgentBinding.*
import com.example.androidpathaoparceljourney.model.Parcel
import com.example.androidpathaoparceljourney.ui.hub.PickupReceiveActivity
import kotlin.jvm.java

class PickupAgentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPickupAgentBinding
    var state: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val parcel = getSerializableExtra(intent, "parcel", Parcel::class.java)




        binding.parcelToNextButton.setOnClickListener {
            when (state) {
                0 -> {
                    binding.parcelStatusText.visibility = View.VISIBLE

                    binding.parcelInfo.recipientName.text = "Recipient Name : " + parcel?.recipientName
                    binding.parcelInfo.recipientPhone.text = "Phone number : " + parcel?.recipientPhoneNumber
                    binding.parcelInfo.deliveryAddress.text = "Address : " + parcel?.deliveryAddress
                    binding.parcelInfo.amountToCollect.text =
                        "Total amount to collect : " + parcel?.amountToCollect.toString()

                    binding.parcelToNextButton.text = "Handed to hub manager"
                    Toast.makeText(
                        this@PickupAgentActivity,
                        "Parcel picked and going to hub for handover",
                        Toast.LENGTH_LONG
                    ).show()
                }

                1 -> {
                    Toast.makeText(
                        this@PickupAgentActivity, "Handed over to hub manager", Toast.LENGTH_LONG
                    ).show()

                    startActivity(
                        Intent(
                            this@PickupAgentActivity, PickupReceiveActivity::class.java
                        ).apply {
                            putExtra("parcel", parcel)
                        })
                    finish()

                }

            }
            this.state++
        }

    }
}