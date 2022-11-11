package com.example.samrajyalakshmitemple

import android.Manifest.permission.READ_SMS
import android.Manifest.permission.RECEIVE_SMS
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.samrajyalakshmitemple.databinding.ActivityMainBinding
import com.example.samrajyalakshmitemple.utils.Constants.PUBLISHABLE_KEY_STRIPE_DEBUG
import com.example.samrajyalakshmitemple.utils.SavedPrefManager
import com.stripe.android.PaymentConfiguration


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    open lateinit var savedPrefManager: SavedPrefManager



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        savedPrefManager = SavedPrefManager(this)
        if (ContextCompat.checkSelfPermission(
                this@MainActivity, READ_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(READ_SMS, RECEIVE_SMS),
                101
            )
        }


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        PaymentConfiguration.init(

            applicationContext,

            PUBLISHABLE_KEY_STRIPE_DEBUG

        )




        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.donations,R.id.about,R.id.home,R.id.pooja,R.id.login,R.id.info,R.id.contact),
            binding!!.drawerLayout
        )
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )
        binding!!.navView.setupWithNavController(navController)

        NavigationUI.setupWithNavController(binding!!.navView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp(appBarConfiguration)
    }
}
