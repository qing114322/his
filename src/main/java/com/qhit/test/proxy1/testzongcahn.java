package com.qhit.test.proxy1;

/**
 * Created by tp on 2018/12/5.
 */
public class testzongcahn {
    public static void main(String[] args) {
        Fandian fandian=new xican();
        proxyfandianproxy proxyfandianproxy=new proxyfandianproxy();
        Fandian bind = (Fandian) proxyfandianproxy.bind(fandian);
        bind.TigongFancai();
    }
}
