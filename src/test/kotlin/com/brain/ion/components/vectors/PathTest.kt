package com.brain.ion.components.vectors

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.awt.geom.Area
import java.awt.geom.Path2D

internal class PathTest {
	
	val i = 1
	val l = 10_000_000_001
	val f = 1.5f
	val d = 1.500_000_000_005
	
	@Nested
	inner class PathCreation() {
		@Test
		fun `using ints`() {
			val actual = actualSetup(i).shape
			val expected = expectedSetup(i.toDouble())
			
			assertThat(Area(actual).equals(Area(expected)))
		}
		@Test
		fun `using longs`() {
			val actual = actualSetup(l).shape
			val expected = expectedSetup(l.toDouble())
			
			assertThat(Area(actual).equals(Area(expected)))
		}
		@Test
		fun `using floats`() {
			val actual = actualSetup(f).shape
			val expected = expectedSetup(f.toDouble())
			
			assertThat(Area(actual).equals(Area(expected)))
		}
		@Test
		fun `using doubles`() {
			val actual = actualSetup(d).shape
			val expected = expectedSetup(d)
			
			assertThat(Area(actual).equals(Area(expected)))
		}
	}
	
	@Test
	fun `path cloning`() {
		val expected = Path().move(i, i)
		val actual = expected.clone()
		
		assertThat(actual).isEqualTo(expected)
	}
	
	private fun actualSetup(v: Number): Path {
		return Path()
				.moveAbs(v, v).move(v, v)
				.lineAbs(v, v).line(v, v)
				.lineXAbs(v).lineX(v)
				.lineYAbs(v).lineY(v)
				.close()
	}
	
	private fun expectedSetup(v: Double): Path2D {
		val expected = Path2D.Double()
		expected.moveTo(v, v)
		expected.moveTo(v + v, v + v)
		expected.lineTo(v, v)
		expected.lineTo(v + v, v + v)
		expected.lineTo(v + v, v + v)
		expected.lineTo(v + v + v, v + v)
		expected.lineTo(v + v + v, v + v)
		expected.lineTo(v + v + v, v + v + v)
		expected.closePath()
		return expected
	}
	
}