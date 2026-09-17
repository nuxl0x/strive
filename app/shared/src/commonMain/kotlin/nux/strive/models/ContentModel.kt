package nux.strive.models

import kotlinx.serialization.Serializable

@Serializable
data class ContentModel(
    val tasks: List<TaskModel>,
    val assessments: List<AssessmentModel>
)
