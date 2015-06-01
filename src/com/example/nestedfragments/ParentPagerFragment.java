package com.example.nestedfragments;

import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ParentPagerFragment extends Fragment {

	public static final String TAG = "ParentPagerFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView savedInstanceState is "+(savedInstanceState == null ? "null" : "not null"));
		
		View v = inflater.inflate(R.layout.fragment_pager, container, false);
		
		List<ChildTextViewFragment> viewFragments = new ArrayList<>();
		viewFragments.add(ChildTextViewFragment.newInstance("Fragment1"));
		viewFragments.add(ChildTextViewFragment.newInstance("Fragment2"));
		viewFragments.add(ChildTextViewFragment.newInstance("Fragment3"));
		
		MyPagerAdapter mPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), viewFragments);
		
		ViewPager mViewPager = (ViewPager) v.findViewById(R.id.pager);
		mViewPager.setAdapter(mPagerAdapter);
		
		return v;
	}
	
	
	class MyPagerAdapter extends FragmentStatePagerAdapter {
		 
		List<ChildTextViewFragment> viewFragments;
		
	    public MyPagerAdapter(FragmentManager fm, List<ChildTextViewFragment> viewFragments) {
	        super(fm);
	        this.viewFragments = viewFragments;
	    }
	 
	    @Override
	    public Fragment getItem(int index) {
	    	return viewFragments.get(index);
	    
	    }
	 
	    @Override
	    public int getCount() {
	        return viewFragments.size();
	    }
	    
	    @Override
	    public CharSequence getPageTitle(int position){
	    	if(position == 0) {
	    		return "Fragment1";
	    	} else if (position == 1) {
	    		return "Fragment2";
	    	} else {
	    		return "Fragment3";
	    	}
	    }
	 
	}
	
}
