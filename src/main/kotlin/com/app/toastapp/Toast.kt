package com.app.toastapp

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.scene.media.AudioClip

class Toast {
    private var config = ToastConfig()
    private var fxmlLoader = FXMLLoader(javaClass.getResource("toast-view.fxml"))
    private val parent: Parent = fxmlLoader.load()
    private val controller: ToastController = fxmlLoader.getController()
    private val toastWindow = ToastWindow()
    private lateinit var sound: AudioClip
    private val textSection = ToastTextSection()

    private class ToastWindow {
        private var owner: Stage = Stage()
        var window: Stage = Stage()

        init {
            owner.initStyle(StageStyle.UTILITY)
            owner.opacity = 0.0
            owner.height = 0.0
            owner.width = 0.0

            window.initStyle(StageStyle.TRANSPARENT)
            window.isAlwaysOnTop = true
            window.isResizable = true
        }

        fun setScene(scene: Scene) {
            window.scene = scene
            window.scene.fill = Color.TRANSPARENT
            window.sizeToScene()
        }

        fun show(config: ToastConfig) {
            owner.show()
            window.initOwner(owner)
            window.show()

            window.x = config.placement.getX(config)
            window.y = when(config.placement) {
                ToastPlacements.LEFT_DOWN_CORNER -> config.placement.getY(config) - window.height + config.minHeight
                ToastPlacements.RIGHT_DOWN_CORNER -> config.placement.getY(config) - window.height + config.minHeight
                else -> config.placement.getY(config)
            }
        }

        fun getStage(): Stage {
            return window
        }
    }

    private class ToastTextSection {
        private var title = ToastText()
        private var message = ToastText()
        private var appName = ToastText()

        fun setUp(title_: String, message_: String, appName_: String, controller: ToastController, config: ToastConfig) {
            title.parseString(title_)
            message.parseString(message_)
            appName.parseString(appName_)

            controller.addText(title.getText(config))
            controller.addText(message.getText(config))
            controller.addText(appName.getText(config))
        }
    }

    class Builder {
        private var config = ToastConfig()

        fun setTitle(str: String): Builder {
            config.title = str
            return this
        }

        fun setMessage(str: String): Builder {
            config.message = str
            return this
        }

        fun setAppName(str: String): Builder {
            config.appName = str
            return this
        }

        fun setImage(str: String): Builder {
            config.image = str
            return this
        }

        fun build(): Toast {
            val toast = Toast()
            toast.config = config
            toast.build()
            return toast
        }
    }

    private fun build() {
        val scene = Scene(parent)
        scene.stylesheets.add(config.css)
        toastWindow.setScene(scene)

        sound = AudioClip(javaClass.getResource(config.pathToSound)!!.toExternalForm())

        controller.setUp(config)
        controller.setImage(config.imageType.getImage(config))
        textSection.setUp(config.title, config.message, config.appName, controller, config)
        controller.setButtons(config.widget.getWidget(toastWindow.getStage(), config), config)
    }

    fun start() {
        toastWindow.show(config)
        sound.play()
        config.animationType.openAnimation(parent, config)
        val thread = Thread {
            try {
                Thread.sleep(config.openTime.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            config.animationType.closeAnimation(parent, config)
        }
        Thread(thread).start()
    }
}