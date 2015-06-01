package com.example.nestedfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChildTextViewFragment extends Fragment {

	public static final String TAG = "ChildTextViewFragment";
	
	public static final String TEXT_KEY = "text_key";
	
	public static ChildTextViewFragment newInstance(String text) {
		ChildTextViewFragment fragment = new ChildTextViewFragment();
		Bundle bundle = new Bundle();
		bundle.putString(TEXT_KEY, text);
		fragment.setArguments(bundle);
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_text, container, false);
		
		TextView mTextView = (TextView) view.findViewById(R.id.textView1);
		String text = getArguments().getString(TEXT_KEY);
		mTextView.setText(text);
		
		Log.d(TAG, text+" :: onCreateView savedInstanceState is " + (savedInstanceState == null ? "null" : "not null"));
		
		return view;
		
	}
	
}
