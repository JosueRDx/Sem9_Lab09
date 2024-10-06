package com.josuerdx.repository

import com.josuerdx.models.PostModel
import com.josuerdx.network.PostApiService

// Se implementa el repositorio que interactúa con el servicio API.
class PostRepository(private val apiService: PostApiService) {

    // Método para obtener la lista de posts.
    suspend fun getPosts(): List<PostModel> {
        return apiService.getUserPosts()
    }

    // Método para obtener un post específico por ID.
    suspend fun getPostById(id: Int): PostModel {
        return apiService.getUserPostById(id)
    }
}
