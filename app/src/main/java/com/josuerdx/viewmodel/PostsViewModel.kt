package com.josuerdx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josuerdx.models.PostModel
import com.josuerdx.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Se crea el ViewModel que interactúa con el repositorio.
class PostsViewModel(private val repository: PostRepository) : ViewModel() {

    // Flujo que mantiene la lista de posts.
    private val _listaPosts = MutableStateFlow<List<PostModel>>(emptyList())
    val listaPosts: StateFlow<List<PostModel>> get() = _listaPosts

    // Flujo que mantiene los detalles de un post específico.
    private val _postDetail = MutableStateFlow<PostModel?>(null)
    val postDetail: StateFlow<PostModel?> get() = _postDetail

    // Función para obtener la lista de posts desde el repositorio.
    fun fetchUserPosts() {
        viewModelScope.launch {
            val posts = repository.getPosts()
            _listaPosts.value = posts
        }
    }

    // Función para obtener un post por su ID desde el repositorio.
    fun fetchUserPostById(id: Int) {
        viewModelScope.launch {
            val post = repository.getPostById(id)
            _postDetail.value = post
        }
    }
}
