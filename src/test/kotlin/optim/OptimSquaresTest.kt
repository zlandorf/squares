package optim

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.File

class OptimSquaresTest {

    @Test
    fun test_1() {
        val vertices = parseVertices(File(ClassLoader.getSystemClassLoader().getResource("vertices.txt").file).readLines())
        assertThat(squares(vertices)).isEqualTo(56)

    }

    @Test
    fun test_2() {
        val vertices = parseVertices(File(ClassLoader.getSystemClassLoader().getResource("vertices.txt").file).readLines())
        assertThat(squares2(vertices)).isEqualTo(56)
    }

    @Test
    fun test_3() {
        val vertices = parseVertices(File(ClassLoader.getSystemClassLoader().getResource("vertices.txt").file).readLines())
        assertThat(squares3(vertices)).isEqualTo(56)
    }

}