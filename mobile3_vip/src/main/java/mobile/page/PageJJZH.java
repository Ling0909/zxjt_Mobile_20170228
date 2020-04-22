package mobile.page;

import mobile.page.base.AbstractPage;
import up.light.pagefactory.TestElement;
import up.light.wait.WaitUtil;

/**
 * 基金转换
 * 
 * @category Stage-02
 */
public class PageJJZH extends AbstractPage {
	private TestElement oEditZCDM; // 转出代码
	private TestElement oTextZCMC; // 转出名称
	private TestElement oEditZRDM; // 转入代码
	private TestElement oTextZRMC; // 转入名称
	private TestElement oTextZDKZ; // 最大可转
	private TestElement oEditZHFE; // 转换份额
	private TestElement oBtnZH; // 转换按钮
	private TestElement oMsgRisk; // 风险提示对话框文本

	public String doInputZCDM(String code) {
		getKeyboard().doInput(oEditZCDM.e(), code);
		loadAndCheck();
		return getText(oTextZCMC.e());
	}

	/**
	 * 输入转入代码
	 * 
	 * @param code 转入代码
	 * @return 转入基金名称
	 */
	public String doInputZRDM(String code) {
		getKeyboard().doInput(oEditZRDM.e(), code);
		loadAndCheck();
		return getText(oTextZRMC.e());
	}

	/**
	 * 输入转入份额
	 * 
	 * @param num
	 */
	public void doInputZHFE(String num) {
		getKeyboard().doInput(oEditZHFE.e(), num);
	}

	/**
	 * 获取最大可转
	 * 
	 * @return
	 */
	public String doGetZDKZ() {
		return getText(oTextZDKZ.e());
	}

	/**
	 * 点击转换按钮
	 */
	public void doTrade() {
		oBtnZH.e().click();
	}

	/**
	 * 处理风险提示对话框
	 */
	public void doHandleRiskAlert() {
		if (WaitUtil.exists(driver, oMsgRisk, 1)) {
			getAlert().doAccept();
		}
	}
}
