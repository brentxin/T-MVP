package com.ui.home;

import com.EventTags;
import com.app.annotation.apt.InstanceFactory;
import com.app.annotation.javassist.Bus;
import com.app.annotation.javassist.BusRegister;
import com.app.annotation.javassist.BusUnRegister;
import com.base.OkBus;
import com.base.util.SpUtil;
import com.data.entity._User;

/**
 * Created by baixiaokang on 16/4/22.
 */
@InstanceFactory
public class HomePresenter extends HomeContract.Presenter {


    @Override
    public void getUserInfo() {
        _User user = SpUtil.getUser();
        if (user != null)
            mView.initUserInfo(user);
    }

    @Override
    public void onAttached() {
        initEvent();
        getTabList();
        getUserInfo();
    }


    @Override
    public void getTabList() {
        String[] mTabs = {"民谣", "摇滚", "电子", "流行", "爵士", "独立", "故事", "新世纪", "精品推荐", "原声"};
        OkBus.getInstance().onEvent(EventTags.SHOW_TAB_LIST, mTabs);
    }

    @Bus(tag = EventTags.SHOW_TAB_LIST)
    public void showTabList(String[] tabs) {
        mView.showTabList(tabs);
    }

    @BusRegister
    private void initEvent() {
    }

    @Bus(tag = EventTags.ON_USER_LOGIN)
    public void OnLogin(_User user) {
        mView.initUserInfo(user);
    }

    @Override
    @BusUnRegister
    public void onDetached() {
        super.onDetached();
    }
}
