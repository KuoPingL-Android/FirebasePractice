package students.jimmy.firebasepractice.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import students.jimmy.firebasepractice.dataclass.Post
import students.jimmy.firebasepractice.readpostfragment.ReadPostRecyclerViewAdpater

@BindingAdapter("postbinding")
fun bind(recyclerView: RecyclerView, posts: List<Post>?) {
    val adapter = recyclerView.adapter
    when(adapter) {
        is ReadPostRecyclerViewAdpater -> {
            adapter.submitList(posts)
        }
    }
}