package com.example.androidpathaoparceljourney.model

import java.io.Serializable

data class Parcel(
    val recipientName: String,
    val recipientPhoneNumber: String,
    val deliveryAddress: String,
    val amountToCollect: Int,
) : Serializable
