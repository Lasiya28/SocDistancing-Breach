package com.lasiya.opentracesdk_fat

import com.lasiya.opentracesdk_fat.alarm.BLEClient
import com.lasiya.opentracesdk_fat.alarm.BLEServer
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * An example of how to start the SDK in autoboot on Android.
 *
 */
class AutoStartExample : BroadcastReceiver() {
    private val mBleServer : BLEServer =
        BLEServer()
    private val mBleClient : BLEClient =
        BLEClient()

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            BLETrace.init(context.applicationContext)
            BLETrace.start(true)
        }
    }
}