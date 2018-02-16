import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.File

class SquaresKtTest {

    @Test
    fun testExtractVertices() {
        val raw = listOf("514 -427", "383 870", "-386 -28", "231 -417")

        assertThat(parseVertices(raw)).containsExactlyInAnyOrder(
            Vertex(514, -427),
            Vertex(383, 870),
            Vertex(-386, -28),
            Vertex(231, -417)
        )
    }

    @Test
    fun testVerticesAreEqual() {
        assertThat(Vertex(3, 2)).isEqualTo(Vertex(3, 2))
    }

    @Test
    fun testSquaresAreEqual() {
        assertThat(
            Square(listOf(Vertex(3, 3), Vertex(5, 4), Vertex(2, 5), Vertex(4, 6)))
        ).isEqualTo(
            Square(listOf(Vertex(3, 3), Vertex(2, 5), Vertex(5, 4), Vertex(4, 6)))
        )
    }

    @Test
    fun testSetOfEqualSquaresOnlyContainsOneElement() {
        val square = Square(listOf(Vertex(3, 3), Vertex(5, 4), Vertex(2, 5), Vertex(4, 6)))
        val equalSquare = Square(listOf(Vertex(3, 3), Vertex(2, 5), Vertex(5, 4), Vertex(4, 6)))

        assertThat(setOf(square, equalSquare)).hasSize(1)
    }

    @Test
    fun testSetOfDifferentSquaresContainsTwoElements() {
        val square = Square(listOf(Vertex(3, 3), Vertex(5, 4), Vertex(2, 5), Vertex(4, 6)))
        val equalSquare = Square(listOf(Vertex(7, 9), Vertex(2, 5), Vertex(5, 4), Vertex(4, 6)))

        assertThat(setOf(square, equalSquare)).hasSize(2)
    }

    @Test
    fun testSquareCount1() {
        val vertices = listOf(
            Vertex(3, 3),
            Vertex(5, 4),
            Vertex(2, 5),
            Vertex(4, 6)
        )

        assertThat(squares(vertices)).hasSize(1)
    }

    @Test
    fun testSquareCount0() {
        val vertices = listOf(
            Vertex(3, 3),
            Vertex(5, 4),
            Vertex(2, 5),
            Vertex(6, 2)
        )

        assertThat(squares(vertices)).hasSize(0)
    }

    @Test
    fun testSquareCount2() {
        val vertices = listOf(
            Vertex(3, 3),
            Vertex(5, 4),
            Vertex(2, 5),
            Vertex(4, 6),
            Vertex(4, 1),
            Vertex(6, 2)
        )

        assertThat(squares(vertices)).hasSize(2)
    }
    @Test
    fun testSquareCount3() {
        val vertices = listOf(
            Vertex(3, 3),
            Vertex(5, 4),
            Vertex(2, 5),
            Vertex(4, 6),
            Vertex(4, 1),
            Vertex(6, 2),
            Vertex(3, 7),
            Vertex(0, 5),
            Vertex(2, 2),
            Vertex(5, 6)
        )

        assertThat(squares(vertices)).hasSize(3)
    }
    
    @Test
    fun testGlobal() {
        val vertices = parseVertices(File(ClassLoader.getSystemClassLoader().getResource("vertices.txt").file).readLines())

        assertThat(squares(vertices)).containsExactlyInAnyOrder(
            Square(listOf(Vertex(-667,-717), Vertex(-661,943), Vertex(993,-723), Vertex(999,937))),
            Square(listOf(Vertex(-148,-157), Vertex(306,103), Vertex(112,-611), Vertex(566,-351))),
            Square(listOf(Vertex(-682,-377), Vertex(-593,48), Vertex(-257,-466), Vertex(-168,-41))),
            Square(listOf(Vertex(-745,-647), Vertex(-605,-401), Vertex(-499,-787), Vertex(-359,-541))),
            Square(listOf(Vertex(-418,373), Vertex(531,779), Vertex(-12,-576), Vertex(937,-170))),
            Square(listOf(Vertex(11,69), Vertex(399,638), Vertex(580,-319), Vertex(968,250))),
            Square(listOf(Vertex(-976,216), Vertex(-849,703), Vertex(-489,89), Vertex(-362,576))),
            Square(listOf(Vertex(-669,24), Vertex(209,133), Vertex(-560,-854), Vertex(318,-745))),
            Square(listOf(Vertex(-366,-89), Vertex(-102,849), Vertex(572,-353), Vertex(836,585))),
            Square(listOf(Vertex(-818,579), Vertex(481,700), Vertex(-697,-720), Vertex(602,-599))),
            Square(listOf(Vertex(-845,-15), Vertex(-75,704), Vertex(-126,-785), Vertex(644,-66))),
            Square(listOf(Vertex(-928,-550), Vertex(-847,336), Vertex(-42,-631), Vertex(39,255))),
            Square(listOf(Vertex(-743,-200), Vertex(-296,886), Vertex(343,-647), Vertex(790,439))),
            Square(listOf(Vertex(-629,-384), Vertex(-464,183), Vertex(-62,-549), Vertex(103,18))),
            Square(listOf(Vertex(-85,-517), Vertex(23,335), Vertex(767,-625), Vertex(875,227))),
            Square(listOf(Vertex(-84,247), Vertex(46,656), Vertex(325,117), Vertex(455,526))),
            Square(listOf(Vertex(-42,-294), Vertex(43,304), Vertex(556,-379), Vertex(641,219))),
            Square(listOf(Vertex(-541,-718), Vertex(-333,88), Vertex(265,-926), Vertex(473,-120))),
            Square(listOf(Vertex(-559,-63), Vertex(100,655), Vertex(159,-722), Vertex(818,-4))),
            Square(listOf(Vertex(276,-314), Vertex(586,84), Vertex(674,-624), Vertex(984,-226))),
            Square(listOf(Vertex(-851,157), Vertex(-559,650), Vertex(-358,-135), Vertex(-66,358))),
            Square(listOf(Vertex(209,133), Vertex(259,787), Vertex(863,83), Vertex(913,737))),
            Square(listOf(Vertex(-850,-360), Vertex(-608,870), Vertex(380,-602), Vertex(622,628))),
            Square(listOf(Vertex(-443,-846), Vertex(-397,-402), Vertex(1,-892), Vertex(47,-448))),
            Square(listOf(Vertex(-80,-18), Vertex(138,-8), Vertex(-70,-236), Vertex(148,-226))),
            Square(listOf(Vertex(-660,-876), Vertex(-650,-775), Vertex(-559,-886), Vertex(-549,-785))),
            Square(listOf(Vertex(126,297), Vertex(528,433), Vertex(262,-105), Vertex(664,31))),
            Square(listOf(Vertex(-692,-2), Vertex(-38,575), Vertex(-115,-656), Vertex(539,-79))),
            Square(listOf(Vertex(124,491), Vertex(708,594), Vertex(227,-93), Vertex(811,10))),
            Square(listOf(Vertex(-712,-253), Vertex(-172,294), Vertex(-165,-793), Vertex(375,-246))),
            Square(listOf(Vertex(202,330), Vertex(528,684), Vertex(556,4), Vertex(882,358))),
            Square(listOf(Vertex(-846,672), Vertex(-75,704), Vertex(-814,-99), Vertex(-43,-67))),
            Square(listOf(Vertex(-554,-251), Vertex(-475,630), Vertex(327,-330), Vertex(406,551))),
            Square(listOf(Vertex(-778,52), Vertex(-382,705), Vertex(-125,-344), Vertex(271,309))),
            Square(listOf(Vertex(-945,-122), Vertex(-351,-72), Vertex(-895,-716), Vertex(-301,-666))),
            Square(listOf(Vertex(276,299), Vertex(379,487), Vertex(464,196), Vertex(567,384))),
            Square(listOf(Vertex(-537,-463), Vertex(-269,690), Vertex(616,-731), Vertex(884,422))),
            Square(listOf(Vertex(-930,-190), Vertex(-506,252), Vertex(-488,-614), Vertex(-64,-172))),
            Square(listOf(Vertex(-752,616), Vertex(595,867), Vertex(-501,-731), Vertex(846,-480))),
            Square(listOf(Vertex(-376,193), Vertex(132,650), Vertex(81,-315), Vertex(589,142))),
            Square(listOf(Vertex(139,512), Vertex(450,530), Vertex(157,201), Vertex(468,219))),
            Square(listOf(Vertex(-857,266), Vertex(-348,840), Vertex(-283,-243), Vertex(226,331))),
            Square(listOf(Vertex(-436,-305), Vertex(-116,36), Vertex(-95,-625), Vertex(225,-284))),
            Square(listOf(Vertex(-934,-116), Vertex(-159,830), Vertex(12,-891), Vertex(787,55))),
            Square(listOf(Vertex(-934,435), Vertex(331,896), Vertex(-473,-830), Vertex(792,-369))),
            Square(listOf(Vertex(267,-529), Vertex(284,-188), Vertex(608,-546), Vertex(625,-205))),
            Square(listOf(Vertex(-653,594), Vertex(609,974), Vertex(-273,-668), Vertex(989,-288))),
            Square(listOf(Vertex(569,-289), Vertex(622,-92), Vertex(766,-342), Vertex(819,-145))),
            Square(listOf(Vertex(-166,-113), Vertex(483,155), Vertex(102,-762), Vertex(751,-494))),
            Square(listOf(Vertex(-982,210), Vertex(-485,942), Vertex(-250,-287), Vertex(247,445))),
            Square(listOf(Vertex(-808,687), Vertex(-487,965), Vertex(-530,366), Vertex(-209,644))),
            Square(listOf(Vertex(562,783), Vertex(564,935), Vertex(714,781), Vertex(716,933))),
            Square(listOf(Vertex(100,655), Vertex(723,679), Vertex(124,32), Vertex(747,56))),
            Square(listOf(Vertex(-649,-212), Vertex(-593,858), Vertex(421,-268), Vertex(477,802))),
            Square(listOf(Vertex(-814,28), Vertex(-619,727), Vertex(-115,-167), Vertex(80,532))),
            Square(listOf(Vertex(-264,173), Vertex(-61,462), Vertex(25,-30), Vertex(228,259)))
        )
    }

}