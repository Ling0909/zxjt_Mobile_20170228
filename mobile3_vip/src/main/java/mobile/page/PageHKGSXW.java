package mobile.page;

import org.openqa.selenium.By;

import mobile.page.base.ElementOf;
import mobile.page.base.ElementOfs;
import mobile.page.base.PageTradeWithSelect;
import up.light.pagefactory.TestElement;
import up.light.wait.WaitUtil;

/**
 * 港股通公司行为
 * 
 * @category Stage-02
 */
public class PageHKGSXW extends PageTradeWithSelect {
	@ElementOf(ElementOfs.CODE)
	private TestElement oEditCode;

	@ElementOf(ElementOfs.NAME)
	private TestElement oTextName;

	private TestElement oLabelYWLX;
	private TestElement oEditXWDM;
	private TestElement oLabelSBLX;
	private TestElement oEditWTBH;

	@ElementOf(ElementOfs.NUMBER)
	private TestElement oEditSBSL;

	@ElementOf(ElementOfs.BUTTON_OK)
	private TestElement oBtnSubmit;

	/**
	 * 选择业务、申报类型
	 * 
	 * @param isYW 是否为业务类型
	 * @param type 类型名称
	 */
	public void doChaneType(boolean isYW, String type) {
		if (isYW) {
			oLabelYWLX.e().click();
		} else {
			oLabelSBLX.e().click();
		}

		WaitUtil.sleep(200);
		String xpath = "//div[@class='kmc-downMenu']/ul/li[text()='" + type + "']";
		driver.findElement(By.xpath(xpath)).click();

		if ("撤销".equals(type)) {
			getKeyboard().doInput(oEditWTBH.e(), "0");
		}
	}

	/**
	 * 输入行为代码
	 * 
	 * @param XWCode 行为代码
	 */
	public void doInputXWCode(String XWCode) {
		getKeyboard().doInput(oEditXWDM.e(), XWCode);
	}
}
