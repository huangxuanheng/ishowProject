package com.ishow.androidlibs.api;


import com.ishow.androidlibs.activity.BaseActivity;

/**
 * Created by huangxuanheng on 2016/4/16 11:17
 * description:
 *      provide some method to make fragment like activity in backStack ,
 *      <p/>
 *      when a fragment becomes invisible totally{@link #onLeave} will be called
 *      <p/>
 *      when a fragment becomes visible from totally invisible,{@link #onBack()} or {@link #onBackWithData(Object)} will be called
 */
public interface ICubeFragment {

    /**
     * before a new fragment add and the current fragment will be call onLeave
     * */
    void onLeave();

    /**
     * the method will be call by fragment who after pop and now on the stack top
     */
    void onBack();

    /**
     * it is had some fragment in the stack ,and when pop to a fragment which need to back with data
     * <p>this method will be call </p>
     * @param object
     */
    void onBackWithData(Object object);

    /**
     * when a fragment is visible and pass back,this will be called
     * @return
     */
    boolean backPressed();

    /**
     * pass the data from {@link BaseActivity#pushFragmentToBackStack(Class,Object)} to this fragment
     * @param data
     */
    void onEnter(Object data);
}
