package kr.ac.mjc.footprint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;

public class HomeFragment extends Fragment {

    int i=0;


    ViewPager viewPager;
    private advFragment1 fragment1;
    private advFragment2 fragment2;
    private advFragment3 fragment3;

    private FirebaseAuth mAuth;
    Bitmap bitmap;

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
        /*
        ImageView profImage = view.findViewById(R.id.profile_iv);
        TextView profName = view.findViewById(R.id.name_tv);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore store = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user !=null ? user.getUid() : null;
*/

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        ImageView user_profile = view.findViewById(R.id.profile_iv);

        Thread mThread = new Thread(){
            @Override

            public void run() {

                try{

                    //현재로그인한 사용자 정보를 통해 PhotoUrl 가져오기

                    URL url = new URL(user.getPhotoUrl().toString());

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setDoInput(true);

                    conn.connect();


                    InputStream is = conn.getInputStream();

                    bitmap = BitmapFactory.decodeStream(is);

                } catch (MalformedURLException ee) {

                    ee.printStackTrace();

                }catch (IOException e){

                    e.printStackTrace();

                }

            }
        };
            mThread.start();

        try{

            mThread.join();

            //변환한 bitmap적용

            user_profile.setImageBitmap(bitmap);

        }catch (InterruptedException e){

            e.printStackTrace();

        }

        TextView user_name = view.findViewById(R.id.name_tv);
        user_name.setText(user.getDisplayName());



        Intrinsics.checkNotNullExpressionValue(btnfab, "v.findViewById(R.id.fab)");
/*
        final DocumentReference userdoc = store.collection("User").document(auth.getCurrentUser().getUid());
        userdoc.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                User user =
            }
        });


        btnfab.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MapActivity.class);
                startActivity(intent);
            }
        });
*/


        fragment1=new advFragment1();
        fragment2=new advFragment2();
        fragment3=new advFragment3();
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