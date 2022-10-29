package com.app.toastapp

import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.shape.Shape
import javafx.scene.text.TextFlow

class ToastController {
    @FXML private var mainVBox = VBox()
    @FXML private var contentHBox = HBox()
    @FXML private var imageBox = BorderPane()
    @FXML private var textVBox = VBox()

    fun setUp(config: ToastConfig) {
        mainVBox.maxWidth = config.width
        mainVBox.minWidth = config.width
        mainVBox.minHeight = config.minHeight
        mainVBox.padding = Insets(
            config.contentPaddingTop,
            config.contentPaddingRight,
            config.contentPaddingBottom,
            config.contentPaddingLeft
        )
        mainVBox.spacing = config.contentSpacing

        if (config.imageType != ToastImageTypes.EMPTY) {
            contentHBox.spacing = config.contentImageBetweenTextSpacing
            imageBox.maxWidth = config.imageWidth
        }

        textVBox.spacing = config.contentTextSpacing
    }

    fun addText(textFlow: TextFlow) {
        textVBox.children.add(textFlow)
    }

    fun setImage(image: Shape) {
        imageBox.center = image
    }

    fun setButtons(buttons: HBox, config: ToastConfig) {
        if (config.buttonsType == ToastButtons.NONE) {
            return
        }
        buttons.padding = Insets(
            config.buttonsPaddingTop,
            config.buttonsPaddingRight,
            config.buttonsPaddingBottom,
            config.buttonsPaddingLeft
        )
        buttons.spacing = config.buttonsBetweenSpacing
        mainVBox.children.add(buttons)
    }
}