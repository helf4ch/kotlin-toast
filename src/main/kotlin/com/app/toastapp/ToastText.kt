package com.app.toastapp

import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.scene.text.TextFlow
import java.util.*

class ToastTextStyles {
    companion object {
        @JvmStatic
        fun bold(): String {
            return "-fx-font-weight: bold; "
        }

        @JvmStatic
        fun italic(): String {
            return "-fx-font-style: italic; "
        }
    }
}

class ToastText {
    private var string = String();
    private var strings =  Vector<String>()
    private var styles =  Vector<String>()

    fun parseString(str: String) {
        string = str
        string = string.replace("<br>", "\n")

        var count = if (string[0] == '*') 1 else 0
        var isBold = false
        var isItalic = false
        var past = 0
        var prev = string[0]
        var waitingForClosers = false
        var i = 1
        while (i < string.length) {
            if (string[i] != '*' && count == 0) {
                while (i < string.length) {
                    if (string[i] == '*') {
                        addText(false, false, string.substring(past, i))
                        past = i
                        ++count
                        break;
                    }
                    prev = string[i]
                    ++i
                }
            } else if ((string[i] == '*' && prev == '*') || string[i] == '*' && prev != '*') {
                ++count
            } else if (string[i] != '*' && prev == '*') {
                when (count) {
                    1 -> isItalic = true
                    2 -> isBold = true
                    3 -> {
                        isItalic = true
                        isBold = true
                    }
                }
                waitingForClosers = true
                var bufCount = 0
                while (i < string.length) {
                    if ((string[i] == '*' && prev == '*') || string[i] == '*' && prev != '*') {
                        ++bufCount
                    }
                    if (count == bufCount) {
                        waitingForClosers = false
                        break
                    }
                    prev = string[i]
                    ++i
                }
                if (!waitingForClosers) {
                    addText(isBold, isItalic, string.substring(past + count, i - count + 1))
                    past = i + 1
                    count = 0
                } else {
                    addText(false, false, string.substring(past, i))
                    past = i
                }
                isBold = false
                isItalic = false
            }
            if (i < string.length) {
                prev = string[i]
                ++i
            }
        }
        if (past + 1 != i) {
            addText(false, false, string.substring(past, i))
        }
    }

    fun getText(config: ToastConfig): TextFlow {
        var result = TextFlow()
        result.textAlignment = config.textAlignment
        for (i in 0 until strings.size) {
            var text = Text(strings[i])
            text.style = styles[i]
            text.font = Font.font(config.fontFamily, config.fontSize)
            result.children.add(text)
        }
        return result
    }

    private fun addText(isBold: Boolean, isItalic: Boolean, str: String) {
        var style = String()
        if (isBold) {
            style += ToastTextStyles.bold()
        }
        if (isItalic) {
            style += ToastTextStyles.italic()
        }
        strings.addElement(str)
        styles.addElement(style)
    }
}