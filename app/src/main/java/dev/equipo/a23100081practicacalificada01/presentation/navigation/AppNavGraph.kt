package dev.equipo.a23100081practicacalificada01.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import dev.equipo.a23100081practicacalificada01.presentation.Apimovies.ApiMoviesScreen
import dev.equipo.a23100081practicacalificada01.presentation.auth.LoginScreen
import dev.equipo.a23100081practicacalificada01.presentation.auth.RegisterScreen

import dev.equipo.a23100081practicacalificada01.presentation.home.HomeScreen
import dev.equipo.a23100081practicacalificada01.presentation.permissions.GalleryPermissionScreen

@Composable
fun AppNavGraph (){
    val navController = rememberNavController()
    val currentUser = FirebaseAuth.getInstance().currentUser
    //NavHost(navController=navController, startDestination="login")
    NavHost(
        navController = navController,
        startDestination = "movies"//if (currentUser == null) "login" else "home"
    )

    {
        composable ("register"){RegisterScreen(navController)}
        composable ("login"){ LoginScreen(navController)  }

        composable ("home"){
            DrawerScaffold(navController){
                HomeScreen()
            }
        }
        composable ("premissions"){
            DrawerScaffold(navController) {
                GalleryPermissionScreen()
            }
        }
        composable("favorites") {
            DrawerScaffold(navController) {
                Text("Proximamente")
            }
        }
        composable ("movies") {
            DrawerScaffold(navController) {
                ApiMoviesScreen()
            }
        }
    }
}