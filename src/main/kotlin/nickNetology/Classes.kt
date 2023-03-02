package nickNetology

class PostNotFoundException(mess: String) : RuntimeException(mess)

class CommentNotFoundException(mess: String) : RuntimeException(mess)

class NotCorrectReason(mess: String) : RuntimeException(mess)

class CRUD<T : Item>() {

    fun create(item: T): T {
        item.id = ++WallService.lastItemId
        if (WallService.foundById(item.id!!)) {
//            WallService.items += item.copy(id = ++WallService.lastItemId)
            WallService.items += item
            return WallService.items.last() as T
        } else throw PostNotFoundException("Post with id = $item.id was not found")
    }

    fun read(id: Int) {
        if (WallService.items[id - 1].isDeleted == false) {
            println(WallService.items[id - 1].text)
        } else println("Impossible to read (the item was deleted)")
    }

    fun update(newItem: T): Boolean {
        for ((id, item) in WallService.items.withIndex()) {
            if (item.id == newItem.id) {
//                WallService.items[id] = newItem.copy()
                WallService.items[id] = newItem
                return true
            }
        }
        return false
    }

    fun delete(id: Int) {
        WallService.items[id - 1].isDeleted = true
    }

    fun restore(id: Int) {
        WallService.items[id - 1].isDeleted = false
    }
}

open class Item(
    open var id: Int?,
    open var postId: Int?,
    open var isDeleted: Boolean?,
    open var text: String?
)

data class Comment(
    override var id: Int? = null,
    override var postId: Int? = null,
    override var isDeleted: Boolean? = false,
    override var text: String? = null,
    val fromId: Int? = null,
    val date: Int? = null,
    val donut: Donut? = null,
    val replyToUser: Int? = null,
    val replyToComment: Int? = null,
    val attachments: Array<Attachment>? = null,
    val parentsStack: Array<ParentComment>? = null,
    val thread: CommentThread? = null//Обычная фред уже зарезервирована джавой
) : Item(id, postId, isDeleted, text) {
    fun print() {
        println(text)
    }
}

data class Note(
    override var id: Int? = null,
    override var postId: Int? = null,
    override var isDeleted: Boolean? = false,
    override var text: String? = null,
    val title: String? = null,
    val privacy: Int? = null,
    val commentPrivacy: Int? = null,
) : Item(id, postId, isDeleted, text) {
    fun createComment() {}//Добавляет новый комментарий к заметке
    fun deleteComment() {}//Удаляет комментарий к заметке
    fun editComment() {}//Редактирует указанный комментарий у заметки
    fun get() {}//Возвращает список заметок, созданных пользователем
    fun getById() {}//Возвращает заметку по её id
    fun getComments() {}//Возвращает список комментариев к заметке
    fun getFriendsNotes() {}//Возвращает список заметок друзей пользователя
    fun restoreComment() {}//Восстанавливает удалённый комментарий
}

data class Post(
    var id: Int = 0,
    var comments: Array<Comment>? = null,
    val text: String? = null,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = true,
    val copyright: String? = null,
    val likes: Likes? = Likes(),
    val reposts: Reposts? = Reposts(),
    val views: Any? = null,
    val postType: String? = null,
    val postSourse: Any? = null,
    val geo: Any? = null,
    val signerId: Int? = null,
    val copyHistory: Array<Post>? = null,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean? = null,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Int? = null,
    val original: Post? = null,
    val attachments: Array<Attachment>? = null
)

data class Reposts(
    val userReposted: Boolean = false,
    val count: Int = 0
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false
)

class Donut(val isDon: Boolean, val placeholder: String)
class ParentComment()
class CommentThread(
    val count: Int,
    val items: Array<Item>,
    val canPost: Boolean,
    val showReplyButton: Boolean,
    val groupsCanPost: Boolean
)



