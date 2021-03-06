package com.letsmeatapp.letsmeatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.letsmeatapp.letsmeatapp.data.UserPreferences
import com.letsmeatapp.letsmeatapp.ui.auth.AuthActivity
import com.letsmeatapp.letsmeatapp.ui.restaurant.RestaurantActivity
import com.letsmeatapp.letsmeatapp.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if (it == null) AuthActivity::class.java else RestaurantActivity::class.java
            startNewActivity(activity)
        })

    }

}