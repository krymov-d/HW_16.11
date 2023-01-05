package kz.kd.hw_1611

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCurrencyRecycler()
        fillCurrency()
    }

    private fun initCurrencyRecycler() {
        currencyAdapter = CurrencyAdapter(layoutInflater)
        val currencyLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val rvCurrency: RecyclerView = findViewById(R.id.rv_currency)
        rvCurrency.layoutManager = currencyLayoutManager
        rvCurrency.adapter = currencyAdapter
    }

    private fun fillCurrency() {
        val currency = mutableListOf<Currency>()
        currency.add(Currency(amount = "1500000", R.drawable.ic_kz, getString(R.string.kz), currencyName = getString(R.string.kz_currency)))
        currency.add(Currency(flag = R.drawable.ic_usa, country = getString(R.string.usa), currencyName = getString(R.string.usa_currency)))
        currency.add(Currency(amount = "23450", flag = R.drawable.ic_tr, country = getString(R.string.tr), currencyName = getString(R.string.tr_currency)))
        currency.add(Currency(amount = "888", flag = R.drawable.ic_eu, country = getString(R.string.eu), currencyName = getString(R.string.eu_currency)))
        currency.add(Currency(amount = "777", flag = R.drawable.ic_usa, country = getString(R.string.usa), currencyName = getString(R.string.usa_currency)))
        currencyAdapter.updateDataSet(currency)
    }
}