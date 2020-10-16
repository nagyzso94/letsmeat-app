package com.letsmeatapp.letsmeatapp.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.letsmeatapp.letsmeatapp.data.network.Resource
import com.letsmeatapp.letsmeatapp.ui.auth.LoginFragment
import com.letsmeatapp.letsmeatapp.ui.auth.RegisterFragment
import com.letsmeatapp.letsmeatapp.ui.base.BaseFragment
import com.letsmeatapp.letsmeatapp.ui.restaurant.RestaurantAddFragment

fun <A: Activity> Activity.startNewActivity(activity: Class<A>){
    overridePendingTransition(0, 0);
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}


fun View.visible(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean){
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null){
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
){
    when{
        failure.isNetworkError -> requireView().snackbar("Ellenőrizd az internetkapcsolatot!", retry)
        failure.errCode == 401 ->
            if (this is LoginFragment){
                requireView().snackbar("Helytelen email és/vagy jelszó!")
            } else if (this is RegisterFragment) {
                requireView().snackbar("Helytelen név, email és/vagy jelszó!")
            } else if (this is RestaurantAddFragment){
                    requireView().snackbar("Helytelen adatok!")
            } else{
                (this as BaseFragment<*,*,*>).logout()
            }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}