package com.brain.ion.graphics

import com.brain.ion.components.Component

interface Group: Renderable, MutableList<Renderable> {
	
	val index: Int
	val parent: Renderable?
	
	fun newGroup(): Group
	fun render(graphics: IonGraphics)
}