/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.OrderUiState
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen

enum class Screen(route: String) {
    Start(route = "StartScreen"),
    EntreeMenu(route = "EntreeMenuScreen"),
    SideDishMenu(route = "SideDishMenuScreen"),
    AccompanimentMenu(route = "AccompanimentMenuScreen"),
    Checkout(route = "CheckoutScreen")

}

// TODO: AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp() {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Start.name
    )


    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = currentScreen.name) },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "NavigateUp"
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Screen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = Screen.Start.name) {
                StartOrderScreen(onStartOrderButtonClicked = { navController.navigate(Screen.EntreeMenu.name) })
            }

            composable(route = Screen.EntreeMenu.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {
                        navController.popBackStack(
                            Screen.Start.name,
                            inclusive = false
                        )
                    },
                    onNextButtonClicked = { navController.navigate(Screen.SideDishMenu.name) },
                    onSelectionChanged = {},
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())
                )
            }

            composable(route = Screen.SideDishMenu.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onNextButtonClicked = { navController.navigate(Screen.AccompanimentMenu.name) },
                    onCancelButtonClicked = {
                        navController.popBackStack(
                            Screen.Start.name,
                            inclusive = false
                        )
                    },
                    onSelectionChanged = {},
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())
                )
            }

            composable(route = Screen.AccompanimentMenu.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onNextButtonClicked = { navController.navigate(Screen.Checkout.name) },
                    onCancelButtonClicked = {
                        navController.popBackStack(
                            Screen.Start.name,
                            inclusive = false
                        )
                    },
                    onSelectionChanged = {},
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())
                )
            }

            composable(route = Screen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = OrderUiState(
                        entree = DataSource.entreeMenuItems[0],
                        sideDish = DataSource.sideDishMenuItems[0],
                        accompaniment = DataSource.accompanimentMenuItems[0],
                        itemTotalPrice = 15.00,
                        orderTax = 1.00,
                        orderTotalPrice = 16.00
                    ),
                    onNextButtonClicked = { navController.navigate(Screen.Start.name) },
                    onCancelButtonClicked = {
                        navController.popBackStack(
                            Screen.Start.name,
                            inclusive = false
                        )
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())
                )
            }

        }
    }
}
