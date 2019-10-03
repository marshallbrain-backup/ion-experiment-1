package com.brain.ion.graphics

import com.brain.ion.components.vectors.Rectangle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested

internal class RenderStackTest {
	
	private lateinit var stack: RenderStack
	private lateinit var rec: Rectangle
	
	@BeforeEach
	fun init() {
		stack = RenderStack()
		rec = Rectangle(width = 10, height = 10)
	}
	
	@Nested
	inner class AddGroup {
		@Test
		fun `group is added`() {
			val group = Group("test")
			stack.addGroup(group)
			
			val map = stack.getGroupsCopy()
			
			assertThat(map).containsValue(group)
			assertThat(map).containsKey("test")
		}
		
		@Test
		fun `id already exist`() {
			val group = Group("test")
			stack.addGroup(group)
			
			assertThat(stack.addGroup(group)).isFalse()
		}
	}
	
	@Nested
	inner class RemoveRenderGroup {
		@Test
		fun `group is removed using id`() {
			val group = Group("test")
			stack.addGroup(group)
			stack.removeRenderGroup("test")
			
			val map = stack.getGroupsCopy()
			
			assertThat(map).doesNotContainValue(group)
			assertThat(map).doesNotContainKey("test")
		}
		
		@Test
		fun `group is removed using group instance`() {
			val group = Group("test")
			stack.addGroup(group)
			stack.removeRenderGroup(group)
			
			val map = stack.getGroupsCopy()
			
			assertThat(map).doesNotContainValue(group)
			assertThat(map).doesNotContainKey("test")
		}
		
		@Test
		fun `id does not exist`() {
			assertThat(stack.removeRenderGroup("test")).isFalse()
		}
		
		@Test
		fun `group does not exist`() {
			val group = Group("test")
			
			assertThat(stack.removeRenderGroup(group)).isFalse()
		}
	}
	
	@Nested
	inner class AddToQueue {
		@Test
		fun `vector is added`() {
			val group = Group("test")
			stack.addGroup(group)
			stack.addToQueue("test", rec)
			
			assertThat(group.getQueueCopy()).contains(rec)
		}
	}
	
	@Nested
	inner class RemoveFromQueue {
		@Test
		fun `vector is removed`() {
			val group = Group("test")
			stack.addGroup(group)
			stack.addToQueue("test", rec)
			stack.removeFromQueue("test", rec)
			
			assertThat(group.getQueueCopy()).doesNotContain(rec)
		}
	}
}