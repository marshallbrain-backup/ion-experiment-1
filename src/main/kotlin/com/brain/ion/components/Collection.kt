package com.brain.ion.components

class Collection(
		override val id: String,
		vararg components: Component,
		componentList: MutableList<Component> = mutableListOf(*components)
) : Component, MutableList<Component> by componentList{
	
	override var onRender: (Component) -> Unit = emptyFunction
	
	override fun clone() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun getCollection(): List<Component> {
		if (onRender != emptyFunction) {
			onRender.invoke(this)
		}
		
		return this.toList()
	}
	
}