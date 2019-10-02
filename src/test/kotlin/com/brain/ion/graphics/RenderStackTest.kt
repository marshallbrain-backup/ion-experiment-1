package com.brain.ion.graphics

import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested
import java.awt.Rectangle

internal class IonGraphicsTest {
	
	private val graphics = IonGraphics(Rectangle(0, 0))
	
	@Nested
	inner class CreateRenderGroup {
		@Test
		fun `group is created`() {
			graphics.createRenderGroup("test")
			
			assertThat(graphics.renderGroups).containsKey("test")
			assertThat(graphics.renderGroups).containsValue(IonGraphics.Group("test"))
		}
		
		@Test
		fun `id already exist`() {
		}
	}
	
	@Nested
	inner class RemoveRenderGroup {
		@Test
		fun `group is removed using id`() {
		}
		
		@Test
		fun `id already exist`() {
		}
	}
	
	@Nested
	inner class AddToQueue {
		@Test
		fun `vector is added`() {
		}
		
		@Test
		fun `id already exist`() {
		}
	}
	
	@Nested
	inner class RemoveFromQueue {
		@Test
		fun `vector is removed`() {
		}
		
		@Test
		fun `id already exist`() {
		}
	}
}