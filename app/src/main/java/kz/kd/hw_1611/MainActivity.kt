package kz.kd.hw_1611

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val KEY_VISIBLE_ITEM = "Visible Item"
private const val KEY_COMPLETELY_VISIBLE_ITEM = "Completely Visible Item"

class MainActivity : AppCompatActivity() {

    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyLayoutManager: LinearLayoutManager
    private lateinit var rvCurrency: RecyclerView

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

        setSupportActionBar(findViewById(R.id.tb_main))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
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

        rvCurrency = findViewById(R.id.rv_currency)
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

        swipeCurrency()
    }

    private fun swipeCurrency() {
        val touchHelper =
            ItemTouchHelper(object : SimpleCallback(UP or DOWN, LEFT or RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    currencyAdapter.onMoveDrag(viewHolder.layoutPosition, target.layoutPosition)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    currencyAdapter.onSwipeDelete(viewHolder.layoutPosition)
                }

            })
        touchHelper.attachToRecyclerView(rvCurrency)
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
        currencyAdapter.updateDataSet(currencyList)
    }
}