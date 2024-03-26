package com.example.dicerollerjc.screens

sealed class Screens(val screen: String) {
    data object Home : Screens("home")
    data object Profile : Screens("profile")
    data object Settings : Screens("settings")
    data object Search : Screens("search")
    data object Post: Screens("post")
    data object Story: Screens("story")
    data object Reel: Screens("reel")
    data object Live: Screens("live")
    data object AllMoves: Screens("allMoves")
}