@file:Suppress("NOTHING_TO_INLINE")

package tech.thdev.jsonplaceholderexample.util

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import tech.thdev.jsonplaceholderexample.R

fun Context.createDialog(titleId: Int,
                                messageRes: Int,
                                negativeButtonListener: ((dialog: DialogInterface?, which: Int) -> Unit)? = null,
                                positiveButtonListener: ((dialog: DialogInterface?, which: Int) -> Unit)? = null) =
        createDialog(getString(titleId), getString(messageRes), negativeButtonListener, positiveButtonListener)

fun Context.createDialog(title: String?,
                                message: String?,
                                negativeButtonListener: ((dialog: DialogInterface?, which: Int) -> Unit)? = null,
                                positiveButtonListener: ((dialog: DialogInterface?, which: Int) -> Unit)? = null) =
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(this.getText(R.string.label_no), negativeButtonListener)
                .setPositiveButton(this.getText(R.string.label_yes), positiveButtonListener)
                .setCancelable(true)
                .create()!!