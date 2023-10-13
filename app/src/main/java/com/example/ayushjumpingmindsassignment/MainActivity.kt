package com.example.ayushjumpingmindsassignment


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.example.ayushjumpingmindsassignment.adapter.FragmentPageAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    lateinit var  pageAdapter: FragmentPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking {
            installSplashScreen()
            delay(3000)
        }
        setContentView(R.layout.activity_main)


        var tabLayout=   findViewById<TabLayout>(R.id.tabLayout)
        var viewPager2=  findViewById<ViewPager2>(R.id.viewPager)

        pageAdapter = FragmentPageAdapter(supportFragmentManager,lifecycle)


        tabLayout.addTab(tabLayout.newTab().setText("Feed").setIcon(R.drawable.baseline_emoji_emotions_24))
        tabLayout.addTab(tabLayout.newTab().setText("Favourite").setIcon(R.drawable.baseline_favorite_24))




        viewPager2.adapter = pageAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })






   }


}


