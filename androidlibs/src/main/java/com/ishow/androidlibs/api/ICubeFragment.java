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
     * before a new fragment open and the current fragment will be call onLeave
     * */
    void onLeave();

    /**
     * the current fragment when go back will be call,it was
     */
    void onBack();

    /**
     * when open a fragment which top had some fragment in the stack and now jump to this fragment ,
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
