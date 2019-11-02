package com.brain.ion.handler

class EventHandler(
		private val map: MutableMap<Int, MutableSet<(Any) -> Unit>> = mutableMapOf()
) : Map<Int, MutableSet<(Any) -> Unit>> by map {
	
	fun add(target: Int, trigger: (Any) -> Unit) {
		val list = map.getOrPut(target) { mutableSetOf() }
		list.add(trigger)
	}
	
	fun trigger(source: Any, target: Int) {
		get(target)?.forEach { it.invoke(source) }
	}
	
}