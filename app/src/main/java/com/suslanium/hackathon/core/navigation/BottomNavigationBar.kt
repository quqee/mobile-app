package com.suslanium.hackathon.core.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.Primary

@Composable
fun BottomNavigationBar(
    buttons: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier.shadow(elevation = 16.dp),
        containerColor = Color.White,
        contentColor = DarkBlue,
    ) {
        buttons.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.iconId),
                        contentDescription = stringResource(id = item.labelId),
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    indicatorColor = Color.White,
                    unselectedIconColor = DarkBlue,
                ),
            )
        }
    }
}