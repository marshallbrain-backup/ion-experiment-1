package com.brain.ion.components.vectors

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.awt.BasicStroke
import java.awt.Color

internal class StyleTest {
	
	@Test
	fun `initiation of stroke`() {
		val actual = Style("FFFFFF", 1, 2)
		val expected = Style(Color(0,0,0,0), Color(255,255,255,255), BasicStroke(2f))
		
		assertThat(actual).isEqualTo(expected)
	}
	
	@Test
	fun `initiation of fill`() {
		val actual = Style("FFFFFF", 1)
		val expected = Style(Color(255,255,255,255), Color(0,0,0,0), BasicStroke(0f))
		
		assertThat(actual).isEqualTo(expected)
	}
	
	@Test
	fun `initiation of all`() {
		val actual = Style("FFFFFF", "FFFFFF", 1, 1, 2)
		val expected = Style(Color(255,255,255,255), Color(255,255,255,255), BasicStroke(2f))
		
		assertThat(actual).isEqualTo(expected)
	}
	
	@Test
	fun `initiation of none`() {
		val actual = Style()
		val expected = Style(Color(0,0,0,0), Color(0,0,0,0), BasicStroke(0f))
		
		assertThat(actual).isEqualTo(expected)
	}
	
	@Test
	fun cloning () {
		val expected = Style("FFFFFF", "FFFFFF", 1, 1, 2)
		val actual = Style(expected)
		
		assertThat(actual).isEqualTo(expected)
	}
	
}