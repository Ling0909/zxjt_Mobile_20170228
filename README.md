# zxjt_Mobile_20170228
1.代码重构，去除所有动态代理，简化代码及调试

2.TestListenerImpl中增加监听UnreachableBrowserException异常，解决appium server因内存泄露崩溃问题

3.增加StopException异常，抛出此异常可停止当前执行

4.增加ViewNavigator类，使App内导航更加简便，大大减少Test类中的代码

