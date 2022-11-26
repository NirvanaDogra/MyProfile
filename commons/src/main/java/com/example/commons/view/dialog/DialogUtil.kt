package com.example.commons.view.dialog

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog

class DialogUtil {
    fun showAlertDialog(context: Context, message: String) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .create()
            .show()
    }

    fun showAlertDialog(
        context: Activity,
        title: String,
        message: String,
        confirmationText: String = "ok"
    ) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setTitle(title)
            .setPositiveButton(confirmationText) { _, _ ->
                context.finish()
            }
            .create()
            .show()
    }
}