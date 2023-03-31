package com.dentons.raidapp.presentation

import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dentons.raidapp.R
import com.dentons.raidapp.presentation.bottomsheet.RaidedViewBottomSheet
import com.dentons.raidapp.presentation.base.SIDE_EFFECTS_KEY
import com.dentons.raidapp.presentation.dos.DosScreen
import com.dentons.raidapp.presentation.home.HomeScreen
import com.dentons.raidapp.presentation.lawyers.LawyersScreen
import com.dentons.raidapp.presentation.more.MoreScreen
import com.dentons.raidapp.ui.custom.CustomBottomNavigationItem
import com.dentons.raidapp.ui.noRippleClickable
import com.dentons.raidapp.ui.theme.DARK_GREY
import com.dentons.raidapp.ui.theme.PURPLE_MAIN
import com.dentons.raidapp.ui.theme.RED
import com.dentons.raidapp.ui.theme.RaidAppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    state: MainViewModel.State,
    effectFlow: Flow<MainViewModel.Effect>?,
    onEventSent: (event: MainViewModel.Event) -> Unit
) {

    val navController = rememberNavController()
    val navItems = listOf(
        BottomTabs.Home,
        BottomTabs.Dos,
        BottomTabs.Lawyers,
        BottomTabs.More
    )

    Scaffold(
        floatingActionButton = {
            RaidAlarmFab(
                onEventSent,
                navController
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier.noRippleClickable(),
        isFloatingActionButtonDocked = true,
        bottomBar = { BottomNavigationBar(navController, navItems) },
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding))
        MainScreenNavigationConfigurations(navController)
    }

    val modalBottomSheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    RaidedViewBottomSheet(modalBottomSheetState, onEventSent)

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is MainViewModel.Effect.ShowCallAlert -> {
                    coroutineScope.launch {
                        modalBottomSheetState.show()
                    }
                }
                is MainViewModel.Effect.DialNumber -> {
                    context.startActivity(Intent(Intent.ACTION_DIAL).also {
                        it.data = Uri.parse("tel:${state.phoneNumber}")
                    })
                }
                else -> {}
            }
        }?.collect()
    }
}

@Composable
fun RaidAlarmFab(
    onEventSent: (event: MainViewModel.Event) -> Unit,
    navController: NavHostController
) {
    FloatingActionButton(
        backgroundColor = RED,
        onClick = {
            onEventSent(
                if (navController.currentDestination?.route == BottomTabs.Home.route)
                    MainViewModel.Event.MakeCall
                else
                    MainViewModel.Event.OnAlertClicked
            )
        }) {
        Icon(
            painter = painterResource(R.drawable.ic_alarm_siren),
            contentDescription = ""
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, bottomNavigationItems: List<BottomTabs>) {
    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 75)),
        contentPadding = PaddingValues(end = 80.dp)
    ) {
        BottomNavigation(
            elevation = 0.dp,
        ) {
            val currentRoute = currentRoute(navController)
            bottomNavigationItems.forEach { screen ->
                CustomBottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(screen.iconId),
                            contentDescription = screen.route
                        )
                    },
                    label = {
                        Text(

                            text = stringResource(id = screen.titleResId),
                            softWrap = false,
                            overflow = TextOverflow.Visible
                        )
                    },
                    selected = currentRoute == screen.route,
                    selectedContentColor = PURPLE_MAIN,
                    unselectedContentColor = DARK_GREY,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route)
                        }
                    }
                )
            }
        }
    }


}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomTabs.Home.route) {
        composable(BottomTabs.Home.route) {
            HomeScreen()
        }
        composable(BottomTabs.Dos.route) {
            DosScreen()
        }
        composable(BottomTabs.Lawyers.route) {
            LawyersScreen()
        }
        composable(BottomTabs.More.route) {
           MoreScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    RaidAppTheme {
        MainScreen(
            state = MainViewModel.State(
                phoneNumber = "",
                isAlertBottomSheetShown = false,
                isLoading = false,
                isError = false,
            ),
            effectFlow = null,
            onEventSent = {},
        )
    }
}

sealed class BottomTabs(val route: String, @StringRes val titleResId: Int, val iconId: Int) {
    object Home : BottomTabs("home", R.string.nav_tab_home, R.drawable.ic_home)
    object Dos : BottomTabs("dos", R.string.nav_tab_do_donts, R.drawable.ic_document)
    object Lawyers : BottomTabs("lawyers", R.string.nav_tab_lawyers, R.drawable.ic_briefcase)
    object More : BottomTabs("more", R.string.nav_tab_more, R.drawable.ic_more)
}