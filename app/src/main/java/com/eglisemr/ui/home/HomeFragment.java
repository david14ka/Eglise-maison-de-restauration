package com.eglisemr.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.eglisemr.R;
import com.eglisemr.model.Resource;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final String TAG = "FragmentHome";
    private List<String> bannerImagesList;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);

        bannerImages();
        performLayers();

        return root;


    }

    private void performLayers() {
        LinearLayout parent = root.findViewById(R.id.content_layout);
        ResourceViewModel resourceViewModel = new ViewModelProvider(this).get(ResourceViewModel.class);
        resourceViewModel.getResources().observe(getViewLifecycleOwner(), new Observer<List<Resource>>() {
            @Override
            public void onChanged(List<Resource> resources) {
                for (Resource res :
                        resources) {
                    Log.i(TAG, "onChanged: "+res.getTitle());
                    parent.addView(new ResourceView(getContext()).withResource(res));
                }
            }
        });
    }

    private void bannerImages() {
        BannerViewModel bannerViewModel = new ViewModelProvider(this).get(BannerViewModel.class);
        bannerViewModel.getBanners().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                bannerImagesList = new ArrayList<>();
                bannerImagesList = strings;
                CarouselView carouselView = root.findViewById(R.id.carouselView);
                carouselView.setImageListener(imageListener);
                carouselView.setPageCount(strings.size());
            }
        });
    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {

            Picasso.get()
                    .load(bannerImagesList.get(position))
                    .placeholder(R.drawable.rosary)
                    .error(R.drawable.holder)
                    .into(imageView);
        }
    };
}