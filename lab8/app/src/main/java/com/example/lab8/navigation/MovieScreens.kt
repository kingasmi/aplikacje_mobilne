package com.example.lab8.navigation

enum class MovieScreens {
    HomeScreen,
    DetailsScreen;
    companion object{
        fun fromRoute(route:String?):MovieScreens=
            when(route?.substringBefore(delimiter = "/"))
            {
                HomeScreen.name -> HomeScreen
                DetailsScreen.name -> DetailsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognize")
            }
    }
}