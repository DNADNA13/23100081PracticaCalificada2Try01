package dev.equipo.a23100081practicacalificada01.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import dev.equipo.a23100081practicacalificada01.presentation.ApiBaraja.BlackjackApp
import dev.equipo.a23100081practicacalificada01.presentation.ApiBaraja.BlackjackViewModel
import dev.equipo.a23100081practicacalificada01.presentation.ApiBaraja.CreateDeckScreen
import dev.equipo.a23100081practicacalificada01.presentation.ApiBaraja.ResultScreen
import dev.equipo.a23100081practicacalificada01.presentation.ApiBaraja.SelectCardsScreen
import dev.equipo.a23100081practicacalificada01.presentation.ApiPokedex.PokemonDetailScreen
import dev.equipo.a23100081practicacalificada01.presentation.ApiPokedex.PokemonListScreen
import dev.equipo.a23100081practicacalificada01.presentation.Apimovies.ApiMoviesScreen
import dev.equipo.a23100081practicacalificada01.presentation.auth.LoginScreen
import dev.equipo.a23100081practicacalificada01.presentation.auth.RegisterScreen
import dev.equipo.a23100081practicacalificada01.presentation.chat.GeminiChatScreen

import dev.equipo.a23100081practicacalificada01.presentation.home.HomeScreen
import dev.equipo.a23100081practicacalificada01.presentation.permissions.GalleryPermissionScreen

@Composable
fun AppNavGraph (){
    val navController = rememberNavController()
    val viewModel = remember { BlackjackViewModel() }
    val currentUser = FirebaseAuth.getInstance().currentUser
    //NavHost(navController=navController, startDestination="login")
    NavHost(
        navController = navController,
        startDestination = if (currentUser == null) "login" else "home"
    )

    {
        composable ("register"){RegisterScreen(navController)}
        composable ("login"){ LoginScreen(navController)  }

        composable ("home"){
            DrawerScaffold(navController){
                CreateDeckScreen(viewModel) { navController.navigate("BlackJackSelectCards") }
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
        composable ("chat") {
            DrawerScaffold(navController) {
                GeminiChatScreen("AIzaSyAnWwywkft6cdKSgqBywXGvy1KqxW37km0")
            }
        }

        composable("pokemon") {
            PokemonListScreen(navController)
        }

        composable(
            route = "detail/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")!!
            PokemonDetailScreen(name)
        }
        //Baraja
        composable("BlackJackCreateDeck") {
            CreateDeckScreen(viewModel) { navController.navigate("BlackJackSelectCards") }
        }
        composable("BlackJackSelectCards") {
            SelectCardsScreen(viewModel) { navController.navigate("BlackJackResult") }
        }
        composable("BlackJackResult") {
            ResultScreen(viewModel) {
                navController.navigate("BlackJackCreateDeck") { popUpTo("BlackJackCreateDeck") { inclusive = true } }
                viewModel.playerCards = listOf()
                viewModel.dealerScore = 0
            }
        }


    }
}