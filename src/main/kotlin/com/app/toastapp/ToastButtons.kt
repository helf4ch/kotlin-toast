package com.app.toastapp

import javafx.scene.layout.HBox
import javafx.stage.Stage

interface IButtons {
    fun getButtons(stage: Stage, config: ToastConfig): HBox
}

enum class ToastButtons {
    NONE,
    NOT_EMPTY
}
