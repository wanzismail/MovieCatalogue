package com.wanztudio.idcamp.moviecatalogue.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class ViewPagerAdapter(
    private val context: Context, fm: FragmentManager, private val fragments: List<Fragment>,
    private val titles: List<Int> = arrayListOf()
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    // This determines the fragment for each tab
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    // This determines the number of tabs
    override fun getCount(): Int {
        return fragments.size
    }

    // This determines the title for each tab
    override fun getPageTitle(position: Int): CharSequence? {
        // Generate title based on item position
        return if (titles.isNotEmpty()) context.getString(titles[position]).toUpperCase(Locale.getDefault()) else ""
    }

}