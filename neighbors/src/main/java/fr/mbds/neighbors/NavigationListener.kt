package fr.mbds.neighbors

import androidx.annotation.StringRes
import fr.mbds.neighbors.fragments.AddNeighbourFragment
import fr.mbds.neighbors.fragments.ListNeighborsFragment

interface NavigationListener {
    fun showFragment(fragment: AddNeighbourFragment)
    fun showFragment1(fragment: ListNeighborsFragment)
    fun updateTitle(@StringRes title: Int)
}