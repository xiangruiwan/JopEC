package com.example.latte.delegates;

/**
 * Created by Administrator on 2017/11/23.
 */

public abstract class LatteDelegate extends PermissionChickerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
