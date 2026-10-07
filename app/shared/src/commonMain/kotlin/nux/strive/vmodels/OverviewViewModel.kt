package nux.strive.vmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nux.strive.models.ContentModel

sealed interface OverviewUiState {
    object Loading : OverviewUiState
    data class Success(val data: ContentModel) : OverviewUiState
    data class Error(val message: String) : OverviewUiState
}

class OverviewViewModel(val httpClient: HttpClient) : ViewModel() {
    private val mutableUiState = MutableStateFlow<OverviewUiState>(OverviewUiState.Loading)
    val uiState = mutableUiState.asStateFlow()

    init {
        fetchContent()
    }

    fun fetchContent() {
        viewModelScope.launch {
            mutableUiState.value = OverviewUiState.Loading
            try {
                val response: ContentModel = httpClient.get("/api/content") {
                    parameter("uuid", "example-uuid")
                }.body()

                mutableUiState.value = OverviewUiState.Success(response)
            } catch (e: ResponseException) {
                mutableUiState.value = OverviewUiState.Error("Server error: ${e.response.status}")
            } catch (e: Exception) {
                mutableUiState.value = OverviewUiState.Error("Network failure: ${e.localizedMessage}")
            }

        }
    }

}