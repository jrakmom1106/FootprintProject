package kr.ac.mjc.footprint;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import kr.ac.mjc.footprint.model.category_search.Document;

import kr.ac.mjc.footprint.model.research.SearchResult;

import kr.ac.mjc.footprint.utils.IntentKey;

public class MapSearchDetailActivity extends AppCompatActivity {
    final static String TAG = "MapSearchDetailTAG";

    //xml
    RadarChart radarChart;
    TextView itemCntText1, itemCntText2, itemCntText3, itemCntText4, itemCntText5 , getItemCntText6;
    TextView ratingScore;
    RatingBar ratingBar;

    //value
    ArrayList<Document> bigMartList = new ArrayList<>(); //대형마트 MT1
    ArrayList<Document> gs24List = new ArrayList<>(); //편의점 CS2
    ArrayList<Document> bankList = new ArrayList<>(); //은행 BK9
    ArrayList<Document> hospitalList = new ArrayList<>(); //병원 HP8
    ArrayList<Document> pharmacyList = new ArrayList<>(); //약국 PM9

    ArrayList<Document> keywordList = new ArrayList<>(); //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search_detail);
        itemCntText1 = findViewById(R.id.mapsearchdetail_tv_itemcount1);
        itemCntText2 = findViewById(R.id.mapsearchdetail_tv_itemcount2);
        itemCntText3 = findViewById(R.id.mapsearchdetail_tv_itemcount3);
        itemCntText4 = findViewById(R.id.mapsearchdetail_tv_itemcount4);
        itemCntText5 = findViewById(R.id.mapsearchdetail_tv_itemcount5);

        ratingBar = findViewById(R.id.mapsearchdetail_rb_ratingbar);
        ratingScore = findViewById(R.id.mapsearchdetail_tv_rating_score);
        radarChart = findViewById(R.id.mapsearchdetail_radar_chart);
        processIntent();
        makeChart();
        initView();
    }

    //인텐트처리
    private void processIntent() {
        Intent getIntent = getIntent();
        bigMartList = getIntent.getParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA1);
        gs24List = getIntent.getParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA2);
        bankList = getIntent.getParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA3);
        hospitalList = getIntent.getParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA4);
        pharmacyList = getIntent.getParcelableArrayListExtra(IntentKey.CATEGOTY_SEARCH_MODEL_EXTRA5);
    }

    private void initView(){
        float itemCnt1 = bigMartList.size();
        float itemCnt2 = gs24List.size();
        float itemCnt3 = bankList.size();
        float itemCnt4 = hospitalList.size();
        float itemCnt5 = pharmacyList.size();

        itemCntText1.setText("" +(int) itemCnt1);
        itemCntText2.setText("" +(int) itemCnt2);
        itemCntText3.setText("" +(int) itemCnt3);
        itemCntText4.setText("" +(int) itemCnt4);
        itemCntText5.setText("" +(int) itemCnt5);



        //평균계산 최대 10점
        if(itemCnt1 > 10){
            itemCnt1 = 10;
        }
        if(itemCnt2 > 10){
            itemCnt2 = 10;
        }
        if(itemCnt3 > 10){
            itemCnt3 = 10;
        }
        if(itemCnt4 > 10){
            itemCnt4 = 10;
        }
        if(itemCnt5 > 10){
            itemCnt5 = 10;
        }

        float averageScore = Math.round((itemCnt1 + itemCnt2 + itemCnt3 + itemCnt4 + itemCnt5)/10*10 /10.0 );
        ratingScore.setText(averageScore+"");
        ratingBar.setRating(averageScore/2);
    }

    //차트생성
    private void makeChart() {
        RadarDataSet dataSet = new RadarDataSet(dataValue(), "주변환경");
        dataSet.setColor(Color.BLUE);

        RadarData data = new RadarData();
        data.addDataSet(dataSet);
        String[] labels = {"대형마트", "편의점", "은행", "병원", "약국"};
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        radarChart.setData(data);
    }

    //차트 데이터 생성
    private ArrayList<RadarEntry> dataValue() {
        ArrayList<RadarEntry> dataVals = new ArrayList<>();
        dataVals.add(new RadarEntry(bigMartList.size()));
        dataVals.add(new RadarEntry(gs24List.size()));
        dataVals.add(new RadarEntry(bankList.size()));
        dataVals.add(new RadarEntry(hospitalList.size()));
        dataVals.add(new RadarEntry(pharmacyList.size()));
        return dataVals;
    }

}
