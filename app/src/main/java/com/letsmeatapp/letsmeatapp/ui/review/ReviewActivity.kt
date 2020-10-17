package com.letsmeatapp.letsmeatapp.ui.review

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.letsmeatapp.letsmeatapp.R
import com.letsmeatapp.letsmeatapp.data.responses.Restaurant
import com.letsmeatapp.letsmeatapp.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_review.*

class ReviewActivity : AppCompatActivity() {

    private val args: ReviewActivityArgs by navArgs()
    var currentRestaurant: Restaurant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        currentRestaurant = args.myRestaurant
        bottom_nav_view.selectedItemId = R.id.my_home
        bottom_nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.my_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
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