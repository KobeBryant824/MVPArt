package com.cxh.mvpsample.manager;


import android.support.v4.app.Fragment;
import android.util.SparseArray;


/**
 * Fragment工厂管理
 * Created by Hai (haigod7@gmail.com) on 2017/3/6 10:51.
 */
public class FragmentFactory {
	public static final int TAB_HOME = 0;
	public static final int TAB_APP = 1;
	public static final int TAB_GAME = 2;
	public static final int TAB_SUBJECT = 3;
	public static final int TAB_RECOMMEND = 4;
	public static final int TAB_CATEGORY = 5;
	public static final int TAB_TOP = 6;

	/** 记录所有的fragment，防止重复创建 */
	private static SparseArray<Fragment> mFragments = new SparseArray<>();

	/** 采用工厂类进行创建Fragment，便于扩展，已经创建的Fragment不再创建 */
	public static Fragment createFragment(int index) {
		Fragment fragment = mFragments.get(index);
		if (fragment == null) {
			switch (index) {
				case TAB_HOME:
//					fragment = new HomeFragment();
					break;
				case TAB_APP:
//					fragment = new AppFragment();
					break;
				case TAB_GAME:
//					fragment = new GameFragment();
					break;
				case TAB_SUBJECT:
//					fragment = new SubjectFragment();
					break;
				case TAB_RECOMMEND:
//					fragment = new RecommendFragment();
					break;
				case TAB_CATEGORY:
//					fragment = new CategoryFragment();
					break;
				case TAB_TOP:
//					fragment = new HotFragment();
					break;
			}
			mFragments.put(index, fragment);
		}
		return fragment;
	}
}
