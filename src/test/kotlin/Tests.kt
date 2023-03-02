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
    
}