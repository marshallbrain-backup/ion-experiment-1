package com.brain.ion.components.vectors

import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.awt.geom.Rectangle2D

@Tag("Vector")
internal class RectangleTest {
	
	val i = 1
	val l = 10_000_000_001
	val f = 1.5f
	val d = 1.500_000_000_005
	
	@Nested
	inner class RectangleCreation() {
		@Test
		fun `using ints`() {
			val rec = Rectangle(i, i, i, i)
			val actual = rec.shape
			val expected = Rectangle2D.Double(i.toDouble(), i.toDouble(), i.toDouble(), i.toDouble())
			
			assertThat(actual).isEqualTo(expected)
		}
		@Test
		fun `using longs`() {
			val rec = Rectangle(l, l, l, l)
			val actual = rec.shape
			val expected = Rectangle2D.Double(l.toDouble(), l.toDouble(), l.toDouble(), l.toDouble())
			
			assertThat(actual).isEqualTo(expected)
		}
		@Test
		fun `using floats`() {
			val rec = Rectangle(f, f, f, f)
			val actual = rec.shape
			val expected = Rectangle2D.Double(f.toDouble(), f.toDouble(), f.toDouble(), f.toDouble())
			
			assertThat(actual).isEqualTo(expected)
		}
		@Test
		fun `using doubles`() {
			val rec = Rectangle(d, d, d, d)
			val actual = rec.shape
			val expected = Rectangle2D.Double(d, d, d, d)
			
			assertThat(actual).isEqualTo(expected)
		}
	}
	
	@Test
	fun `rectangle cloning`() {
		val expected = Rectangle(i, i, i, i)
		val actual = expected.clone()
		
		assertThat(actual).isEqualTo(expected)
	}
	
}