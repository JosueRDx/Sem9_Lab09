package com.josuerdx.lab09.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.josuerdx.lab09.ui.components.BarraInferior
import com.josuerdx.lab09.ui.components.BarraSuperior
import com.josuerdx.lab09.ui.components.Contenido
import com.josuerdx.network.PostApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ProgPrincipal9() {
    val urlBase = "https://jsonplaceholder.typicode.com/"
    val retrofit = Retrofit.Builder().baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val servicio = retrofit.create(PostApiService::class.java)
    val navController = rememberNavController()

    Scaffold(
        topBar =    { BarraSuperior() },
        bottomBar = { BarraInferior(navController) },
        content =   { paddingValues -> Contenido(paddingValues, navController, servicio) }
    )
}
