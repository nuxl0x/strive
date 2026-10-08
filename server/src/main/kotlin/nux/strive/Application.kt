package nux.strive

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import nux.strive.models.AssessmentModel
import nux.strive.models.ContentModel
import nux.strive.models.ContentModels
import nux.strive.models.TaskModel
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

fun main() {
    embeddedServer(Netty, port = 8087, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    val studentUuids = listOf(
        "student-1-uuid",
        "student-2-uuid"
    )

    val teacherUuids = listOf(
        "teacher-1-uuid"
    )

    val teacherToStudentAssignment = mapOf(
        "teacher-1-uuid" to studentUuids
    )

    val database = mutableMapOf<String, ContentModel>()

    val defaultTasks = mutableMapOf(
        "student-1-uuid" to mutableListOf(
            TaskModel(
                "Read & annotate Chapter 4 of Electrodynamics",
                1.5.hours, "PHYSICS"
            ),

            TaskModel(
                "Draft outline for Philosophy term paper",
                1.hours, "PHIL"
            ),

            TaskModel(
                "Complete problem set on Multivariable Calculus",
                2.hours, "MATH"
            ),

            TaskModel(
                "Review active recall cards for Classical Mechanics",
                45.minutes, "PHYSICS"
            ),

            TaskModel(
                "Submit initial prototype proposal",
                30.minutes, "DESIGN"
            )
        ),
        "student-2-uuid" to mutableListOf(
            TaskModel(
                "Read & annotate Chapter 6 of Thermodynamics",
                1.hours, "PHYSICS"
            ),

            TaskModel(
                "Draft outline for History paper",
                1.5.hours, "HISTORY"
            ),

            TaskModel(
                "Revise Japanese assessments",
                4.hours, "JAP"
            ),

            TaskModel(
                "Research network security",
                56.minutes, "COMP"
            ),

            TaskModel(
                "Create test application",
                43.minutes, "COMP"
            )
        )
    )

    val defaultAssessments = mutableMapOf(
        "student-1-uuid" to mutableListOf(
            AssessmentModel("Math Midterm Exam", LocalDate(2026, 10, 7)),
            AssessmentModel("Philosophy Paper Draft", LocalDate(2026, 10, 8)),
            AssessmentModel("Physics Lab Report 3", LocalDate(2026, 10, 10)),
            AssessmentModel("English Language Exam", LocalDate(2026, 10, 12)),
            AssessmentModel("Applied Computing SAC", LocalDate(2026, 10, 25))
        ),
        "student-2-uuid" to mutableListOf(
            AssessmentModel("Japanese SAC", LocalDate(2026, 10, 8)),
            AssessmentModel("Physics Thermodynamics Test", LocalDate(2026, 10, 11)),
            AssessmentModel("History Essay", LocalDate(2026, 10, 13)),
            AssessmentModel("English Language Exam", LocalDate(2026, 10, 23)),
            AssessmentModel("Applied Computing Exam", LocalDate(2026, 10, 27))
        )
    )

    fun populateDatabase() {
        database.clear()
        studentUuids.forEach { uuid ->
            val userContent = ContentModel(defaultTasks[uuid]!!, defaultAssessments[uuid]!!)
            database[uuid] = userContent
        }
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    routing {
        get("/api/content") {
            val uuid = call.request.queryParameters["uuid"]

            if (uuid.isNullOrBlank()) call.respond(HttpStatusCode.BadRequest)
            if (!studentUuids.contains(uuid)) call.respond(HttpStatusCode.BadRequest)
            if (database.isEmpty()) populateDatabase()

            val response = database[uuid] ?: call.respond(HttpStatusCode.NotFound)

            call.respond(response)
        }

        get("/api/students") {
            val uuid = call.request.queryParameters["uuid"]

            if (uuid.isNullOrBlank()) call.respond(HttpStatusCode.BadRequest)
            if (!teacherUuids.contains(uuid)) call.respond(HttpStatusCode.BadRequest)

            val students = teacherToStudentAssignment[uuid] ?: call.respond(HttpStatusCode.NotFound)

            call.respond(students)
        }

        post("/api/content/add-content") {
            val uuid = call.request.queryParameters["uuid"]

            if (uuid.isNullOrBlank()) call.respond(HttpStatusCode.BadRequest)
            if (!studentUuids.contains(uuid)) call.respond(HttpStatusCode.BadRequest)
            if (database.isEmpty()) populateDatabase()

            when (val toUpload = call.receive<ContentModels>()) {
                is TaskModel -> {
                    val uuidStoredContent = database[uuid]
                    if (uuidStoredContent == null) call.respond(HttpStatusCode.InternalServerError)
                    uuidStoredContent!!.tasks.add(toUpload)
                }
                is AssessmentModel -> {
                    val uuidStoredContent = database[uuid]
                    if (uuidStoredContent == null) call.respond(HttpStatusCode.InternalServerError)
                    uuidStoredContent!!.assessments.add(toUpload)
                }
            }

            call.respond(HttpStatusCode.Accepted)

        }

        post("/api/content/remove-content") {
            val uuid = call.request.queryParameters["uuid"]
            if (uuid.isNullOrBlank()) call.respond(HttpStatusCode.BadRequest)
            if (!studentUuids.contains(uuid)) call.respond(HttpStatusCode.BadRequest)
            if (database.isEmpty()) populateDatabase()

            val type = call.request.queryParameters["type"]
            if (type.isNullOrBlank()) call.respond(HttpStatusCode.BadRequest)
            if (!(type == "task" || type == "assessment")) call.respond(HttpStatusCode.BadRequest)

            val name = call.request.queryParameters["name"]
            if (name == null) call.respond(HttpStatusCode.BadRequest)

            val uuidStoredContent = database[uuid]
            if (uuidStoredContent == null) call.respond(HttpStatusCode.InternalServerError)

            when (type) {
                "task" -> {
                    uuidStoredContent!!.tasks.removeAll { it.name == name }
                }
                "assessment" -> {
                    uuidStoredContent!!.assessments.removeAll { it.name == name }
                }
            }

        }

    }
}