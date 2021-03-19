package ulp.pt.yourspot.fragments

import android.R.attr.fragment
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ulp.pt.yourspot.R


const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // Variaveis

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Binding
        drawerLayout = findViewById(R.id.drawer_layout_main_activity)
        navigationView = findViewById(R.id.hamburger_menu_yourspot)
        toolbar = findViewById(R.id.toolbar_am)

        // Tool Bar
        setSupportActionBar(toolbar)

        // Navigation Drawer
        //var toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        navigationView.bringToFront()
        var toggle_2 = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar_am,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle_2.isDrawerIndicatorEnabled = false
        val drawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.hamburger_icon_drawer,
            applicationContext.theme
        )
        toggle_2.setHomeAsUpIndicator(drawable)
        toggle_2.toolbarNavigationClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
            }
        }
        //drawerLayout.addDrawerListener(toggle_2)
        toggle_2.syncState()

        navigationView.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        /*val perfil = PerfilFragment()
        //val bundle = Bundle()
        //bundle.putLong("ID_Utilizador", 1 )
        perfil.arguments = bundle*/
        when (item.itemId){
            R.id.perfil_nav -> {
                fragmentTransaction.replace(R.id.drawer_layout_main_activity, PerfilFragment()).commit()
                Log.d("TAG", "message_teste_04_03_2021")
            }
        //R.id.config_nav ->
        //R.id.suporte_nav ->
        }
        return true
    }

}