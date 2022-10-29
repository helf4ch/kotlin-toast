package com.app.toastapp

import javafx.scene.image.Image
import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.scene.shape.Shape

interface IImage {
    fun getImage(config: ToastConfig): Shape
}

enum class ToastImageTypes: IImage {
    EMPTY {
        override fun getImage(config: ToastConfig): Shape {
            return Rectangle(0.0, 0.0)
        }
    },
    SQAURE {
        override fun getImage(config: ToastConfig): Shape {
            var iconBorder = Rectangle(100.0, 100.0)
            iconBorder.fill = ImagePattern(Image(config.image))
            iconBorder.id = "image"
            return iconBorder
        }
    },
    CIRCLE {
        override fun getImage(config: ToastConfig): Shape {
            var iconBorder = Circle(50.0, 50.0, 50.0)
            iconBorder.fill = ImagePattern(Image(config.image))
            iconBorder.id = "image"
            return iconBorder
        }
    }
}