package cn.bproject.neteasynews.fragment;

import java.util.HashMap;

/**
 * 生产fragment工厂
 * 
 * @author Kevin
 * @date 2015-10-27
 */
public class FragmentFactory {

	private static HashMap<Integer, BaseFragment> mFragmentMap = new HashMap<Integer, BaseFragment>();

	public static BaseFragment createFragment(int pos) {
		// 先从集合中取, 如果没有,才创建对象, 提高性能
		BaseFragment fragment = mFragmentMap.get(pos);

		if (fragment == null) {
			switch (pos) {
			case 0:
				fragment = new NewsListFragment();
				break;
			case 1:
				fragment = new NewsListFragment();
				break;
			case 2:
				fragment = new NewsListFragment();
				break;
			case 3:
				fragment = new NewsListFragment();
				break;
			case 4:
				fragment = new NewsListFragment();
				break;
//			case 5:
//				fragment = new CategoryFragment();
//				break;
//			case 6:
//				fragment = new HotFragment();
//				break;

			default:
				break;
			}

			mFragmentMap.put(pos, fragment);// 将fragment保存在集合中
		}

		return fragment;
	}
}
