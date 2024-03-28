package com.suslanium.hackathon.core.navigation

import com.suslanium.hackathon.R

object BottomNavItems {

    val items = listOf(
        BottomNavItem(
            labelId = R.string.statements,
            iconId = R.drawable.ic_list,
            route = RoadCareDestinations.STATEMENTS
        ),
        BottomNavItem(
            labelId = R.string.map,
            iconId = R.drawable.ic_map,
            route = RoadCareDestinations.MAP
        ),
        BottomNavItem(
            labelId = R.string.profile,
            iconId = R.drawable.ic_profile,
            route = RoadCareDestinations.PROFILE
        )
    )

}