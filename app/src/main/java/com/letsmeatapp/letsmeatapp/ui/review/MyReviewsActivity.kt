package com.letsmeatapp.letsmeatapp.ui.review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.ui.profile.ProfileActivity
import com.letsmeatapp.letsmeatapp.ui.restaurant.RestaurantActivity
import kotlinx.android.synthetic.main.activity_review.*

class MyReviewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_reviews)

        bottom_nav_view.selectedItemId = R.id.my_reviews
        bottom_nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.my_home -> {
                    startActivity(Intent(this, RestaurantActivity::class.java))
                    overridePendingTransition(0, 0);
                }
                R.id.my_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0);
                }
            }
            true
        }
    }
}