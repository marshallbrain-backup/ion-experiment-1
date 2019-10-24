package com.brain.ion.graphics

import com.brain.ion.components.Component

interface Group: Renderable, MutableList<Renderable> {
	
	fun newGroup(add: Boolean = true): Group
	fun render(graphics: IonGraphics)
}