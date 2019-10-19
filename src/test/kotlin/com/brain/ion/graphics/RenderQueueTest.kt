package com.brain.ion.graphics

import com.brain.ion.components.vectors.Rectangle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class RenderQueueTest {
	
	private lateinit var queue: RenderQueue
	private lateinit var rec: Rectangle
	
	@BeforeEach
	fun init() {
		queue = RenderQueue()
		rec = Rectangle(width = 10, height = 10)
	}
	
	@Nested
	inner class AddGroup {
		@Test
		fun `group is added`() {
			val group = Group("test")
			queue.addGroup(group)
			
			val map = queue.getGroupsCopy()
			
			assertThat(map).containsValue(group)
			assertThat(map).containsKey("test")
		}
		
		@Test
		fun `id already exist`() {
			val group = Group("test")
			queue.addGroup(group)
			
			assertThat(queue.addGroup(group)).isFalse()
		}
	}
	
	@Nested
	inner class RemoveRenderGroup {
		@Test
		fun `group is removed using id`() {
			val group = Group("test")
			queue.addGroup(group)
			queue.removeRenderGroup("test")
			
			val map = queue.getGroupsCopy()
			
			assertThat(map).doesNotContainValue(group)
			assertThat(map).doesNotContainKey("test")
		}
		
		@Test
		fun `group is removed using group instance`() {
			val group = Group("test")
			queue.addGroup(group)
			queue.removeRenderGroup(group)
			
			val map = queue.getGroupsCopy()
			
			assertThat(map).doesNotContainValue(group)
			assertThat(map).doesNotContainKey("test")
		}
		
		@Test
		fun `id does not exist`() {
			assertThat(queue.removeRenderGroup("test")).isFalse()
		}
		
		@Test
		fun `group does not exist`() {
			val group = Group("test")
			
			assertThat(queue.removeRenderGroup(group)).isFalse()
		}
	}
	
	@Nested
	inner class AddToQueue {
		@Test
		fun `vector is added`() {
			val group = Group("test")
			queue.addGroup(group)
			queue.addToQueue("test", rec)
			
			assertThat(group.getQueueCopy()).contains(rec)
		}
	}
	
	@Nested
	inner class RemoveFromQueue {
		@Test
		fun `vector is removed`() {
			val group = Group("test")
			queue.addGroup(group)
			queue.addToQueue("test", rec)
			queue.removeFromQueue("test", rec)
			
			assertThat(group.getQueueCopy()).doesNotContain(rec)
		}
	}
}