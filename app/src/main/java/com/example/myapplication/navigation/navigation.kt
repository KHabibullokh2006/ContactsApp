package com.example.myapplication.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.database.AppDataBase
import com.example.myapplication.screens.addEdit.AddEditModel
import com.example.myapplication.screens.addEdit.AddEditView
import com.example.myapplication.screens.addEdit.AddEditViewModel
import com.example.myapplication.screens.home.HomeModel
import com.example.myapplication.screens.home.HomeView
import com.example.myapplication.screens.home.HomeViewModel

@Composable
fun navigation(navController: NavHostController, context: Context) {
    val appDataBase = AppDataBase.getInstance(context)
    val appDao = appDataBase.getDao()
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        val hm = HomeModel(appDao)
        val hvm = HomeViewModel(navController, hm)
        composable(Screens.Home.route) {
            HomeView(hvm)
        }

        val aem = AddEditModel(appDao)
        composable(Screens.Add.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) {
            val id = it.arguments?.getInt("id")!!
            val avm = AddEditViewModel(navController, id, aem)
            AddEditView(avm)
        }
    }
}