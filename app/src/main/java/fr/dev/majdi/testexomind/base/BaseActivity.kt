package fr.dev.majdi.testexomind.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.dev.majdi.testexomind.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.no_data
import kotlinx.android.synthetic.main.activity_main.recycler
import kotlinx.android.synthetic.main.activity_picture.*

/**
 * Created by Majdi RABEH on 12/08/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@Suppress("DEPRECATION")
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setStatusBarColor(resources.getColor(R.color.colorPrimary))

    }

    fun setStatusBarColor(color: Int) {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }

    private fun overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    fun overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }


    public fun setupToolbar(toolbar: Toolbar, colorBackButton: Int) {
        setSupportActionBar(toolbar)
        // add back arrow to toolbar
        if (supportActionBar != null) {
            val upArrow = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_black_24dp)
            upArrow!!.setTint(resources.getColor(colorBackButton))
            supportActionBar!!.setHomeAsUpIndicator(upArrow)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(false)
        }
    }

    fun openActivity(currentActivity: Activity, activity: Class<*>, bundle: Bundle?) {
        val intent = Intent(currentActivity, activity)
        if (bundle != null)
            intent.putExtras(bundle)

        startActivity(intent)
        overridePendingTransitionEnter()
    }

    fun initRecycle(recycler: RecyclerView) {
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    fun visibilityRecycle(setVisible: Boolean, recycler: RecyclerView, no_data: TextView) {
        if (setVisible) {
            recycler.visibility = View.VISIBLE
            no_data.visibility = View.GONE
        } else {
            recycler.visibility = View.GONE
            no_data.visibility = View.VISIBLE
        }
    }

}