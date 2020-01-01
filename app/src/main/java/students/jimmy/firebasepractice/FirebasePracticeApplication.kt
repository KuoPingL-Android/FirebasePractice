package students.jimmy.firebasepractice

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class FirebasePracticeApplication: Application() {
    companion object {
        var instance: FirebasePracticeApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}