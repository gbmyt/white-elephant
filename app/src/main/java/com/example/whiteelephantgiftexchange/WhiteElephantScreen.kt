package com.example.whiteelephantgiftexchange

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.whiteelephantgiftexchange.ui.GameViewModel
import com.example.whiteelephantgiftexchange.ui.screens.GameScreen
import com.example.whiteelephantgiftexchange.ui.screens.PlayersScreen
import com.example.whiteelephantgiftexchange.ui.screens.RulesScreen
import com.example.whiteelephantgiftexchange.ui.theme.WhiteElephantGiftExchangeTheme

enum class WhiteElephantScreen(@StringRes val title: Int) {
    Images(title = R.string.app_name),
    Rules(title = R.string.game_rules),
    Players(title = R.string.players),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhiteElephantGiftExchangeAppBar(
    currentScreen: WhiteElephantScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhiteElephantGiftExchangeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    gameViewModel: GameViewModel = viewModel(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = WhiteElephantScreen.valueOf(
        backStackEntry?.destination?.route ?: WhiteElephantScreen.Images.name
    )

    Scaffold(
        topBar = {
            WhiteElephantGiftExchangeAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = WhiteElephantScreen.Images.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = WhiteElephantScreen.Images.name) {
                GameScreen(
                    gameViewModel = gameViewModel,
                    onRulesButtonClicked = { navController.navigate(WhiteElephantScreen.Rules.name) },
                    onPlayerButtonClicked = { navController.navigate(WhiteElephantScreen.Players.name) },
                )
            }

            composable(route = WhiteElephantScreen.Rules.name) {
                RulesScreen()
            }

            composable(route = WhiteElephantScreen.Players.name) {
                PlayersScreen(gameViewModel = gameViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WhiteElephantAppPreview() {
    WhiteElephantGiftExchangeTheme {
        WhiteElephantGiftExchangeApp()
    }
}