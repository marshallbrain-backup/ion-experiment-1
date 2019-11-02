package com.brain.ion.handler

class EventHandler(
		private val map: MutableMap<Event, MutableSet<(Any) -> Unit>> = mutableMapOf()
) : Map<Event, MutableSet<(Any) -> Unit>> by map {
	
	fun add(target: Event, trigger: (Any) -> Unit) {
		val list = map.getOrPut(target) { mutableSetOf() }
		list.add(trigger)
	}
	
	fun trigger(source: Any, target: Event) {
		get(target)?.forEach { it.invoke(source) }
	}
	
}