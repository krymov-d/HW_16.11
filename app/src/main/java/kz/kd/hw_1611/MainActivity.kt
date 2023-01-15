package kz.kd.hw_1611

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val KEY_VISIBLE_ITEM = "Visible Item"
private const val KEY_COMPLETELY_VISIBLE_ITEM = "Completely Visible Item"

class MainActivity : AppCompatActivity() {

    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCurrencyRecycler()
        fillCurrency()

        if (savedInstanceState != null) {
            val firstVisibleItem = savedInstanceState.getInt(KEY_VISIBLE_ITEM)
            val firstCompletelyVisibleItem = savedInstanceState.getInt(KEY_COMPLETELY_VISIBLE_ITEM)
            currencyLayoutManager.scrollToPositionWithOffset(
                firstVisibleItem,
                firstCompletelyVisibleItem
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val firstVisibleItem = currencyLayoutManager.findFirstVisibleItemPosition()
        val firstCompletelyVisibleItem =
            currencyLayoutManager.findFirstCompletelyVisibleItemPosition()
        outState.putInt(KEY_VISIBLE_ITEM, firstVisibleItem)
        outState.putInt(KEY_COMPLETELY_VISIBLE_ITEM, firstCompletelyVisibleItem)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val firstVisibleItem = savedInstanceState.getInt(KEY_VISIBLE_ITEM)
        val firstCompletelyVisibleItem = savedInstanceState.getInt(KEY_COMPLETELY_VISIBLE_ITEM)
        currencyLayoutManager.scrollToPositionWithOffset(
            firstVisibleItem,
            firstCompletelyVisibleItem
        )
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun initCurrencyRecycler() {
        currencyAdapter =
            CurrencyAdapter(layoutInflater, object : CurrencyAdapter.BtnAddClickListener {
                override fun bntAddClicked() {
                    currencyLayoutManager.scrollToPosition(currencyAdapter.itemCount)
                }
            })
        currencyLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val rvCurrency: RecyclerView = findViewById(R.id.rv_currency)
        rvCurrency.layoutManager = currencyLayoutManager
        rvCurrency.adapter = currencyAdapter

        val dividerItemDecoration = DividerItemDecoration(this, currencyLayoutManager.orientation)
        dividerItemDecoration.setDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.divider_bg
            )!!
        )
        rvCurrency.addItemDecoration(dividerItemDecoration)
    }

    private fun fillCurrency() {
        val currencyList = mutableListOf<Currency>()
        currencyList.add(
            Currency(
                amount = "1",
                flag = R.drawable.ic_kz,
                country = getString(R.string.kz),
                currencyName = getString(R.string.kz_currency)
            )
        )
        currencyList.add(
            Currency(
                flag = R.drawable.ic_usa,
                country = getString(R.string.usa),
                currencyName = getString(R.string.usa_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "3",
                flag = R.drawable.ic_tr,
                country = getString(R.string.tr),
                currencyName = getString(R.string.tr_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "4",
                flag = R.drawable.ic_eu,
                country = getString(R.string.eu),
                currencyName = getString(R.string.eu_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "5",
                flag = R.drawable.ic_usa,
                country = getString(R.string.usa),
                currencyName = getString(R.string.usa_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "6",
                flag = R.drawable.ic_kz,
                country = getString(R.string.kz),
                currencyName = getString(R.string.kz_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "7",
                flag = R.drawable.ic_usa,
                country = getString(R.string.usa),
                currencyName = getString(R.string.usa_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "8",
                flag = R.drawable.ic_tr,
                country = getString(R.string.tr),
                currencyName = getString(R.string.tr_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "9",
                flag = R.drawable.ic_eu,
                country = getString(R.string.eu),
                currencyName = getString(R.string.eu_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "10",
                flag = R.drawable.ic_usa,
                country = getString(R.string.usa),
                currencyName = getString(R.string.usa_currency)
            )
        )
        /*
        val currentCurrencyList = currencyAdapter.getCurrencyList()
        if (currencyList.size == currentCurrencyList.size) {
            currencyAdapter.updateDataSet(currencyList)
        }
        else {
            currencyAdapter.updateDataSet(currentCurrencyList)
        }

         */
        currencyAdapter.updateDataSet(currencyList)
    }
}