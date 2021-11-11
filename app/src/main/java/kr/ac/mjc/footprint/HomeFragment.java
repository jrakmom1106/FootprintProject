package kr.ac.mjc.footprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import kotlin.jvm.internal.Intrinsics;

public class HomeFragment extends Fragment {

    int i=0;


    ViewPager viewPager;
    private advFragment1 fragment1;
    private advFragment1 fragment2;
    private advFragment1 fragment3;

   // private ThirdFragment fragment3;

    public HomeFragment(){

    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Intrinsics.checkNotNullExpressionValue(view, "inflater.inflate(R.layou…t_home, container, false)");



        Button btnfab  = view.findViewById(R.id.fab);

        Intrinsics.checkNotNullExpressionValue(btnfab, "v.findViewById(R.id.fab)");

        btnfab.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MapActivity.class);
                startActivity(intent);
            }
        });


        fragment1=new advFragment1();
        fragment2=new advFragment1();
        fragment3=new advFragment1();
       // fragment3=new ThirdFragment();
        viewPager=(ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        viewPager.setCurrentItem(0);




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm){
            super(fm);
            getItem(0);
        }
        public Fragment getItem(int position){
            if(position==0)
            {
                return fragment1;
            }else if(position==1){
                return fragment2;
            }else {
               return fragment3;
            }

        }
        public int getCount(){
            return 3;
        }

    }






}