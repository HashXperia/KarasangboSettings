package io.geeteshk.settingsx;

public class DrawerItem {

    private boolean mShowNotify;
    private String mTitle;

    public DrawerItem() {}

    public DrawerItem(boolean showNotify, String title) {
        mShowNotify = showNotify;
        mTitle = title;
    }

    public boolean isShowNotify() {
        return mShowNotify;
    }

    public void setShowNotify(boolean showNotify) {
        mShowNotify = showNotify;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
