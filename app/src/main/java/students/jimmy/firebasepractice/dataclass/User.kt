package students.jimmy.firebasepractice.dataclass

enum class UserKeys(val string: String) {
    Name("user_name"),
    Email("user_email"),
    ID("user_id")
}


data class User(
    val user_email: String? = null,
    val user_name: String? = null,
    val user_id: String? = null
)