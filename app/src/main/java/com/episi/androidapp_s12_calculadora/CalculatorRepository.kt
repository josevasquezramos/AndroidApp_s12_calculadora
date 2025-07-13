import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

class CalculatorRepository(context: Context) {

    companion object {
        private const val PREFS_NAME = "calc_prefs"
        private const val KEY_HISTORY = "calc_history"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveOperation(operation: String) {
        val currentHistory = getHistory().toMutableList()
        currentHistory.add(0, operation) // más reciente al inicio
        if (currentHistory.size > 50) {
            currentHistory.removeAt(currentHistory.lastIndex)
        }
        val json = gson.toJson(currentHistory)
        prefs.edit { putString(KEY_HISTORY, json) }
    }

    fun getHistory(): List<String> {
        val json = prefs.getString(KEY_HISTORY, null)
        return if (json != null) {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun clearHistory() {
        prefs.edit { remove(KEY_HISTORY) }
    }
}
