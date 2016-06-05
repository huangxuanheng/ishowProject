package com.ishow.ishow;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.Serializable;

/**
 * Created by huangxuanheng on 2016/1/5.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    public FragmentManager fm;
    private static boolean isAppActive;   //app是否是处于活动状态
    private static boolean activeFlag;     //是否是在自己的页面活动

    protected SuperFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        fm = getSupportFragmentManager();
        initView();
    }

    public abstract void initView();

    /**
     * 添加fragment到栈中，
     * @param tag
     * @param fragment
     * 该方法已经过时，将被{@link #gotoThisFragment}替代，使用示例可以参考{@link #pushFragmentToBackStack}
     */
    @Deprecated
    public void addFragmentToBackStack(String tag, SuperFragment fragment) {

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_activity_container, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(tag);
        ft.commitAllowingStateLoss();
    }

    /**
     * 跳转到该指定的fragment，
     * @param cls
     * @param bundle
     * @param data
     * @return
     */
    public SuperFragment gotoThisFragment(Class<? extends SuperFragment>cls,Bundle bundle,Object data){
        String tag = cls.getName();
        SuperFragment fragment = (SuperFragment)fm.findFragmentByTag(tag);
        if(fragment==null){
            try{
                fragment = (SuperFragment)Class.forName(tag).newInstance();
            }catch (Exception e){
//                LogUtils.e(TabFragment.class.getSimpleName(), e.getMessage());
            }
        }
        FragmentTransaction ft = fm.beginTransaction();
        if(currentFragment!=null&&currentFragment!=fragment){
            currentFragment.onLeave();
            ft.hide(currentFragment);
        }
        fragment.onEnter(data);
        if(bundle!=null){
            fragment.setArguments(bundle);
        }

        if(fragment.isAdded()){
            ft.show(fragment);
        }else {
            ft.add(R.id.fab, fragment, tag);
        }
        currentFragment=fragment;
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(tag);
        ft.commitAllowingStateLoss();
        return fragment;
    }

    /**
     * 跳转到指定fragment，如果该fragment已经在栈中，并且其上面有多个fragment，则其上面的fragment将会被置空
     * @param cls
     * @param data
     */
    public void goToFragment(Class<? extends SuperFragment>cls,Object data){
        if(cls==null){
            return;
        }
        String tag = cls.getName();
        SuperFragment fragment = (SuperFragment)fm.findFragmentByTag(tag);
        if(fragment!=null){
            currentFragment=fragment;
            fragment.onBackWithData(data);
        }
        fm.popBackStackImmediate(tag, 0);
    }

    /**
     * 打开fragment到stack
     * @param cls
     * @param bundle
     * @param data
     */
    public SuperFragment pushFragmentToBackStack(Class cls,Bundle bundle,Object data){
        return gotoThisFragment(cls, bundle, data);
    }

    public SuperFragment pushFragmentToBackStack(Class cls,Object data){
        return pushFragmentToBackStack(cls, null, data);
    }


    /**
     * 回退时一个一个fragment退出
     */
    @Override
    public void onBackPressed() {
        clickBack();
    }

    /**
     * fragment回退
     */
    public void clickBack() {
        if(currentFragment !=null&&currentFragment.backPressed()){
            return;
        }
        int count = fm.getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            fm.popBackStack();
            if(currentFragment!=null){
                currentFragment.onBack();
            }
        }

    }


    @Override
    public void onClick(View v) {
        clickBack();
    }
}
