package com.brain.ion.components.vectors

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.awt.geom.Ellipse2D

@Tag("Vector")
internal class CircleTest {
	
	val i = 1
	val l = 10_000_000_001
	val f = 1.5f
	val d = 1.500_000_000_005
	
	@Nested
	inner class CircleCreation {
		@Test
		fun `using ints`() {
			val v = Circle("", i, i, i)
			val actual = v.shape
			val expected = Ellipse2D.Double(i.toDouble(), i.toDouble(), i.toDouble(), i.toDouble())
			
			Assertions.assertThat(actual).isEqualTo(expected)
		}
		
		@Test
		fun `using longs`() {
			val v = Circle("", l, l, l)
			val actual = v.shape
			val expected = Ellipse2D.Double(l.toDouble(), l.toDouble(), l.toDouble(), l.toDouble())
			
			Assertions.assertThat(actual).isEqualTo(expected)
		}
		
		@Test
		fun `using floats`() {
			val v = Circle("", f, f, f)
			val actual = v.shape
			val expected = Ellipse2D.Double(f.toDouble(), f.toDouble(), f.toDouble(), f.toDouble())
			
			Assertions.assertThat(actual).isEqualTo(expected)
		}
		
		@Test
		fun `using doubles`() {
			val v = Circle("", d, d, d)
			val actual = v.shape
			val expected = Ellipse2D.Double(d, d, d, d)
			
			Assertions.assertThat(actual).isEqualTo(expected)
		}
	}
	
	@Test
	fun `circle cloning`() {
		val expected = Circle("", i, i, i)
		val actual = expected.clone()
		
		Assertions.assertThat(actual).isEqualTo(expected)
	}
	
}