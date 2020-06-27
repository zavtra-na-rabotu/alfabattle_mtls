package ru.alfabattle.mtls.extensions

import java.text.DecimalFormat

private val df = DecimalFormat("0.00")

fun String.round() = df.format(this.toFloat())