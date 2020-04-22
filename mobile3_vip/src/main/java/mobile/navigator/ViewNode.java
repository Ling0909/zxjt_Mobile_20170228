package mobile.navigator;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import mobile.page.base.PageManager;
import mobile.page.module.ToolBar;
import up.light.DriverFactory;
import up.light.pagefactory.TestElement;

/**
 * view节点，每个节点代表一个UI入口元素
 */
public class ViewNode {
	// back element
	private static ToolBar toolBar = PageManager.getPage(ToolBar.class);

	// login infomation
	private static Loginer loginer = new Loginer();
	private String groupName;

	// node infomation
	private ViewNode parent;
	private String name;
	// private int depth;
	private TestElement element;
	private boolean checklogin;
	private INavigationHandler handler;

	public ViewNode(ViewNode parent, String name, TestElement element, boolean checklogin, INavigationHandler handler) {
		this.parent = parent;
		this.name = name;
		this.element = element;
		this.checklogin = checklogin;
		this.handler = handler;
	}

	public void enter() {
		WebElement e = element.e();

		@SuppressWarnings("unchecked")
		AppiumDriver<WebElement> d = (AppiumDriver<WebElement>) DriverFactory.getDriver();

		if (d.getContext().startsWith("WEBVIEW")) {
			// use javascript to click element
			d.executeScript("arguments[0].click();", e);
		} else {
			e.click();
		}

		if (loginer.needLogin(this)) {
			loginer.login(groupName);
		}
	}

	/*
	 * invoked by ViewNavigator
	 */
	void enterWithHandler() {
		if (handler != null) {
			handler.enter(this);
		} else {
			enter();
		}
	}

	public void back() {
		if (NoOperationBack.isNoOperation(name))
			return;

		toolBar.doBack();
	}

	/*
	 * invoked by ViewNavigator
	 */
	void backWithHandler() {
		if (handler != null) {
			handler.back(this);
		} else {
			back();
		}
	}

	// setter and getter
	public static Loginer getLoginer() {
		return loginer;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public ViewNode getParent() {
		return parent;
	}

	public void setParent(ViewNode parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TestElement getElement() {
		return element;
	}

	public void setElement(TestElement element) {
		this.element = element;
	}

	public boolean isChecklogin() {
		return checklogin;
	}

	public void setChecklogin(boolean checklogin) {
		this.checklogin = checklogin;
	}

	public INavigationHandler getHandler() {
		return handler;
	}

	public void setHandler(INavigationHandler handler) {
		this.handler = handler;
	}

	public String toString() {
		String p = parent != null ? parent.getName() : null;
		return String.format("ViewNode[%s(%s)]", name, p);
	}
}
