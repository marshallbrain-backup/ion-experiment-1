package com.brain.ion.graphics

import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested
import java.awt.Rectangle

internal class RenderStackTest {
	
	private val stack = RenderStack()
	
	@Nested
	inner class AddGroup {
		@Test
		fun `group is added`() {
			val group = Group("test")
			stack.addGroup(group)
			
			val map = stack.getGroupsCopy()
			
			assertThat(map).containsValue(group)
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
		fun `group is removed using group instance`() {
		}
		
		@Test
		fun `id does not exist`() {
		}
		
		@Test
		fun `group does not exist`() {
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