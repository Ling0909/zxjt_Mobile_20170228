package mobile.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import mobile.page.base.AbstractPage;
import up.light.Setting;
import up.light.pagefactory.TestElement;
import up.light.wait.WaitUtil;

public class PageCodeSelect extends AbstractPage {
	private TestElement oEditCode;
	private TestElement oTextItem;
	private TestElement oBtnCancel;
	String xpath;

	public void doInputCode(String code) {
		WaitUtil.sleep(1500);
		getKeyboard().doInput(oEditCode.e(), code);
		WaitUtil.sleep(500);
		oTextItem.e().click();
	}

	public void doInputCode2(String code, String name) {
		WaitUtil.sleep(1500);
		getKeyboard().doInput(oEditCode.e(), code);
		WaitUtil.sleep(500);
		if (Setting.getPlatform().equals("android")) {
			xpath = "//android.widget.TextView[@text = '" + name + "']";
		} else {
			xpath = "//UIATableCell[@name = '" + name + "']";
		}
		WebElement oTextItem = driver.findElement(By.xpath(xpath));
		oTextItem.click();
	}

	@Override
	public void reset() {
		oBtnCancel.e().click();
	}
}
