package optim

import java.io.File
import java.lang.Integer.parseInt

data class Vertex(val x: Int, val y: Int)

fun parseVertices(verticesRaw: List<String>) = verticesRaw.map { raw ->
    val split = raw.split(' ')
    Vertex(parseInt(split[0]), parseInt(split[1]))
}

fun List<BooleanArray>.exists(x: Int, y: Int) =
    x in 0..(this.size - 1) &&
    y in 0..(this[x].size - 1) &&
    this[x][y]

fun List<BooleanArray>.exists(v: Vertex) = this.exists(v.x, v.y)

fun squares(originalVertices: List<Vertex>): Int {
    val offset = Vertex(
        Math.abs(originalVertices.map { v -> v.x }.min() ?:0),
        Math.abs(originalVertices.map { v -> v.y }.min() ?:0)
    )

    val vertices = originalVertices.map { v -> Vertex(v.x + offset.x, v.y + offset.y) }
    val dx = vertices.map { v -> v.x }.max() ?: 0
    val dy = vertices.map { v -> v.y }.max() ?: 0
    val lookup = IntRange(0, dx).map { _ -> BooleanArray(dy + 1, { _ -> false }) }

    vertices.forEach { v -> lookup[v.x][v.y] = true }

    var iterations = 0
    var res = 0

    val sorted = vertices.sortedWith(Comparator { v1, v2 -> v1.y.compareTo(v2.y) })
    sorted.forEachIndexed { index, u ->
        sorted.subList(index + 1, vertices.size).forEach { v ->
            iterations++
            if (u.x < v.x) {
                val u2 = Vertex(u.x + (v.y - u.y), u.y + (u.x - v.x))
                val v2 = Vertex(v.x + (v.y - u.y), v.y + (u.x - v.x))

                if (lookup.exists(u2) && lookup.exists(v2)) {
                    res++
                }
            }
        }
    }

    println(iterations)

    return res
}

fun squares2(originalVertices: List<Vertex>): Int {
    val offset = Vertex(
        Math.abs(originalVertices.map { v -> v.x }.min() ?:0),
        Math.abs(originalVertices.map { v -> v.y }.min() ?:0)
    )

    val vertices = originalVertices.map { v -> Vertex(v.x + offset.x, v.y + offset.y) }
    val dx = vertices.map { v -> v.x }.max() ?: 0
    val dy = vertices.map { v -> v.y }.max() ?: 0
    val lookup = IntRange(0, dx).map { _ -> BooleanArray(dy + 1, { _ -> false }) }

    vertices.forEach { v -> lookup[v.x][v.y] = true }

    var iterations = 0
    var res = 0

    val lookupVertices = vertices.groupBy { v -> v.x }.mapValues { entry -> entry.value.groupBy { v -> v.y }.toSortedMap() }.toMap()
    vertices.forEachIndexed { index, u ->
        (u.x + 1 until lookup.size)
            .filter { lookupVertices.containsKey(it) }
            .flatMap { lookupVertices[it]!!.subMap(u.y, lookup[u.x].size - 1).values }
            .flatMap { it }
            .forEach { v ->
                iterations++
                val u2 = Vertex(u.x + (v.y - u.y), u.y + (u.x - v.x))
                val v2 = Vertex(v.x + (v.y - u.y), v.y + (u.x - v.x))

                if (lookup.exists(u2) && lookup.exists(v2)) {
                    res++
                }
            }
    }

    println(iterations)

    return res
}

fun squares3(originalVertices: List<Vertex>): Int {
    val offset = Vertex(
        Math.abs(originalVertices.map { v -> v.x }.min() ?:0),
        Math.abs(originalVertices.map { v -> v.y }.min() ?:0)
    )

    val vertices = originalVertices.map { v -> Vertex(v.x + offset.x, v.y + offset.y) }
    val dx = vertices.map { v -> v.x }.max() ?: 0
    val dy = vertices.map { v -> v.y }.max() ?: 0
    val lookup = IntRange(0, dx).map { _ -> BooleanArray(dy + 1, { _ -> false }) }

    vertices.forEach { v -> lookup[v.x][v.y] = true }

    var iterations = 0
    var res = 0

    (0 until lookup.size).forEach { ux ->
        (0 until lookup[ux].size).forEach { uy ->
            if (lookup[ux][uy]) {
                (ux + 1 until lookup.size).forEach { vx ->
                    (uy until lookup[vx].size).forEach { vy ->
                        iterations++
                        if (lookup[vx][vy]) {
                            if (lookup.exists(ux + (vy - uy), uy + (ux - vx)) && lookup.exists(vx + (vy - uy), vy + (ux - vx))) {
                                res++
                            }
                        }
                    }
                }
            }
        }
    }

    println(iterations)

    return res
}

fun main(args: Array<String>) {
    val vertices = parseVertices(File(ClassLoader.getSystemClassLoader().getResource("vertices.txt").file).readLines())
    println(squares(vertices))
}