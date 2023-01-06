package kz.kd.hw_1611

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val etAmount: EditText = itemView.findViewById(R.id.et_it_amount)
    private val tlAmount: TextInputLayout = itemView.findViewById(R.id.et_l_amount)
    private val ivFlag: ImageView = itemView.findViewById(R.id.iv_flag)
    private val tvCountry: TextView = itemView.findViewById(R.id.tv_country)

    fun bind(currency: Currency) {
        etAmount.setText(currency.amount)
        tlAmount.hint = currency.currencyName
        ivFlag.setImageResource(currency.flag)
        tvCountry.text = currency.country
    }
}