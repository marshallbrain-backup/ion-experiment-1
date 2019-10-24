package com.brain.ion.graphics

interface RenderQueue: MutableList<Renderable> {
	
	fun newGroup(add: Boolean = true): Group
	fun render(graphics: IonGraphics)
}