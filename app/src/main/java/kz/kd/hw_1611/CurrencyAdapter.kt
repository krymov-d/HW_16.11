package kz.kd.hw_1611

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

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
        val layoutID: Int = if (viewType == 0) {
            R.layout.card_currency
        } else {
            R.layout.btn_add
        }
        val view = layoutInflater.inflate(layoutID, parent, false)

        return if (viewType == 0) {
            CurrencyViewHolder(view)
        } else {
            BtnAddViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CurrencyViewHolder) {
            val currency = currencyList[position]
            holder.bind(currency)
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