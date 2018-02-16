import java.io.File
import java.lang.Integer.parseInt

data class Vertex(val x: Int, val y: Int) {
    fun plus(other: Vertex) = Vertex(x + other.x, y + other.y)
    fun minus(other: Vertex) = Vertex(x - other.x, y - other.y)
}

data class Segment(val u: Vertex, val v: Vertex)

data class Square(val vertices: List<Vertex>) {
    override fun equals(other: Any?): Boolean {
        return other is Square && vertices.size == other.vertices.size && vertices.containsAll(other.vertices)
    }

    override fun hashCode(): Int {
        return vertices.map { v -> v.hashCode() }.sum()
    }
}

fun parseVertices(verticesRaw: List<String>) = verticesRaw.map { raw ->
    val split = raw.split(' ')
    Vertex(parseInt(split[0]), parseInt(split[1]))
}

fun List<Vertex>.changeReferential(): List<Vertex> {
    val offset = Vertex(Math.abs(this.map { v -> v.x }.min() ?:0), Math.abs(this.map { v -> v.y }.min() ?:0))
    return this.map { v -> v.plus(offset) }
}

fun List<BooleanArray>.exists(v: Vertex) =
    v.x in 0..(this.size - 1) &&
    v.y in 0..(this[v.x].size - 1) &&
    this[v.x][v.y]

fun List<BooleanArray>.exists(segment: Segment) = this.exists(segment.u) && this.exists(segment.v)

fun squares(segment: Segment, lookup: List<BooleanArray>): List<Square> {
    val u = segment.u
    val v = segment.v

    if (u == v) {
        return listOf()
    }

    val offset = Vertex(v.y - u.y, u.x - v.x)

    var squares = mutableListOf<Square>()

    if (lookup.exists(Segment(u.plus(offset), v.plus(offset)))) {
        squares.add(Square(listOf(u, v, u.plus(offset), v.plus(offset))))
    }
    if (lookup.exists(Segment(u.minus(offset), v.minus(offset)))) {
        squares.add(Square(listOf(u, v, u.minus(offset), v.minus(offset))))
    }

    if (squares.size > 0) {
        lookup[segment.v.x][segment.v.y] = false
        lookup[segment.u.x][segment.u.y] = false
    }

    return squares
}

fun squares(originalVertices: List<Vertex>): List<Square> {
    val offset = Vertex(Math.abs(originalVertices.map { v -> v.x }.min() ?:0), Math.abs(originalVertices.map { v -> v.y }.min() ?:0))
    val vertices = originalVertices.map { v -> v.plus(offset) }

    val dx = vertices.map { v -> v.x }.max() ?: 0
    val dy = vertices.map { v -> v.y }.max() ?: 0

    val lookup = IntRange(0, dx).map { _ -> BooleanArray(dy + 1, { _ -> false }) }

    vertices.forEach { v -> lookup[v.x][v.y] = true }


    var squares = mutableSetOf<Square>()

    vertices.forEachIndexed { index, u ->
        vertices.subList(index + 1, vertices.size).forEach { v ->
            squares.addAll(squares(Segment(u, v), lookup))
        }
    }

    return squares.map { s -> Square(s.vertices.map { v -> v.minus(offset) }) }
}

fun main(args: Array<String>) {
    val vertices = parseVertices(File(ClassLoader.getSystemClassLoader().getResource("vertices.txt").file).readLines())
    println(squares(vertices).size)
}