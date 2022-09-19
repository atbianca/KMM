package com.example.kotlinmultiplatform

import com.example.kotlinmultiplatform.cache.Database
import com.example.kotlinmultiplatform.cache.DatabaseDriverFactory
import com.example.kotlinmultiplatform.entity.RocketLaunch
import com.example.kotlinmultiplatform.network.SpaceXApi

class SpaceXSDK (databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

    @Throws(Exception::class) suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}
