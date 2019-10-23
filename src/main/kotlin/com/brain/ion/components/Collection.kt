package com.brain.ion.components

class Collection(
		override val id: String,
		vararg components: Component,
		componentList: MutableList<Component> = mutableListOf(*components)
) : Component, MutableList<Component> by componentList{
	
	override var onRender: (Component) -> Unit = emptyFunction
	
	constructor(c: Collection): this(c.id, *c.toTypedArray())
	
	override fun getCollection(): List<Component> {
		if (onRender != emptyFunction) {
			onRender.invoke(this)
		}
		
		return this.toList()
	}
	
	override fun clone(): Component {
		return Collection(this)
	}
	
}