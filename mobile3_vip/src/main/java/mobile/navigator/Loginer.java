package mobile.navigator;

import java.util.ArrayList;
import java.util.List;

import mobile.controller.StopException;
import mobile.page.PageLogin;
import mobile.page.base.PageManager;
import up.light.Setting;
import up.light.utils.LogUtil;

/**
 * 用于{@link ViewNavigator}实现自动登录
 */
public class Loginer {
	private List<String> hasLoginedGroup = new ArrayList<>();

	/**
	 * 当前节点是否需要登录
	 * 
	 * @param node 节点实例
	 * @return 需要登录true，否则false
	 */
	public boolean needLogin(ViewNode node) {
		PageLogin page = PageManager.getPage(PageLogin.class);

		if (hasLoginedGroup.contains(node.getGroupName()))
			return false;
		if (node.isChecklogin() == true) {
			if (page.exitsBtnLogin() == true)
				return true;
		}
		return false;
	}

	/**
	 * 登录指定分组
	 * 
	 * @param groupName 分组名（PTJY、RZRQ）
	 */
	public void login(String groupName) {
		PageLogin page = PageManager.getPage(PageLogin.class);
		String group = null;
		boolean checkAlert = false;

		if ("普通交易".equals(groupName)) {
			group = "PTJY";
			checkAlert = true;
		} else {
			group = "RZRQ";
		}

		String username = Setting.getProperty("username." + group);
		String password = Setting.getProperty("password." + group);

		try {
			page.doLogin(username, password, checkAlert);
			LogUtil.log.info(String.format("[Login] %s, username: %s, password: %s", group, username, password));
		} catch (Exception e) {
			throw new StopException(e);
		}

		hasLoginedGroup.add(groupName);
	}

	public void reset() {
		hasLoginedGroup.clear();
	}

}
