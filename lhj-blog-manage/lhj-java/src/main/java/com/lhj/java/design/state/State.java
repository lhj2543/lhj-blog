package com.lhj.java.design.state;

/**
 * 状态接口
 * @author 17822
 */
public interface State {

    public Boolean  apply();
    public Boolean  reApply();
    public Boolean  approval();
    public Boolean  sendBack();
    public Boolean  end();

}
