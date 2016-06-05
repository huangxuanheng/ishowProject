package com.ishow.ishow;


/**
 * Created by huangxuanheng on 2016/4/16 11:17
 * description:
 *      provide some method to make fragment like activity in backStack ,
 *      <p/>
 *      when a fragment becomes invisible totally{@link #onLeave} will be called
 *      <p/>
 *      when a fragment becomes visible from totally invisible,{@link #onBack()} or {@link #onBackWithData(Object)} will be called
 */
public interface IFragmentLifeCycle {

    void onLeave();

    void onBack();


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
