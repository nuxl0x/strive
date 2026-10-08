package nux.strive.models

import kotlinx.serialization.Serializable

@Serializable
data class ContentModel(
    val tasks: MutableList<TaskModel>,
    val assessments: MutableList<AssessmentModel>
)
