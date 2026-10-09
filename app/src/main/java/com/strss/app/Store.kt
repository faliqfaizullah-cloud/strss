package com.strss.app

import android.content.Context

class Store(c: Context) {
    private val p = c.getSharedPreferences("strss", Context.MODE_PRIVATE)

    fun setLimit(pkg: String, minutes: Int) {
        p.edit().apply { if (minutes <= 0) remove("limit_$pkg") else putInt("limit_$pkg", minutes) }.apply()
    }

    fun limits(): Map<String, Int> =
        p.all.filterKeys { it.startsWith("limit_") }
            .map { it.key.removePrefix("limit_") to (it.value as Int) }.toMap()

    var bedEnabled: Boolean
        get() = p.getBoolean("bed_on", true)
        set(v) { p.edit().putBoolean("bed_on", v).apply() }
    var bedMin: Int
        get() = p.getInt("bed", 23 * 60)
        set(v) { p.edit().putInt("bed", v).apply() }
    var wakeMin: Int
        get() = p.getInt("wake", 7 * 60)
        set(v) { p.edit().putInt("wake", v).apply() }
    var windDown: Int
        get() = p.getInt("wind", 30)
        set(v) { p.edit().putInt("wind", v).apply() }
    var premium: Boolean
        get() = p.getBoolean("premium", true)
        set(v) { p.edit().putBoolean("premium", v).apply() }
}
