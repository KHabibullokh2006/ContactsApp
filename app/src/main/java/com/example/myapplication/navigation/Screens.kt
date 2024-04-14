package com.example.myapplication.navigation

sealed class Screens(val route:String) {
    object Home : Screens("HomeView")
    object Add : Screens("AddView/{id}")
}