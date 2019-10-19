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
		rec = Rectangle("test", width = 10, height = 10)
	}
	
	@Nested
	inner class AddComponent {
		@Test
		fun `group is added`() {
			queue.addComponent(rec)
			
			val map = queue.getComponentCopy()
			
			assertThat(map).containsValue(rec)
			assertThat(map).containsKey("test")
		}
		
		@Test
		fun `id already exist`() {
			queue.addComponent(rec)
			
			assertThat(queue.addComponent(rec)).isFalse()
		}
	}
	
	@Nested
	inner class RemoveRenderGroup {
		@Test
		fun `group is removed using id`() {
			queue.addComponent(rec)
			queue.removeComponent("test")
			
			val map = queue.getComponentCopy()
			
			assertThat(map).doesNotContainValue(rec)
			assertThat(map).doesNotContainKey("test")
		}
		
		@Test
		fun `group is removed using group instance`() {
			queue.addComponent(rec)
			queue.removeComponent(rec)
			
			val map = queue.getComponentCopy()
			
			assertThat(map).doesNotContainValue(rec)
			assertThat(map).doesNotContainKey("test")
		}
		
		@Test
		fun `id does not exist`() {
			assertThat(queue.removeComponent("test")).isFalse()
		}
		
		@Test
		fun `group does not exist`() {
			
			assertThat(queue.removeComponent(rec)).isFalse()
		}
	}
}