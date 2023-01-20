package kz.kd.hw_1611

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class DFDelete: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dfDeleteView: View = layoutInflater.inflate(R.layout.df_delete, null)

        with(dfDeleteView) {
            findViewById<Button>(R.id.df_delete_cancel).setOnClickListener {
                dismiss()
            }
            findViewById<Button>(R.id.df_delete_confirm).setOnClickListener {
                dismiss()
            }
        }
        return dfDeleteView
    }
}