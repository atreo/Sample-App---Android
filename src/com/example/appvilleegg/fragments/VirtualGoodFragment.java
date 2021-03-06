package com.example.appvilleegg.fragments;


import java.util.List;

import com.applicasa.ApplicasaManager.LiPromo;
import com.applicasa.ApplicasaManager.LiStore;
import com.applicasa.Promotion.Promotion;

import com.appvilleegg.R;
import com.example.appvilleegg.adapters.VirtualGoodAdapter;
import com.example.appvilleegg.sampleApp.TabsFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import applicasa.LiCore.LiErrorHandler;
import applicasa.LiCore.LiLogger;
import applicasa.LiCore.promotion.sessions.LiPromotionCallback;
import applicasa.kit.IAP.IAP.GetVirtualGoodKind;
import applicasa.kit.IAP.IAP.LiCurrency;


public class VirtualGoodFragment extends Fragment implements GridView.OnItemClickListener{
	/** (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	String Tag = VirtualGoodFragment.class.getSimpleName();
	
    private GridView                mGridView;
    private static VirtualGoodAdapter  	mProductAdapter;
    Activity activity;
	   
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		final View v = inflater.inflate(R.layout.grid_frag, container, false);
		mGridView = (GridView) v.findViewById(R.id.grid_view);
		mGridView.setVisibility(View.VISIBLE);	
	    return v;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		activity = getActivity();
		 if (activity != null) {
	            // gets an instance of the custom adapter for the Virtual Product.
			 	mProductAdapter = VirtualGoodAdapter.getInstance(activity, LiStore.GetAllVirtualGoods(GetVirtualGoodKind.ALL));
	            
	            if (mGridView != null) {
	                mGridView.setAdapter(mProductAdapter);
	            }
	            
	            mGridView.setOnItemClickListener(this);
	 	
		 }
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	}
	
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		LiLogger.LogWarning(Tag, "Clicked");
		if (TabsFragmentActivity.clickEnabled)	
		{
			try {
				(LiStore.GetAllVirtualGoods(GetVirtualGoodKind.ALL)).get(position).buyVirtualGoods(1,LiCurrency.MainCurrency);
				TabsFragmentActivity.refreshUI();
			} catch (LiErrorHandler e) {
				// TODO Auto-generated catch block
			}
		}
	}


	public static void OnInitCompleted() {
		// TODO Auto-generated method stub
		mProductAdapter.notifyDataSetChanged();
	}

	@Override
	public void onPause() {
		
	    super.onPause();
	}
	
}
