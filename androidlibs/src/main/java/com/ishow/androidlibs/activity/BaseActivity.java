package com.ishow.androidlibs.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ishow.androidlibs.R;
import com.ishow.androidlibs.fragment.CubeFragment;

/**
 * Created by huangxuanheng on 2016/1/5.
 */
public abstract class BaseActivity extends AppCompatActivity{

    public FragmentManager fm;
    private CubeFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        fm = getSupportFragmentManager();
    }

    /**
     * 跳转到该指定的fragment，
     * @param cls
     * @param data
     * @return
     */
    public void gotoThisFragment(Class<? extends CubeFragment>cls,Object data){
        String tag = cls.getName();
        CubeFragment fragment = (CubeFragment)fm.findFragmentByTag(tag);
        if(fragment==null){
            try{
                fragment = (CubeFragment)Class.forName(tag).newInstance();
            }catch (Exception e){
            }
        }
        FragmentTransaction ft = fm.beginTransaction();
        if(currentFragment!=null&&currentFragment!=fragment){
            currentFragment.onLeave();
            ft.hide(currentFragment);
        }
        fragment.onEnter(data);

        if(fragment.isAdded()){
            ft.show(fragment);
        }else {
            ft.add(R.id.fl_activity_container, fragment, tag);
        }
        currentFragment=fragment;
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(tag);
        ft.commitAllowingStateLoss();
    }

    /**
     * 跳转到指定fragment，如果该fragment已经在栈中，并且其上面有多个fragment，则其上面的fragment将会被置空
     * @param cls
     * @param data
     */
    public void goToFragment(Class<? extends CubeFragment>cls,Object data){
        if(cls==null){
            return;
        }
        String tag = cls.getName();
        CubeFragment fragment = (CubeFragment)fm.findFragmentByTag(tag);
        if(fragment!=null){
            currentFragment=fragment;
            fragment.onBackWithData(data);
        }
        fm.popBackStackImmediate(tag, 0);
    }

    /**
     * 携带数据回到栈中的前一个fragment
     * @param data 携带的数据
     */
    public void popTopFragment(Object data){
        fm.popBackStackImmediate();
        if(tryToUpdateCurrentAfterPop()){
            currentFragment.onBackWithData(data);
        }
    }

    //在Pop之后更新当前fragment，如果更新成功则返回true,否则false
    private boolean tryToUpdateCurrentAfterPop() {
        int backStackEntryCount = fm.getBackStackEntryCount();
        if(backStackEntryCount>0){
            String name = fm.getBackStackEntryAt(backStackEntryCount - 1).getName();
            Fragment fragment = fm.findFragmentByTag(name);
            if(fragment!=null && fragment instanceof CubeFragment){
                currentFragment= (CubeFragment) fragment;
                return true;
            }
        }
        return false;
    }

    /**
     * 根据指定的字节码获取当前activity栈中对应包名tag的fragment
     * @param cls 指定的字节码
     * @return
     */
    public CubeFragment getCubeFragment(Class<? extends CubeFragment>cls){
        return (CubeFragment) fm.findFragmentByTag(cls.getName());
    }

    /**
     * 打开fragment到stack
     * @param cls
     * @param data
     */
    public void pushFragmentToBackStack(Class cls,Object data){
        gotoThisFragment(cls, data);
    }

    /**
     * 回退时一个一个fragment退出
     */
    @Override
    public void onBackPressed() {
        if(currentFragment !=null&&currentFragment.backPressed()){
            return;
        }
        backPressed();
    }

    /**
     * fragment回退
     */
    public void backPressed() {
        int count = fm.getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            fm.popBackStackImmediate();
            if(tryToUpdateCurrentAfterPop()){
                currentFragment.onBack();
            }
        }
    }
}
