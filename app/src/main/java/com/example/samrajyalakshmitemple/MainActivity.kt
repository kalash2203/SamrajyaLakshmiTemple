package com.example.samrajyalakshmitemple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.samrajyalakshmitemple.databinding.ActivityMainBinding
import com.example.samrajyalakshmitemple.utils.Constants
import com.example.samrajyalakshmitemple.utils.Constants.PUBLISHABLE_KEY_STRIPE_DEBUG
import com.example.samrajyalakshmitemple.utils.SavedPrefManager
import com.stripe.android.PaymentConfiguration
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.samrajyalakshmitemple.ui.fragment.HomeFragment
import com.example.samrajyalakshmitemple.ui.fragment.LoginFragment
import com.example.samrajyalakshmitemple.utils.Constants.USER
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    open lateinit var savedPrefManager: SavedPrefManager



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        savedPrefManager = SavedPrefManager(this)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        PaymentConfiguration.init(applicationContext, PUBLISHABLE_KEY_STRIPE_DEBUG)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.donations,R.id.about,R.id.home,R.id.pooja,R.id.login,R.id.info,R.id.contact,R.id.my_profile,R.id.user_panel
            ,R.id.donation_record,R.id.my_donation),
            binding!!.drawerLayout
        )
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )
        binding!!.navView.setupWithNavController(navController)

        NavigationUI.setupWithNavController(binding!!.navView, navController)

        hideItem()
        setLoginLogoutBtn()
    }

    private fun hideItem() {
        if (!savedPrefManager.isLogin()) {
            binding?.navView?.menu?.findItem(R.id.dashboard)?.isVisible = false
            if (savedPrefManager.checkRole()== Constants.USER){
                binding?.navView?.menu?.findItem(R.id.donation_record)?.isVisible = false
                binding?.navView?.menu?.findItem(R.id.user_panel)?.isVisible = false
            }else {
                binding?.navView?.menu?.findItem(R.id.donation_record)?.isVisible = true
                binding?.navView?.menu?.findItem(R.id.user_panel)?.isVisible = true
            }
        }
    }

    private fun setLoginLogoutBtn(){
        if (savedPrefManager.isLogin()) {
                 binding?.loginSignoutBtn?.text="Sign Out"
        }else{
                binding?.loginSignoutBtn?.text="Login"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp(appBarConfiguration)
    }
}
