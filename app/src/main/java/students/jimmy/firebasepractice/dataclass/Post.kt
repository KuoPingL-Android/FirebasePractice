package students.jimmy.firebasepractice.dataclass

import java.util.*

enum class PostKeys(val string: String) {
    Title("article_title"),
    Content("article_content"),
    Tag("article_tag"),
    Author("author"),
    ID("article_id"),
    Date("create_time"),
    UserId("user_id")
}

data class Post(
    val article_title: String? = null,
    val article_content: String? = null,
    val article_tag: String? = null,
    val author: String? = null,
    val create_time: String? = null,
    val user_id: String? = null
) {
    val article_id: String? = null
}