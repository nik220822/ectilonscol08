import junit.framework.Assert.assertEquals
import nickNetology.*
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class Tests {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun testAdd() {
        val result = WallService.add(Post()).id
        assertNotEquals(0, result)
    }

    @Test
    fun testUpdateIfReturnTrue() {
        val post1 = Post()
        WallService.add(post1)
        val result = WallService.update(Post(id = 1))
        assertEquals(true, result)
    }

    @Test
    fun testUpdateIfReturnFalse() {
        val post1 = Post()
        WallService.add(post1)
        val result = WallService.update(Post(id = 2))
        assertEquals(false, result)
    }

    @Test
    fun createTrue() {
        var result: Item
        WallService.add(Post())//id=1
        val item: Item = Note()
        item.id = ++WallService.lastItemId
        if (WallService.foundById(item.id!!)) {
            WallService.items += item
            result = WallService.items.last()
        } else throw PostNotFoundException("Post with id = $item.id was not found")
        assertEquals(item, result)
    }

    @Test
    fun readTrue() {
        WallService.add(Post())//id=1
        val id = 1
        val result: Boolean
        CRUD<Item>().create(Note(postId = 1, title = "Important note for post 1", text = "note1 text"))
        if (WallService.items[id - 1].isDeleted == false) {
            println(WallService.items[id - 1].text)
            result = true
        } else {
            println("Impossible to read (the item was deleted)")
            result = false
        }
        assertEquals(true, result)
    }

    @Test
    fun readFalse() {
        WallService.add(Post())//id=1
        val id = 1
        val result: Boolean
        CRUD<Item>().create(Note(postId = 1, title = "Important note for post 1", text = "note1 text"))
        WallService.items[id-1].isDeleted = true
        if (WallService.items[id-1].isDeleted == false) {
            println(WallService.items[id].text)
            result = true
        } else {
            println("Impossible to read (the item was deleted)")
            result = false
        }
        assertEquals(false, result)
    }

    @Test
    fun updateFalse() {
        WallService.add(Post())//id=1
        var result: Boolean
        CRUD<Item>().create(Note(postId = 1, title = "Important note for post 1", text = "note1 text"))
        val newItem: Item = Note(id = 2, postId = 1, title = "Important note for post 1", text = "updated note1 text")
        for ((id, item) in WallService.items.withIndex()) {
            if (item.id == newItem.id) {
                WallService.items[id-1] = newItem
                result = true
            }
        }
        result = false
        assertEquals(false, result)
    }

    @Test
    fun delete() {
        WallService.add(Post())//id=1
        val id = 1
        CRUD<Item>().create(Note(postId = 1, title = "Important note for post 1", text = "note1 text"))
        WallService.items[id-1].isDeleted = true
        val result = WallService.items[id-1].isDeleted
        assertEquals(true, result)
    }

    @Test
    fun restore() {
        WallService.add(Post())//id=1
        val id = 1
        CRUD<Item>().create(Note(postId = 1, title = "Important note for post 1", text = "note1 text"))
        WallService.items[id-1].isDeleted = false
        val result = WallService.items[id-1].isDeleted
        assertEquals(false, result)
    }
}

