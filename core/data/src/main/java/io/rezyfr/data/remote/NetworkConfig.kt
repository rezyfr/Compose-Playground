package io.rezyfr.data.remote

abstract class NetworkConfig {
    abstract fun baseUrl(): String
    abstract fun imageUrl(): String
    abstract fun backdropUrl(): String
    abstract fun timeOut(): Long

    open fun isDev() = false
}