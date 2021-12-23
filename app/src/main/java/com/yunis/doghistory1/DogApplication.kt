package com.yunis.doghistory1
import android.app.Application


class DogApplication : Application() {
    val database: DogDatabase by lazy { DogDatabase.getDatabase(this) }
}