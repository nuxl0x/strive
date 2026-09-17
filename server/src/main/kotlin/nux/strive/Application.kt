package nux.strive

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.LocalDate
import nux.strive.models.AssessmentModel
import nux.strive.models.ContentModel
import nux.strive.models.TaskModel
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        get("/api/content") {
            val apiQuery = call.request.queryParameters["uuid"]
            if (apiQuery.isNullOrBlank()) call.respond(HttpStatusCode.BadRequest)

            if (!apiQuery.equals("example-uuid", ignoreCase = true)) call.respond(HttpStatusCode.BadRequest)

            val exampleTasks = listOf(
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
            )

            val exampleAssessments = listOf(
                AssessmentModel("Math Midterm Exam", LocalDate(2026, 9, 1)),
                AssessmentModel("Philosophy Paper Draft", LocalDate(2026, 9, 2)),
                AssessmentModel("Physics Lab Report 3", LocalDate(2026, 9, 3)),
                AssessmentModel("English Language Exam", LocalDate(2026, 9, 28)),
                AssessmentModel("Applied Computing SAC", LocalDate(2026, 9, 3))
            )

            call.respond(ContentModel(exampleTasks, exampleAssessments))
        }
    }
}