package com.example.androidpathaoparceljourney.ui.merchant

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.androidpathaoparceljourney.R
import com.example.androidpathaoparceljourney.databinding.ActivityMerchantHomeBinding
import com.example.androidpathaoparceljourney.model.Parcel
import com.example.androidpathaoparceljourney.ui.hub.PickupHubActivity

class MerchantHomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityMerchantHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMerchantHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val requiredInputList = listOf(
            binding.recipientName,
            binding.recipientPhone,
            binding.deliveryAddress,
            binding.amountToCollect
        )

        fun updateSubmitButton() {
            val isComplete = requiredInputList.all { input ->
                !input.text.isNullOrBlank()
            }

            binding.submitButton.apply {
                isEnabled = isComplete
                alpha = if (isComplete) 1f else 0.23f
            }
        }

        binding.submitButton.apply {
            alpha = 0.23f
            isEnabled = false
        }

        for (input in requiredInputList) {
            input.doAfterTextChanged {
                updateSubmitButton()
            }
        }

        binding.submitButton.setOnClickListener {
            startActivity(Intent(this@MerchantHomeActivity, PickupHubActivity::class.java).apply {
                putExtra(
                    "parcel",
                    Parcel(
                        recipientName = binding.recipientName.text.trim().toString(),
                        recipientPhoneNumber = binding.recipientPhone.text.trim().toString(),
                        deliveryAddress = binding.deliveryAddress.text.trim().toString(),
                        amountToCollect = binding.amountToCollect.text.toString().toInt()
                    ),
                )
            })
            finish()
        }

    }
}