package com.brain.ion

import java.awt.Canvas
import javax.swing.JFrame

class Ion () {

	init {

		var mainFrame = JFrame();

		mainFrame.title = "Pulsar";
		mainFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE;
		mainFrame.isResizable = false;
		mainFrame.isVisible = true;

		var canvas = Canvas()
		canvas.setBounds(50, 50, 1600, 900)
		mainFrame.add(canvas)

		mainFrame.pack();

	}

}