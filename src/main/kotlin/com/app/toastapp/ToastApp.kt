package com.app.toastapp

import javafx.application.Application
import javafx.stage.Stage

class ToastApp: Application() {
    override fun start(primaryStage: Stage?) {
        var toast = Toast.Builder()
            .setTitle("DED ATTACK")
//            .setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt 1ut Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut")
//            .setMessage("мда")
            .setMessage("**bold** <br> *italic<br>* ***bold and italic*** and dead!!**!inside")
            .setAppName("call the **police**")
            .setImage("https://img09.rl0.ru/afisha/-x-i/daily.afisha.ru/uploads/images/2/9f/29fde671d4bd62aafcd52761ee9ba5c7.jpg")
            .build()
        toast.start()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(ToastApp::class.java)
        }
    }
}