package com.u9porn.ui.splash;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.u9porn.data.DataManager;
import com.u9porn.data.model.Notice;
import com.u9porn.data.model.UpdateVersion;
import com.u9porn.ui.notice.NoticePresenter;
import com.u9porn.ui.update.UpdatePresenter;

import javax.inject.Inject;

/**
 * @author flymegoc
 * @date 2017/12/21
 */

public class SplashPresenter extends MvpBasePresenter<SplashView> implements ISplash {

    private DataManager dataManager;
    private final UpdatePresenter updatePresenter;
    private final NoticePresenter noticePresenter;
    @Inject
    public SplashPresenter(DataManager dataManager, UpdatePresenter updatePresenter, NoticePresenter noticePresenter) {
        this.dataManager = dataManager;
        this.updatePresenter = updatePresenter;
        this.noticePresenter = noticePresenter;
    }

    @Override
    public boolean isUserLogin() {
        return dataManager.isUserLogin();
    }

    @Override
    public String getVideo9PornAddress() {
        return dataManager.getPorn9VideoAddress();
    }


    @Override
    public void checkUpdate(int versionCode) {
        updatePresenter.checkUpdate(versionCode, new UpdatePresenter.UpdateListener() {
            @Override
            public void needUpdate(final UpdateVersion updateVersion) {
                ifViewAttached(new ViewAction<SplashView>() {
                    @Override
                    public void run(@NonNull SplashView view) {
                        view.needUpdate(updateVersion);
                    }
                });
            }

            @Override
            public void noNeedUpdate() {
                ifViewAttached(new ViewAction<SplashView>() {
                    @Override
                    public void run(@NonNull SplashView view) {
                        view.noNeedUpdate();
                    }
                });
            }

            @Override
            public void checkUpdateError(final String message) {
                ifViewAttached(new ViewAction<SplashView>() {
                    @Override
                    public void run(@NonNull SplashView view) {
                        view.checkUpdateError(message);
                    }
                });
            }
        });
    }

    @Override
    public void checkNewNotice() {
        noticePresenter.checkNewNotice(new NoticePresenter.CheckNewNoticeListener() {
            @Override
            public void haveNewNotice(final Notice notice) {
                ifViewAttached(new ViewAction<SplashView>() {
                    @Override
                    public void run(@NonNull SplashView view) {
                        view.haveNewNotice(notice);
                    }
                });
            }

            @Override
            public void noNewNotice() {
                ifViewAttached(new ViewAction<SplashView>() {
                    @Override
                    public void run(@NonNull SplashView view) {
                        view.noNewNotice();
                    }
                });
            }

            @Override
            public void checkNewNoticeError(final String message) {
                ifViewAttached(new ViewAction<SplashView>() {
                    @Override
                    public void run(@NonNull SplashView view) {
                        view.checkNewNoticeError(message);
                    }
                });
            }
        });
    }

    @Override
    public int getIgnoreUpdateVersionCode() {
        return dataManager.getIgnoreUpdateVersionCode();
    }
}
