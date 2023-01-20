package kz.kd.hw_1611

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment

class DFDelete : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dfDeleteView: View = layoutInflater.inflate(R.layout.df_delete, null)
        val dfDelete: AlertDialog = AlertDialog.Builder(requireContext()).apply {
            setView(dfDeleteView)
        }.create()

        with(dfDeleteView) {
            findViewById<Button>(R.id.df_delete_cancel).setOnClickListener {
                dismiss()
            }
            findViewById<Button>(R.id.df_delete_confirm).setOnClickListener {
                dismiss()
            }
        }
        dfDelete.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dfDelete
    }
}