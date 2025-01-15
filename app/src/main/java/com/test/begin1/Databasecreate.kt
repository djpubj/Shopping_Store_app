package com.test.begin1

import android.app.Application
import com.test.begin1.roomdatabase.Graph

class Databasecreate : Application() {

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}