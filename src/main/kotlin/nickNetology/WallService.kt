package nickNetology

object WallService {
    var posts = emptyArray<Post>()
    private var lastPostId: Int = 0
    var comments = emptyArray<Comment>()
    var items = mutableListOf<Item>()
    private var lastCommentId: Int = 0
    var reportComments = emptyArray<Comment>()
    private var lastReportCommentId: Int = 0
    internal var lastItemId: Int = 0

    fun clear() {
        posts = emptyArray()
        comments = emptyArray()
        reportComments = emptyArray()
        lastPostId = 0
        lastCommentId = 0
        lastReportCommentId = 0
        lastItemId = 0
        items = mutableListOf()
    }

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastPostId, likes = post.likes?.copy(), reposts = post.reposts?.copy())
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((id, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[id] = newPost.copy(likes = newPost.likes?.copy(), reposts = newPost.reposts?.copy())
                return true
            }
        }
        return false
    }

    fun print() {
        for (post in posts) {
            println(post)
            for (comment in comments) {
                if (post.id == comment.postId) {
                    println(comment)
                    comment.print()
                }
            }
            for (item in items) {
                if (post.id == item.postId)
                println(item)
            }
        }
    }

    fun foundById(PostId: Int): Boolean {
        for (post in posts) {
            if (post.id == PostId) {
                return true
            }
        }
        return false
    }

    fun reportComment(id: Int, reason: Int): Int {
        if (foundCommentById(id)) {
            addReportComment(id)
            when (reason) {
                0 -> {
                    println("в комменте $id спам")
                    return 1
                }

                1 -> {
                    println("в комменте $id детская порнография")
                    return 1
                }

                2 -> {
                    println("в комменте $id экстремизм")
                    return 1
                }

                3 -> {
                    println("в комменте $id насилие")
                    return 1
                }

                4 -> {
                    println("в комменте $id пропаганда наркотиков")
                    return 1
                }

                5 -> {
                    println("в комменте $id материал для взрослых")
                    return 1
                }

                6 -> {
                    println("в комменте $id оскорбление")
                    return 1
                }

                7 -> {
                    println("в комменте $id призывы к суициду")
                    return 1
                }

                else -> {
                    throw NotCorrectReason("the given reason ($reason) is incorrect")
                }
            }
        }
        return throw CommentNotFoundException("Comment with id = $id was not found")
    }

    fun foundCommentById(commentId: Int): Boolean {
        for (comment in comments) {
            if (comment.id == commentId) {
                return true
            }
        }
        return false
    }

    fun addReportComment(commentId: Int): Comment {
        val comment = comments[commentId - 1]
        reportComments += comment.copy(id = ++lastReportCommentId)
        return reportComments.last()
    }
}