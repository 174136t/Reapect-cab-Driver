package com.nexeyotest.respectcab
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class FirebaseHelper constructor(driverId: String) {

    companion object {
        private const val ONLINE_DRIVERS = "OnlineDrivers"
        private const val ONLINE_HIRING_DRIVERS = "OnlineHiringDrivers"
        private const val PUSH_TOKENS = "PushTokens"

    }

    private val onlineDriverDatabaseReference: DatabaseReference = FirebaseDatabase
            .getInstance()
            .reference
            .child(ONLINE_DRIVERS)
            .child(driverId)

    private val onlineHiringDriverDatabaseReference: DatabaseReference = FirebaseDatabase
            .getInstance()
            .reference
            .child(ONLINE_HIRING_DRIVERS)
            .child(driverId)

    private val pushNotifications: DatabaseReference = FirebaseDatabase
            .getInstance()
            .reference
            .child(PUSH_TOKENS)
            .child(driverId)

    init {
        onlineDriverDatabaseReference
                .onDisconnect()
                .removeValue()

        onlineHiringDriverDatabaseReference
                .onDisconnect()
                .removeValue()

        pushNotifications
                .onDisconnect()
                .removeValue()
    }

    fun updateDriver(driver: Driver) {
        onlineDriverDatabaseReference
                .setValue(driver)
        Log.e("Driver Info", " Updated")
    }

    fun updateHiringDriver(driver: Driver) {
        onlineHiringDriverDatabaseReference
                .setValue(driver)
        Log.e("Driver Info", " Updated")
    }

    fun updateTokens(devtoken: Token) {
        pushNotifications
                .setValue(devtoken)
        Log.e("Driver Token Info", " Token added successfully")
    }

    fun deleteDriver() {
        onlineDriverDatabaseReference
                .removeValue()
    }
    fun deleteHiringDriver() {
        onlineHiringDriverDatabaseReference
                .removeValue()
    }

    fun deletePushToken(){
        pushNotifications
                .removeValue()
    }
}
