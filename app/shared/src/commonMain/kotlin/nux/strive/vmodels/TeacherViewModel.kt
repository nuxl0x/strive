package nux.strive.vmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nux.strive.models.ContentModel
import nux.strive.models.ContentModelType
import nux.strive.models.ContentModels

sealed interface TeacherViewUiState {
    object Loading : TeacherViewUiState
    data class Success(val data: Map<String, ContentModel>) : TeacherViewUiState
    data class Error(val message: String) : TeacherViewUiState
}

class TeacherViewModel(
    val httpClient: HttpClient
) : ViewModel() {
    private val mutableUiState = MutableStateFlow<TeacherViewUiState>(TeacherViewUiState.Loading)
    val uiState = mutableUiState.asStateFlow()

    init {
        fetchContent()
    }

    fun fetchContent() {
        viewModelScope.launch {
            mutableUiState.value = TeacherViewUiState.Loading
            try {
                val students: List<String> = httpClient.get("/api/students") {
                    parameter("uuid", "teacher-1-uuid")
                }.body()

                val studentModels = mutableMapOf<String, ContentModel>()
                students.forEach { studentUuid ->
                    val model: ContentModel = httpClient.get("/api/content") {
                        parameter("uuid", studentUuid)
                    }.body()
                    studentModels[studentUuid] = model
                }
                val response: Map<String, ContentModel> = studentModels

                mutableUiState.value = TeacherViewUiState.Success(response)
            } catch (e: ResponseException) {
                mutableUiState.value = TeacherViewUiState.Error("Server error: ${e.response.status}")
            } catch (_: Exception) {
                mutableUiState.value = TeacherViewUiState.Error("You need internet to use this application!")
            }
        }
    }

    fun addModel(uuid: String, model: ContentModels) {
        viewModelScope.launch {
            try {
                httpClient.post("/api/content/add-content") {
                    parameter("uuid", uuid)
                    setBody(model)
                }
            } catch (_: Exception) {}
        }
    }

    fun removeModel(uuid: String, type: ContentModelType, name: String) {
        viewModelScope.launch {
            try {
                httpClient.post("/api/content/remove-content") {
                    parameter("uuid", uuid)
                    parameter("type", type.strValue)
                    parameter("name", name)
                }
            } catch (_: Exception) {}
        }
    }

}