package mobile.page.base;

import java.util.HashMap;
import java.util.Map;

import up.light.exceptions.LightReflectionException;
import up.light.utils.ArgumentUtil;
import up.light.utils.ClassUtil;
import up.light.utils.LogUtil;

/**
 * Page管理器，获取Page实例，为防止Page多次初始化，所有Page均使用单例模式
 */
public class PageManager {
	private static Map<Class<? extends AbstractPage>, AbstractPage> pages = new HashMap<>();

	@SuppressWarnings("unchecked")
	public static <T extends AbstractPage> T getPage(Class<T> clazz) {
		ArgumentUtil.notNull(clazz, "class must not be null");
		AbstractPage p = pages.get(clazz);

		if (p == null) {
			p = InstancePage(clazz);
			pages.put(clazz, p);
			LogUtil.log.debug("[Instance page] " + clazz.getSimpleName());
		}
		return (T) p;
	}

	private static AbstractPage InstancePage(Class<? extends AbstractPage> clazz) {
		AbstractPage p = null;

		try {
			p = ClassUtil.getInstance(clazz);
		} catch (LightReflectionException e) {
			throw new RuntimeException(e.getCause());
		}

		return p;
	}
}
