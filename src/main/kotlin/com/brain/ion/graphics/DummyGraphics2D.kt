package com.brain.ion.graphics

import java.awt.Color
import java.awt.Composite
import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.GraphicsConfiguration
import java.awt.Image
import java.awt.Paint
import java.awt.Rectangle
import java.awt.RenderingHints
import java.awt.Shape
import java.awt.Stroke
import java.awt.font.FontRenderContext
import java.awt.font.GlyphVector
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.awt.image.BufferedImageOp
import java.awt.image.ImageObserver
import java.awt.image.RenderedImage
import java.awt.image.renderable.RenderableImage
import java.text.AttributedCharacterIterator

class DummyGraphics2D: Graphics2D() {
	
	override fun getClipBounds(): Rectangle {
		throw IllegalCallerException()
	}
	
	override fun drawPolyline(p0: IntArray?, p1: IntArray?, p2: Int) {
		throw IllegalCallerException()
	}
	
	override fun rotate(p0: Double) {
		throw IllegalCallerException()
	}
	
	override fun rotate(p0: Double, p1: Double, p2: Double) {
		throw IllegalCallerException()
	}
	
	override fun drawLine(p0: Int, p1: Int, p2: Int, p3: Int) {
		throw IllegalCallerException()
	}
	
	override fun copyArea(p0: Int, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int) {
		throw IllegalCallerException()
	}
	
	override fun draw(p0: Shape?) {
		throw IllegalCallerException()
	}
	
	override fun setStroke(p0: Stroke?) {
		throw IllegalCallerException()
	}
	
	override fun getComposite(): Composite {
		throw IllegalCallerException()
	}
	
	override fun fillArc(p0: Int, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int) {
		throw IllegalCallerException()
	}
	
	override fun fill(p0: Shape?) {
		throw IllegalCallerException()
	}
	
	override fun getDeviceConfiguration(): GraphicsConfiguration {
		throw IllegalCallerException()
	}
	
	override fun getBackground(): Color {
		throw IllegalCallerException()
	}
	
	override fun clip(p0: Shape?) {
		throw IllegalCallerException()
	}
	
	override fun setPaint(p0: Paint?) {
		throw IllegalCallerException()
	}
	
	override fun drawString(p0: String, p1: Int, p2: Int) {
		throw IllegalCallerException()
	}
	
	override fun drawString(p0: String?, p1: Float, p2: Float) {
		throw IllegalCallerException()
	}
	
	override fun drawString(p0: AttributedCharacterIterator?, p1: Int, p2: Int) {
		throw IllegalCallerException()
	}
	
	override fun drawString(p0: AttributedCharacterIterator?, p1: Float, p2: Float) {
		throw IllegalCallerException()
	}
	
	override fun clipRect(p0: Int, p1: Int, p2: Int, p3: Int) {
		throw IllegalCallerException()
	}
	
	override fun shear(p0: Double, p1: Double) {
		throw IllegalCallerException()
	}
	
	override fun transform(p0: AffineTransform?) {
		throw IllegalCallerException()
	}
	
	override fun setPaintMode() {
		throw IllegalCallerException()
	}
	
	override fun getColor(): Color {
		throw IllegalCallerException()
	}
	
	override fun scale(p0: Double, p1: Double) {
		throw IllegalCallerException()
	}
	
	override fun drawImage(p0: Image?, p1: AffineTransform?, p2: ImageObserver?): Boolean {
		throw IllegalCallerException()
	}
	
	override fun drawImage(p0: BufferedImage?, p1: BufferedImageOp?, p2: Int, p3: Int) {
		throw IllegalCallerException()
	}
	
	override fun drawImage(p0: Image?, p1: Int, p2: Int, p3: ImageObserver?): Boolean {
		throw IllegalCallerException()
	}
	
	override fun drawImage(p0: Image?, p1: Int, p2: Int, p3: Int, p4: Int, p5: ImageObserver?): Boolean {
		throw IllegalCallerException()
	}
	
	override fun drawImage(p0: Image?, p1: Int, p2: Int, p3: Color?, p4: ImageObserver?): Boolean {
		throw IllegalCallerException()
	}
	
	override fun drawImage(p0: Image?, p1: Int, p2: Int, p3: Int, p4: Int, p5: Color?, p6: ImageObserver?): Boolean {
		throw IllegalCallerException()
	}
	
	override fun drawImage(p0: Image?, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int, p9: ImageObserver?): Boolean {
		throw IllegalCallerException()
	}
	
	override fun drawImage(p0: Image?, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int, p9: Color?, p10: ImageObserver?): Boolean {
		throw IllegalCallerException()
	}
	
	override fun getFontRenderContext(): FontRenderContext {
		throw IllegalCallerException()
	}
	
	override fun setXORMode(p0: Color?) {
		throw IllegalCallerException()
	}
	
	override fun addRenderingHints(p0: MutableMap<*, *>?) {
		throw IllegalCallerException()
	}
	
	override fun getRenderingHints(): RenderingHints {
		throw IllegalCallerException()
	}
	
	override fun translate(p0: Int, p1: Int) {
		throw IllegalCallerException()
	}
	
	override fun translate(p0: Double, p1: Double) {
		throw IllegalCallerException()
	}
	
	override fun setFont(p0: Font?) {
		throw IllegalCallerException()
	}
	
	override fun getFont(): Font {
		throw IllegalCallerException()
	}
	
	override fun getStroke(): Stroke {
		throw IllegalCallerException()
	}
	
	override fun fillOval(p0: Int, p1: Int, p2: Int, p3: Int) {
		throw IllegalCallerException()
	}
	
	override fun getClip(): Shape {
		throw IllegalCallerException()
	}
	
	override fun drawRenderedImage(p0: RenderedImage?, p1: AffineTransform?) {
		throw IllegalCallerException()
	}
	
	override fun dispose() {
		throw IllegalCallerException()
	}
	
	override fun setClip(p0: Int, p1: Int, p2: Int, p3: Int) {
		throw IllegalCallerException()
	}
	
	override fun setClip(p0: Shape?) {
		throw IllegalCallerException()
	}
	
	override fun setRenderingHints(p0: MutableMap<*, *>?) {
		throw IllegalCallerException()
	}
	
	override fun getTransform(): AffineTransform {
		throw IllegalCallerException()
	}
	
	override fun create(): Graphics {
		throw IllegalCallerException()
	}
	
	override fun drawOval(p0: Int, p1: Int, p2: Int, p3: Int) {
		throw IllegalCallerException()
	}
	
	override fun drawRenderableImage(p0: RenderableImage?, p1: AffineTransform?) {
		throw IllegalCallerException()
	}
	
	override fun setComposite(p0: Composite?) {
		throw IllegalCallerException()
	}
	
	override fun clearRect(p0: Int, p1: Int, p2: Int, p3: Int) {
		throw IllegalCallerException()
	}
	
	override fun drawPolygon(p0: IntArray?, p1: IntArray?, p2: Int) {
		throw IllegalCallerException()
	}
	
	override fun setTransform(p0: AffineTransform?) {
		throw IllegalCallerException()
	}
	
	override fun getPaint(): Paint {
		throw IllegalCallerException()
	}
	
	override fun fillRect(p0: Int, p1: Int, p2: Int, p3: Int) {
		throw IllegalCallerException()
	}
	
	override fun drawGlyphVector(p0: GlyphVector?, p1: Float, p2: Float) {
		throw IllegalCallerException()
	}
	
	override fun drawRoundRect(p0: Int, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int) {
		throw IllegalCallerException()
	}
	
	override fun getFontMetrics(p0: Font?): FontMetrics {
		throw IllegalCallerException()
	}
	
	override fun fillPolygon(p0: IntArray?, p1: IntArray?, p2: Int) {
		throw IllegalCallerException()
	}
	
	override fun setColor(p0: Color?) {
		throw IllegalCallerException()
	}
	
	override fun setRenderingHint(p0: RenderingHints.Key?, p1: Any?) {
		throw IllegalCallerException()
	}
	
	override fun fillRoundRect(p0: Int, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int) {
		throw IllegalCallerException()
	}
	
	override fun drawArc(p0: Int, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int) {
		throw IllegalCallerException()
	}
	
	override fun getRenderingHint(p0: RenderingHints.Key?): Any {
		throw IllegalCallerException()
	}
	
	override fun hit(p0: Rectangle?, p1: Shape?, p2: Boolean): Boolean {
		throw IllegalCallerException()
	}
	
	override fun setBackground(p0: Color?) {
		throw IllegalCallerException()
	}
	
}