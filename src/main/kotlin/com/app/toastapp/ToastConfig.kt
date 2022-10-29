package com.app.toastapp

import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.text.TextAlignment
import javafx.stage.Stage

class MyWidget: IWidget {
    override fun getWidget(stage: Stage, config: ToastConfig): HBox {
        val hbox = HBox()
        val but1 = Button("left")
        val but2 = Button("right")
        but1.onAction = EventHandler {
            stage.close()
        }
        but2.onAction = EventHandler {
            val clipboard = Clipboard.getSystemClipboard()
            val content = ClipboardContent()
            content.putString(config.message)
            clipboard.setContent(content)
        }
        but1.maxWidth = Double.POSITIVE_INFINITY
        but2.maxWidth = Double.POSITIVE_INFINITY
        hbox.children.addAll(but1, but2)
        hbox.alignment = Pos.CENTER
        HBox.setHgrow(but1, Priority.ALWAYS)
        HBox.setHgrow(but2, Priority.ALWAYS)
        return hbox
    }
}

class ToastConfig {
    var alpha = 0.9
    var openTime = 7000.0

    var width = 350.0
    var minHeight = 100.0

    var title = "TITLE"
    var message = "MESSAGE"
    var appName = "APP NAME"

    var fontFamily = "Comic Sans MS"
    var fontSize = 14.0
    var textAlignment = TextAlignment.CENTER

    var animationType = ToastAnimations.TRANSLATE
    var placement = ToastPlacements.RIGHT_DOWN_CORNER

    var imageType = ToastImageTypes.CIRCLE
    var image = "https://avatars.mds.yandex.net/i?id=ee0a8cd0c69a411b7fee131fde2b4980-3732926-images-thumbs&n=13"
    var imageWidth = 100.0

    var pathToSound = "/other/aughh.mp3"

    var widgetType = ToastWidget.NOT_EMPTY
    var widget: IWidget = MyWidget()

    var contentPaddingTop = 5.0
    var contentPaddingBottom = 5.0
    var contentPaddingLeft = 20.0
    var contentPaddingRight = 20.0

    var contentSpacing = 10.0
    var contentImageBetweenTextSpacing = 10.0
    var contentTextSpacing = 10.0

    var buttonsPaddingTop = 0.0
    var buttonsPaddingBottom = 0.0
    var buttonsPaddingLeft = 20.0
    var buttonsPaddingRight = 20.0
    var buttonsBetweenSpacing = 20.0

    var css = javaClass.getResource("style.css")?.toExternalForm()
}