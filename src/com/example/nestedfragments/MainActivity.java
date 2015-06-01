package com.example.nestedfragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	// Drawer Layout
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private Toolbar mToolbar;

	private String drawerTitle;
	private int viewingPosition;
	
	public static final String VIEWING_POS_BUNDLE_KEY = "viewingposition";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d("MainActivity", "onCreate savedInstanceState = "+(savedInstanceState == null ? "null" : "not null"));
		
		setContentView(R.layout.activity_main);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		if(savedInstanceState != null) {
			viewingPosition = savedInstanceState.getInt(VIEWING_POS_BUNDLE_KEY);
		}
		initializeNavigationDrawer(savedInstanceState);
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		if(savedInstanceState != null) {
			savedInstanceState.putInt(VIEWING_POS_BUNDLE_KEY, viewingPosition);
		}
		super.onSaveInstanceState(savedInstanceState);
	}
	
	/*
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private void initializeNavigationDrawer(Bundle savedInstanceState) {

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item,
				new String[]{"Item1", "Item2"}));

		mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				if (position == 0) {
					if (viewingPosition == position) {
						mDrawerLayout.closeDrawer(mDrawerList);
						return;
					}
					getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.contentFrame, new ParentPagerFragment(),
									ParentPagerFragment.TAG).commit();

				}

				if (position == 1) {
					if (viewingPosition == position) {
						mDrawerLayout.closeDrawer(mDrawerList);
						return;
					}
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.contentFrame, ChildTextViewFragment.newInstance("hello fragment"), ChildTextViewFragment.TAG)
							.commit();
				}

				mDrawerList.setItemChecked(position, true);
				mDrawerLayout.closeDrawer(mDrawerList);

				viewingPosition = position;

			}
		});

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
				R.string.drawer_open, R.string.drawer_closed) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(drawerTitle);
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle("Opened");
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if(savedInstanceState == null) {
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.contentFrame, new ParentPagerFragment(),
					ParentPagerFragment.TAG).commit();

		}
		
	}

}
