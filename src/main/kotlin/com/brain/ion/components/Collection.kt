package com.brain.ion.components

import com.brain.ion.graphics.IonGraphics

class Collection(
		override val id: String,
		vararg components: Component,
		override var x: Number = 0,
		override var y: Number = 0,
		override var visible: Boolean = true,
		componentList: MutableList<Component> = mutableListOf(*components)
) : Component, MutableList<Component> by componentList{
	override var onRender: (Component) -> Unit = renderEmpty
	
	constructor(c: Collection): this(
			c.id,
			*c.toTypedArray(),
			x = c.x,
			y = c.y,
			visible = c.visible
	)
	
	fun addAll(vararg components: Component) {
		addAll(components)
	}
	
	override fun getCollection(graphics: IonGraphics): List<Component> {
		if (onRender != renderEmpty) {
			onRender.invoke(this)
		}
		
		return this.toList()
	}
	
	override fun clone(): Component {
		return Collection(this)
	}
	
}