package com.example.buynow_te_e_comerce_app.views

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.buynow_te_e_comerce_app.R
import com.example.buynow_te_e_comerce_app.views.fragment.*
import com.example.buynow_te_e_comerce_app.databinding.ActivityHomeBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.navigation.NavigationBarView

class activity_home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var badge_notifiction: BadgeDrawable
    lateinit var badge_note: BadgeDrawable



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        badge_notifiction = binding.bottomNavMenu.getOrCreateBadge(R.id.bagMenu)
        badge_notifiction.backgroundColor = getColor(R.color.primary)
        badge_notifiction.maxCharacterCount = 5
        badge_notifiction.number = 100
        badge_notifiction.setVisible(true)
        setupClickListener()
        if (savedInstanceState == null) {
        loadFragment(HomeFragment())
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_fragment, fragment)
            .commit()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupClickListener() {
        binding.bottomNavMenu.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.shopMenu -> {
                    ShopingFragment()
                }
                R.id.profileMenu -> {
                    ProfFragment()
                }
                R.id.favMenu -> {
                    FavFragment()
                }
                R.id.bagMenu -> {
                    badge_note   = binding.bottomNavMenu.getBadge(R.id.bagMenu)!!
                    badge_notifiction.backgroundColor = getColor(R.color.primary)
                    badge_notifiction.clearNumber()
                    badge_notifiction.setVisible(false)
                    BagsFragment()
                }
                else -> {
                    HomeFragment()
                }
            }
            loadFragment(fragment)
            true

        })
    }


}