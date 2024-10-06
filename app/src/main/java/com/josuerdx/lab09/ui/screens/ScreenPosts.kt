package com.josuerdx.lab09.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.josuerdx.models.PostModel
import com.josuerdx.network.PostApiService

@Composable
fun ScreenPosts(navController: NavHostController, servicio: PostApiService) {
    // Definir la lista observable de posts
    val listaPosts: SnapshotStateList<PostModel> = remember { mutableStateListOf() }

    // Llamada a la API para cargar los posts
    LaunchedEffect(Unit) {
        val listado = servicio.getUserPosts()  // Obtener posts desde la API
        listaPosts.addAll(listado)  // Actualizar la lista de posts
    }

    // Mostrar los posts en una LazyColumn
    LazyColumn {
        items(listaPosts) { item ->
            Row(modifier = Modifier.padding(8.dp)) {
                Text(text = item.id.toString(), Modifier.weight(0.1f), textAlign = TextAlign.End)
                Spacer(Modifier.padding(horizontal = 8.dp))
                Text(text = item.title, Modifier.weight(0.7f))
                IconButton(
                    onClick = {
                        navController.navigate("postsVer/${item.id}")
                    },
                    Modifier.weight(0.2f)
                ) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "Ver")
                }
            }
        }
    }
}
