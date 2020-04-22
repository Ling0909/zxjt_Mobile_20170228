package mobile.navigator;

import java.util.List;
import java.util.Map;

import mobile.page.base.AbstractPage;
import up.light.DriverFactory;
import up.light.utils.ArgumentUtil;
import up.light.utils.LogUtil;
import up.light.wait.WaitUtil;

public class ViewNavigator {
	public static ViewTree tree;
	private static String currentName;
	private static AbstractPage currentPage;
	private static int SLEEP_TIME = 1000;

	public static void navigate(String name, AbstractPage targetPage) {
		ArgumentUtil.notBlank(name, "name must not be blank");
		ArgumentUtil.notNull(targetPage, "page must not be null");

		if (currentName.equals(name)) {
			return;
		}

		Map<String, List<ViewNode>> path = tree.getPath(currentName, name);
		List<ViewNode> back = path.get(ViewTree.BACK);
		List<ViewNode> enter = path.get(ViewTree.ENTER);

		LogUtil.log.info("[Navigate] back: " + back);
		LogUtil.log.info("[Navigate] enter: " + enter);

		// 切换到存在的窗口，防止chromedriver卡死
		if (back.size() > 0) {
			DriverFactory.getContextHandler().resetWindow();
		}
		for (ViewNode node : back) {
			WaitUtil.sleep(SLEEP_TIME);
			node.backWithHandler();
			currentName = node.getName();
		}

		for (ViewNode node : enter) {
			WaitUtil.sleep(SLEEP_TIME);
			node.enterWithHandler();
			if (node.getName().equals("资金调度"))
				break;
			currentName = node.getName();

		}

		currentPage = targetPage;
	}

	public static AbstractPage getCurrentPage() {
		return currentPage;
	}

	public static void reset() {
		// reset login infomation
		ViewNode.getLoginer().reset();

		// reset all handlers
		for (ViewNode n : tree.getNodes()) {
			INavigationHandler h = n.getHandler();
			if (h != null) {
				h.reset();
			}
		}

		// navigate to last view
		String last = currentName;
		// reset current view
		currentName = tree.getRoot().getName();
		navigate(last, currentPage);
	}

	public static void setTree(ViewTree tree) {
		ViewNavigator.tree = tree;
		currentName = tree.getRoot().getName();
	}

}
