package kz.kd.hw_1611

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCurrencyRecycler()
        fillCurrency()
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
        currencyAdapter.updateDataSet(currencyList)
    }
}