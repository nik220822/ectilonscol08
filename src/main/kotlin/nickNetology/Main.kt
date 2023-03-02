package nickNetology

fun main() {
    WallService.clear()
    WallService.add(Post())//id=1
    WallService.add(Post(text = "text for future updation"))//id=2
    WallService.add(Post())//id=3
//    WallService.print()
//    println()
//    WallService.update(Post(id = 2, text = "text updation"))// Обновил пост с id=2
    val comment1 = Comment(postId = 2, text = "text for comment for post 2")
    val note1 = Note(postId = 1, title = "Important note for post 1", text = "note1 text")
    CRUD<Item>().create(comment1)//id=1
    CRUD<Item>().create(note1)//id=2
    WallService.print()
    println()
    CRUD<Item>().update(Comment(id = 1, text = "updated text for comment for post 2"))
    CRUD<Item>().read(1)
    CRUD<Item>().read(2)
    println("deleting item 1")
    CRUD<Item>().delete(1)
    CRUD<Item>().read(1)
    println("restoring item 1")
    CRUD<Item>().restore(1)
    CRUD<Item>().read(1)
}