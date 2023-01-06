package kz.kd.hw_1611

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CurrencyAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val currencyList: MutableList<Currency> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return if (position == currencyList.size) {
            1
        } else {
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutID: Int
        if (viewType == 0) {
            layoutID = R.layout.card_currency
        } else {
            layoutID = R.layout.btn_add
        }
        val view = layoutInflater.inflate(layoutID, parent, false)

        if (viewType == 0) {
            return CurrencyViewHolder(view)
        } else {
            return BtnAddViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CurrencyViewHolder) {
            val currency = currencyList[position]
            holder.bind(currency)

            /*
            val ivFlag: ImageView = holder.itemView.findViewById(R.id.iv_flag)
            ivFlag.setBackgroundResource(R.drawable.img_flag_bg)
            val etAmount: TextInputEditText = holder.itemView.findViewById(R.id.et_it_amount)
            etAmount.isTextInputLayoutFocusedRectEnabled
            val tlAmount: TextInputLayout = holder.itemView.findViewById(R.id.et_l_amount)
            tlAmount.isHintEnabled
            tlAmount.boxStrokeColor == R.color.btn_text_color
            */
        }
    }

    override fun getItemCount(): Int {
        return currencyList.size + 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newDataSet: List<Currency>) {
        currencyList.clear()
        currencyList.addAll(newDataSet)
        notifyDataSetChanged()
    }

}