package nux.strive.models

enum class DeadlineStatus(
    text: String
) {
    OVERDUE("OVERDUE"),
    DUE("DUE"),
    SOON("SOON"),
    NOT_DUE("NOT DUE")
}
