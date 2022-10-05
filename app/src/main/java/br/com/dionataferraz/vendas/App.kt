package br.com.dionataferraz.vendas

import android.app.Application
import android.content.Context
import java.text.DecimalFormat

class App : Application() {
    companion object {
        private lateinit var instance: App
        val context: Context
            get() = instance
    }

    init {
        instance = this
    }
}

fun Double.toCurrency(): String {
    if (this == 0.0) {
        return "$0.00"
    }
    val format = DecimalFormat("$#,###.00")
    format.isDecimalSeparatorAlwaysShown = false
    return format.format(this).toString()
}