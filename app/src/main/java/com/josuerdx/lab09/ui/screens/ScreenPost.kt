package com.josuerdx.lab09.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.josuerdx.models.PostModel
import com.josuerdx.network.PostApiService

@Composable
fun ScreenPost(navController: NavHostController, servicio: PostApiService, id: Int) {
    var post by remember { mutableStateOf<PostModel?>(null) }

    // Llamar a la API para obtener el detalle del post según el ID
    LaunchedEffect(Unit) {
        val fetchedPost = servicio.getUserPostById(id)  // Obtener el post
        post = fetchedPost  // Asignar el post recuperado a la variable
    }
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        if (post != null) {
            // Mostrar los detalles del post
            OutlinedTextField(
                value = post!!.id.toString(),
                onValueChange = {},
                label = { Text("ID") },
                readOnly = true
            )
            OutlinedTextField(
                value = post!!.userId.toString(),
                onValueChange = {},
                label = { Text("User ID") },
                readOnly = true
            )
            OutlinedTextField(
                value = post!!.title,
                onValueChange = {},
                label = { Text("Title") },
                readOnly = true
            )
            OutlinedTextField(
                value = post!!.body,
                onValueChange = {},
                label = { Text("Body") },
                readOnly = true
            )
        } else {
            // Mostrar un texto mientras se carga la información
            Text("Cargando detalles...")
        }
    }
}
