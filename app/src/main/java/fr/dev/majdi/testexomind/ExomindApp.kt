package fr.dev.majdi.testexomind

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import de.hdodenhof.circleimageview.BuildConfig
import fr.dev.majdi.testexomind.module.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

import timber.log.Timber.DebugTree


/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
class ExomindApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ExomindApp)
            modules(listOf(mainModule))
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    /** A tree which logs important information for crash reporting.  */
    private class CrashReportingTree : Timber.Tree() {
        override fun log(
            priority: Int,
            tag: String?,
            @NonNull message: String,
            t: Throwable?
        ) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

            if (t != null) {
                if (priority == Log.ERROR) {
                    //Send crashlytics report
                } else if (priority == Log.WARN) {
                    //Send crashlytics report
                }
            }
        }
    }

}