package com.cvs.worldquiz

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.squareup.leakcanary.LeakCanary
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.concurrent.TimeUnit

class WorldQuizApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        // Normal app init code...

        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
                .name("WorldQuiz.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build()
        Realm.setDefaultConfiguration(configuration)

        Fresco.initialize(this)
    }

}