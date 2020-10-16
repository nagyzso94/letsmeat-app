package com.letsmeatapp.letsmeatapp.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.ui.restaurant.RestaurantActivity
import com.letsmeatapp.letsmeatapp.ui.review.MyReviewsActivity
import com.letsmeatapp.letsmeatapp.ui.review.ReviewActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottom_nav_view.selectedItemId = R.id.my_profile
        bottom_nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.my_home -> {
                    startActivity(Intent(this, RestaurantActivity::class.java))
                    overridePendingTransition(0, 0);
                }
                R.id.my_reviews -> {
                    startActivity(Intent(this, MyReviewsActivity::class.java))
                    overridePendingTransition(0, 0);
                }
            }
            true
        }

    }
}