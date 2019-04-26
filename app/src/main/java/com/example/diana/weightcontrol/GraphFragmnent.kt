package com.example.diana.weightcontrol

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.graph_fragment.view.*


@Suppress("DEPRECATION")
class GraphFragmnent : Fragment() {

    private lateinit var graph: LineChart
    private var databaseHelper = App.getInstance().getDatabaseInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.graph_fragment, container, false)
        graph = rootView.lc_graph
        SetupChart()
        return rootView
    }

    private fun SetupChart() {
        //graph.setOnChartValueSelectedListener(this)
        graph.isDragEnabled
        graph.isScaleXEnabled
        graph.isScaleYEnabled
        graph.data = PrepareData()
    }

    private fun PrepareData(): LineData {
        var tempSet: ArrayList<Entry> = ArrayList()
        var timeSet: ArrayList<String> = ArrayList()
        for (item in databaseHelper.journalDao.allData) {
            timeSet.add(item.date)
            tempSet.add(Entry(item.id.toFloat(), item.weight.toFloat()))           
        }
        var dataSet = LineDataSet(tempSet, "ВЕС")
        dataSet.fillAlpha = 110
        dataSet.color = resources.getColor(R.color.colorPrimaryDark)
        dataSet.lineWidth = 3f
        dataSet.valueTextSize = 15f
        var dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(dataSet)
        var data = LineData(dataSets)
        return data
    }
}
