package com.kishanmaurya.kad

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.kishanmaurya.kad.commons.ItemClickListener
import com.kishanmaurya.kad.news.NewsWebViewFragment

class MainActivity : AppCompatActivity(), ItemClickListener
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        if(savedInstanceState == null)
        {
            changeFragment(MainActivityFragment())
        }
    }

    private fun changeFragment(f: Fragment, cleanStack: Boolean = false)
    {
        val ft = supportFragmentManager.beginTransaction()
        if(cleanStack)
        {
            clearBackStack()
        }

        ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.add(R.id.activity_base_content, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onItemSelected(url: String?)
    {
        val fragment = NewsWebViewFragment.newInstance(url ?: "")
        changeFragment(fragment)
    }

    private fun clearBackStack()
    {

        val manager = supportFragmentManager
        if(manager.backStackEntryCount > 0)
        {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }


}
