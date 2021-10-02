package kr.ac.mjc.footprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class HomeFragment: Fragment() {
    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_home, container, false)

        //지도
        val mapView: MapView = MapView(getActivity())
        val mapViewContainer: ViewGroup = v.findViewById<View>(R.id.map_view) as ViewGroup
        mapViewContainer.addView(mapView)

        // 중심점 변경 - 예제 좌표는 서울 남산
        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                37.54892296550104,
                126.99089033876304
            ), true
        )

        // 줌 레벨 변경
        mapView.setZoomLevel(4, true)

        //마커 찍기
        val MARKER_POINT: MapPoint =
            MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304)
        val marker: MapPOIItem = MapPOIItem()
        marker.setItemName("Default Marker")
        marker.setTag(0)
        marker.setMapPoint(MARKER_POINT)
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin) // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin) // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker)
        return v
    }
}