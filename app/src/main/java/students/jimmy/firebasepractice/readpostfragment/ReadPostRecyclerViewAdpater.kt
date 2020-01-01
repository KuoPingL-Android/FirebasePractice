package students.jimmy.firebasepractice.readpostfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import students.jimmy.firebasepractice.databinding.GridItemReadpostBinding
import students.jimmy.firebasepractice.dataclass.Post

class ReadPostRecyclerViewAdpater : ListAdapter<Post, ReadPostRecyclerViewAdpater.PostViewHolder>(CallBack) {
    companion object CallBack: DiffUtil.ItemCallback<Post>() {
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return (
                    oldItem.article_content == newItem.article_content &&
                            oldItem.article_title == newItem.article_title &&
                            oldItem.article_id == newItem.article_id
                    )
        }

        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }
    }

    class PostViewHolder(val binding: GridItemReadpostBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       return PostViewHolder(GridItemReadpostBinding.inflate(
           LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }


}