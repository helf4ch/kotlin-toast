package com.app.toastapp

import javafx.animation.FadeTransition
import javafx.animation.TranslateTransition
import javafx.event.EventHandler
import javafx.scene.Parent
import javafx.util.Duration
import javafx.application.Platform

interface IAnimation {
    fun openAnimation(parent: Parent, config: ToastConfig)
    fun closeAnimation(parent: Parent, config: ToastConfig)
}

enum class ToastAnimations: IAnimation {
    FADE{
        override fun openAnimation(parent: Parent, config: ToastConfig) {
            val anim = FadeTransition(Duration.millis(1500.0), parent)
            anim.fromValue = 0.0
            anim.toValue = config.alpha
            anim.cycleCount = 1
            anim.play()
        }

        override fun closeAnimation(parent: Parent, config: ToastConfig) {
            val anim = FadeTransition(Duration.millis(1500.0), parent)
            anim.fromValue = config.alpha
            anim.toValue = 0.0
            anim.cycleCount = 1
            anim.onFinished = EventHandler {
                Platform.exit()
                System.exit(0)
            }
            anim.play()
        }
    },
    TRANSLATE {
        override fun openAnimation(parent: Parent, config: ToastConfig) {
            val anim = TranslateTransition(Duration.millis(1500.0), parent)
            anim.fromX = when(config.placement) {
                ToastPlacements.LEFT_UP_CORNER -> -config.width
                ToastPlacements.LEFT_DOWN_CORNER -> -config.width
                ToastPlacements.RIGHT_UP_CORNER -> config.width
                ToastPlacements.RIGHT_DOWN_CORNER -> config.width
            }
            anim.toX = 0.0
            anim.cycleCount = 1
            anim.play()
        }

        override fun closeAnimation(parent: Parent, config: ToastConfig) {
            val anim = TranslateTransition(Duration.millis(1500.0), parent)
            anim.fromX = 0.0
            anim.toX = when(config.placement) {
                ToastPlacements.LEFT_UP_CORNER -> -config.width
                ToastPlacements.LEFT_DOWN_CORNER -> -config.width
                ToastPlacements.RIGHT_UP_CORNER -> config.width
                ToastPlacements.RIGHT_DOWN_CORNER -> config.width
            }
            anim.cycleCount = 1
            anim.onFinished = EventHandler {
                Platform.exit()
                System.exit(0)
            }
            anim.play()
        }
    }
}