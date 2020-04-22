package mobile.page;

import mobile.page.base.ElementOf;
import mobile.page.base.ElementOfs;
import mobile.page.base.PageTradeWithSelect;
import up.light.pagefactory.TestElement;

/**
 * 港股通买入、卖出界面
 * 
 * @category Stage-02
 * @since 2016-08-15
 */
public class PageHKJY extends PageTradeWithSelect {
	@ElementOf(ElementOfs.CODE)
	private TestElement oEditCode;

	@ElementOf(ElementOfs.NAME)
	private TestElement oTextName;

	private TestElement oEditPrice;

	@ElementOf(ElementOfs.NUMBER)
	private TestElement oEditNum;

	@ElementOf(ElementOfs.BUTTON_OK)
	private TestElement oBtnTrade;

	/**
	 * 获取价格编辑框中的文本
	 * 
	 * @return 股票价格文本
	 */
	public String doGetPrice() {
		return getValue(oEditPrice.e());
	}

}
