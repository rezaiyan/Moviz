package ir.alirezaiyan.moviz.data.utils

import android.content.Context

/**
 * Injectable class which returns information about the network connection state.
 */
class NetworkHandler
constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}