package students.jimmy.firebasepractice

import students.jimmy.firebasepractice.dataclass.User

object CurrentUser {
    private var user: User? = null

    val name: String?
        get() = user?.user_name

    val email: String?
        get() = user?.user_email

    fun setUser(user: User) {
        this.user = user
    }
}