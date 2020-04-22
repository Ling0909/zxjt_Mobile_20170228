package mobile.page;

import org.openqa.selenium.NoSuchElementException;

import mobile.page.base.AbstractPage;
import up.light.pagefactory.TestElement;
import up.light.wait.WaitUtil;

/**
 * 基金分红设置
 * 
 * @category Stage-02
 */
public class PageJJFHSZ extends AbstractPage {
	private TestElement oEditJJDM; // 代码
	private TestElement oTextJJMC; // 名称
	private TestElement oTextJJJZ; // 净值
	private TestElement oBtnFHFS; // 分红方式
	private TestElement oBtnSH; // 设置按钮

	/**
	 * 输入基金代码
	 * 
	 * @param code 基金代码
	 * @return 基金名称
	 */
	public String doInputJJDM(String code) {
		getKeyboard().doInput(oEditJJDM.e(), code);
		loadAndCheck();
		return getText(oTextJJMC.e());
	}

	/**
	 * 选择分红方式
	 * 
	 * @param type 分红方式：红利转投、现金分红
	 */
	public void doSelectFHFS(String type) {
		oBtnFHFS.e().click();
		WaitUtil.sleep(500);

		try {
			driver.findElementByXPath("//li[text()='" + type + "']").click();
		} catch (NoSuchElementException e) {
			throw new RuntimeException("unsupported type: " + type);
		}

	}

	/**
	 * 获取基金净值
	 * 
	 * @return
	 */
	public String doGetJJJZ() {
		return getText(oTextJJJZ.e());
	}

	/**
	 * 点击设置按钮
	 */
	public void doTrade() {
		oBtnSH.e().click();
	}
}
