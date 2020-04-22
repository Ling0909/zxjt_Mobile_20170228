package mobile.page;

import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.WebElement;

import mobile.page.base.ElementOf;
import mobile.page.base.ElementOfs;
import mobile.page.base.PageTradeWithSelect;
import up.light.pagefactory.TestElement;
import up.light.wait.WaitUtil;

public class PageJY extends PageTradeWithSelect {
	@ElementOf(ElementOfs.CODE)
	private TestElement oEditCode;

	@ElementOf(ElementOfs.NAME)
	private TestElement oTextName;

	@ElementOf(ElementOfs.NUMBER)
	private TestElement oEditNum;

	private TestElement oEditPrice;
	private TestElement oMenuWTFS;
	private TestElement oListWTFSes;

	@ElementOf(ElementOfs.BUTTON_OK)
	private TestElement oBtnBuyorSell;

	/**
	 * 获取价格
	 * 
	 * @return
	 */
	public String doGetPrice() {
		return getValue(oEditPrice.e());
	}

	/**
	 * 输入价格（使用js）
	 * 
	 * @param Type用例类型
	 */
	public void doEditPrice(String Type) {
		String p = null;
		if (Type.indexOf("无证券代码") > -1) {
			getValue(oEditPrice.e());
			p = "1";
		} else {
			Float price = Float.parseFloat(getValue(oEditPrice.e()));
			DecimalFormat decimalFormat = new DecimalFormat(".00");// 字符格式这里如果小数不足2位,会以0补足.
			if (Type.indexOf("低于跌停价") > -1) {
				// 需输入低于跌停价，获取页面价格，并改其为自身二分之一
				p = decimalFormat.format(price / 2);// format 返回的是字符串
			} else if (Type.indexOf("超过涨停价") > -1) {
				p = decimalFormat.format(price * 2);// format 返回的是字符串
			} else {
				return;
			}
		}
		String script = "document.getElementById('price').value = \"" + p + "\"";
		// selenium使用js
		driver.executeScript(script);
	}

	/**
	 * 选择委托方式
	 * 
	 * @param WTFS 委托方式
	 */
	public void doChoseWTFS(String WTFS) {
		oMenuWTFS.e().click();
		WaitUtil.sleep(500);
		List<WebElement> es = oListWTFSes.es();
		for (int i = 0; i < es.size(); i++) {
			if (es.get(i).getText().equals(WTFS)) {
				es.get(i).click();
				break;
			}
		}
	}

}
