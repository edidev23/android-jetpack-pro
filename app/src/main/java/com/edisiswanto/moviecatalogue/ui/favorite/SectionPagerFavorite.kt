package com.edisiswanto.moviecatalogue.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.ui.favorite.movie.MovieFavoriteFragment
import com.edisiswanto.moviecatalogue.ui.favorite.tv.TvFavoriteFragment
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment
import com.edisiswanto.moviecatalogue.ui.tv.TvFragment

class SectionPagerFavorite(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFavoriteFragment()
            1 -> TvFavoriteFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size
}