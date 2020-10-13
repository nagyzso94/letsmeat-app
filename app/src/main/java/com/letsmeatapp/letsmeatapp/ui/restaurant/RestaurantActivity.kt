package com.letsmeatapp.letsmeatapp.ui.restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.ui.profile.ProfileActivity
import com.letsmeatapp.letsmeatapp.ui.review.ReviewActivity
import kotlinx.android.synthetic.main.activity_restaurant.*


class RestaurantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        bottom_nav_view.selectedItemId = R.id.my_home
        bottom_nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.my_home -> true
                R.id.my_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0);
                }
                R.id.my_reviews -> {
                    startActivity(Intent(this, ReviewActivity::class.java))
                    overridePendingTransition(0, 0);
                }
            }
            true
        }
    }

}