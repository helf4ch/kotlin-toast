package com.app.toastapp

import javafx.scene.layout.HBox
import javafx.stage.Stage

interface IWidget {
    fun getWidget(stage: Stage, config: ToastConfig): HBox
}

enum class ToastWidget {
    NONE,
    NOT_EMPTY
}
