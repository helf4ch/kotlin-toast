package com.app.toastapp

import javafx.stage.Screen

interface IPlacement {
    fun getX(config: ToastConfig): Double
    fun getY(config: ToastConfig): Double
}

enum class ToastPlacements: IPlacement {
    LEFT_UP_CORNER {
        override fun getX(config: ToastConfig): Double {
            var screenBounds = Screen.getPrimary().visualBounds
            return screenBounds.minX + 20
        }

        override fun getY(config: ToastConfig): Double {
            var screenBounds = Screen.getPrimary().visualBounds
            return screenBounds.minY + 20
        }
    },
    RIGHT_UP_CORNER {
        override fun getX(config: ToastConfig): Double {
            var screenBounds = Screen.getPrimary().visualBounds
            return screenBounds.maxX - config.width - 20
        }

        override fun getY(config: ToastConfig): Double {
            var screenBounds = Screen.getPrimary().visualBounds
            return screenBounds.minY + 20
        }
    },
    LEFT_DOWN_CORNER {
        override fun getX(config: ToastConfig): Double {
            var screenBounds = Screen.getPrimary().visualBounds
            return screenBounds.minX + 20
        }

        override fun getY(config: ToastConfig): Double {
            var screenBounds = Screen.getPrimary().visualBounds
            return screenBounds.maxY - config.minHeight - 20
        }
    },
    RIGHT_DOWN_CORNER {
        override fun getX(config: ToastConfig): Double {
            var screenBounds = Screen.getPrimary().visualBounds
            return screenBounds.maxX - config.width - 20
        }

        override fun getY(config: ToastConfig): Double {
            var screenBounds = Screen.getPrimary().visualBounds
            return screenBounds.maxY - config.minHeight - 20
        }
    }
}