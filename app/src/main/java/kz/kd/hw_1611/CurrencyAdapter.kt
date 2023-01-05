package kz.kd.hw_1611

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter(private val layoutInflater: LayoutInflater): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val currencyList: MutableList<Currency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.card_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currency = currencyList[position]
        if (holder is CurrencyViewHolder) {
            holder.bind(currency)
        }
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newDataSet: List<Currency>) {
        currencyList.clear()
        currencyList.addAll(newDataSet)
        notifyDataSetChanged()
    }

}