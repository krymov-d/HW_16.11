package kz.kd.hw_1611

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter(
    private val layoutInflater: LayoutInflater,
    private val listener: BtnAddClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var currencyList: MutableList<Currency> = mutableListOf()
    private var currencyListCopy: MutableList<Currency> = mutableListOf()
    private var sortType: Int = 0

    interface BtnAddClickListener {
        fun bntAddClicked()
    }

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
        } else if (holder is BtnAddViewHolder) {
            val btnAdd: Button = holder.itemView.findViewById(R.id.btn_add)
            btnAdd.setOnClickListener {
                listener.bntAddClicked()
                newCurrencyAdded(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return currencyList.size + 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newDataSet: List<Currency>) {
        currencyList.clear()
        currencyList.addAll(newDataSet)
        reserveCopy(currencyList)
        notifyDataSetChanged()
    }

    fun onMoveDrag(startPosition: Int, stopPosition: Int) {
        val temporaryCurrency: Currency = currencyList[stopPosition]
        currencyList[stopPosition] = currencyList[startPosition]
        currencyList[startPosition] = temporaryCurrency
        reserveCopy(currencyList)
        notifyItemMoved(startPosition, stopPosition)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onSwipeDelete(position: Int) {
        currencyList.removeAt(position)
        reserveCopy(currencyList)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, currencyList.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun newCurrencyAdded(position: Int) {
        val newCurrencyListSize = currencyList.size + 1
        val newCurrency = Currency(
            amount = newCurrencyListSize.toString(),
            flag = R.drawable.ic_rus,
            country = "Россия",
            currencyName = "Рубль"
        )
        currencyList.add(position, newCurrency)
        reserveCopy(currencyList)
        when (sortType) {
            1 -> currencyList.sortBy { it.country }
            2 -> {
                currencyList.sortBy { it.amount }
                currencyList.reverse()
            }
        }
        val newPosition = currencyList.indexOf(newCurrency)
        notifyItemInserted(newPosition)
        notifyItemRangeChanged(newPosition, newCurrencyListSize)
    }

    private fun reserveCopy(copyDataSet: List<Currency>) {
        currencyListCopy.clear()
        currencyListCopy.addAll(copyDataSet)
        currencyListCopy.sortBy { it.amount }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun reset() {
        sortType = 0
        currencyList.clear()
        currencyList.addAll(currencyListCopy)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortAlpha() {
        sortType = 1
        currencyList.sortBy { it.country }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortNum() {
        sortType = 2
        currencyList.sortBy { it.amount }
        currencyList.reverse()
        notifyDataSetChanged()
    }
}