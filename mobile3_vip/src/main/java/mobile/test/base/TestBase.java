package mobile.test.base;

import mobile.navigator.ViewNavigator;
import mobile.page.base.AbstractPage;
import up.light.Setting;
import up.light.utils.ArgumentUtil;
import up.light.utils.LogUtil;

/**
 * Test基类，提供默认的recovery方法
 */
public class TestBase {

	public void recovery() {
		AbstractPage page = ViewNavigator.getCurrentPage();

		if (page != null) {
			LogUtil.log.debug("[Recovery] current page: " + page.getClass().getName());
			page.reset();
		} else {
			System.err.println("Current page is null");
		}
	}

	/**
	 * 将字符串中的换行符统一替换为当前操作系统的换行符
	 * 
	 * @param str
	 * @return
	 */
	protected String lineSeparator(String str) {
		ArgumentUtil.notNull(str, "str must not be null");
		return str.replaceAll("[\\r\\n]{1,2}", Setting.LINE_SEPARATOR);
	}

}
