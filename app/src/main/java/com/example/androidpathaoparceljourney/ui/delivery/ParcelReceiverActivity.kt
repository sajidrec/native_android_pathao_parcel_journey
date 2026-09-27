package com.example.androidpathaoparceljourney.ui.delivery

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.androidpathaoparceljourney.R
import com.example.androidpathaoparceljourney.databinding.ActivityParcelReceiverBinding
import com.example.androidpathaoparceljourney.model.Parcel

class ParcelReceiverActivity : AppCompatActivity() {

    lateinit var binding: ActivityParcelReceiverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityParcelReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val parcel: Parcel =
            IntentCompat.getSerializableExtra<Parcel>(intent, "parcel", Parcel::class.java)!!

        var otpVerified: Boolean = false
        var otpVerificationForPartialPayment: Boolean = false

        binding.takeTheParcelButton.setOnClickListener {
            if (!otpVerified) {
                binding.takeTheParcelButton.text = "Verify otp"
            } else {
                binding.returnTheParcelButton.isVisible = false
                binding.partialPayForTheParcelButton.isVisible = false
                binding.takeTheParcelButton.isVisible = false

                binding.parcelInfo.recipientName.text = "Recipient Name : " + parcel.recipientName
                binding.parcelInfo.recipientPhone.text =
                    "Phone number : " + parcel.recipientPhoneNumber
                binding.parcelInfo.deliveryAddress.text = "Address : " + parcel.deliveryAddress
                binding.parcelInfo.amountToCollect.text =
                    "Total amount to collect : " + parcel.amountToCollect.toString()

                binding.parcelStatusText.isVisible = true
                binding.parcelStatusText.text = "Parcel taken"

                Toast.makeText(
                    this@ParcelReceiverActivity,
                    "Finally the parcel was delivered",
                    Toast.LENGTH_SHORT
                ).show()
            }
            otpVerified = true;
        }
        binding.returnTheParcelButton.setOnClickListener {
            binding.returnTheParcelButton.isVisible = false
            binding.partialPayForTheParcelButton.isVisible = false
            binding.takeTheParcelButton.isVisible = false

            binding.parcelInfo.root.isVisible = false

            binding.parcelStatusText.isVisible = true
            binding.parcelStatusText.text = "Parcel returned"

            Toast.makeText(
                this@ParcelReceiverActivity,
                "The parcel was returned",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.partialPayForTheParcelButton.setOnClickListener {
            if (!otpVerificationForPartialPayment) {
                binding.partialPayForTheParcelButton.text = "Verify otp"
            } else {
                binding.returnTheParcelButton.isVisible = false
                binding.partialPayForTheParcelButton.isVisible = false
                binding.takeTheParcelButton.isVisible = false

                binding.parcelInfo.recipientName.text = "Recipient Name : " + parcel.recipientName
                binding.parcelInfo.recipientPhone.text =
                    "Phone number : " + parcel.recipientPhoneNumber
                binding.parcelInfo.deliveryAddress.text = "Address : " + parcel.deliveryAddress
                binding.parcelInfo.amountToCollect.text =
                    "Total amount to collect : " + parcel.amountToCollect.toString()

                binding.parcelStatusText.isVisible = true
                binding.parcelStatusText.text = "Parcel partially paid"

                Toast.makeText(
                    this@ParcelReceiverActivity,
                    "Finally the parcel was delivered with partial payment",
                    Toast.LENGTH_SHORT
                ).show()
            }
            otpVerificationForPartialPayment = true;
        }

    }
}