package io.rezyfr.muviplayground.provider

import io.rezyfr.data.remote.NetworkConfig
import io.rezyfr.muviplayground.BuildConfig

class AppNetworkProvider: NetworkConfig() {
    override fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }
    override fun imageUrl(): String {
        return BuildConfig.BASE_IMG_URL
    }
    override fun backdropUrl(): String {
        return BuildConfig.BASE_BACKDROP_URL
    }

    override fun timeOut(): Long {
        return 30L
    }
}