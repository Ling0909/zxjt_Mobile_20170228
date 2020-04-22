package mobile.page.base;

import java.lang.reflect.Field;

import mobile.page.PageCodeSelect;
import mobile.page.module.Alert;
import mobile.page.module.Loader;
import up.light.pagefactory.TestElement;
import up.light.wait.WaitUtil;

/**
 * 带有NATIVE_APP代码选择界面的交易类界面的父类，提供代码输入、数量输入、点击按钮、场景恢复功能
 */
public class PageTradeWithSelect extends AbstractPage {
	private TestElement mElementOfCode;
	private TestElement mElementOfName;
	private TestElement mElementOfNumber;
	private TestElement mElementOfButtonOK;

	private PageCodeSelect mPageCodeSelect = PageManager.getPage(PageCodeSelect.class);
	private Loader mLoader = PageManager.getPage(Loader.class);
	private boolean isInSelectView;

	public PageTradeWithSelect() {
		Field[] fs = this.getClass().getDeclaredFields();

		for (Field f : fs) {
			if (!TestElement.class.isAssignableFrom(f.getType()))
				continue;

			ElementOf ef = f.getAnnotation(ElementOf.class);

			if (ef != null) {
				ElementOfs eType = ef.value();
				f.setAccessible(true);
				TestElement eValue = null;
				try {
					eValue = (TestElement) f.get(this);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}

				switch (eType) {
				case CODE:
					mElementOfCode = eValue;
					break;
				case NAME:
					mElementOfName = eValue;
					break;
				case NUMBER:
					mElementOfNumber = eValue;
					break;
				case BUTTON_OK:
					mElementOfButtonOK = eValue;
					break;
				}
			}
		}
	}

	/**
	 * 输入证券代码
	 * 
	 * @param code 证券代码
	 * @return 证券名称
	 */
	public String doInputCode(String code) {
		// 等待元素出现
		WaitUtil.waitFor(driver, mElementOfCode, WaitUtil.WAIT_MEDIUM).click();
		WaitUtil.sleep(500);

		// 选择代码
		isInSelectView = true;
		mPageCodeSelect.doInputCode(code);
		isInSelectView = false;

		// 等待加载框消失
		mLoader.waitForLoad();

		// 检查对话框
		Alert alert = getAlert();
		if (alert.exists()) {
			throw new RuntimeException(alert.doGetText());
		}

		return getText(mElementOfName.e());
	}

	public void doInputErrorCode(String code) {
		// 等待元素出现
		WaitUtil.waitFor(driver, mElementOfCode, WaitUtil.WAIT_MEDIUM).click();
		WaitUtil.sleep(500);

		// 选择代码
		isInSelectView = true;
		mPageCodeSelect.doInputCode(code);
		isInSelectView = false;

		// 等待加载框消失
		mLoader.waitForLoad();
	}

	/**
	 * 输入证券代码
	 * 
	 * @param code 证券代码
	 * @return 证券名称
	 */
	public String doInputCode2(String code, String name) {
		// 等待元素出现
		WaitUtil.waitFor(driver, mElementOfCode, WaitUtil.WAIT_MEDIUM).click();
		WaitUtil.sleep(500);

		// 选择代码
		isInSelectView = true;
		mPageCodeSelect.doInputCode2(code, name);
		isInSelectView = false;

		// 等待加载框消失
		mLoader.waitForLoad();

		// 检查对话框
		Alert alert = getAlert();
		if (alert.exists()) {
			throw new RuntimeException(alert.doGetText());
		}

		return getText(mElementOfName.e());
	}

	/**
	 * 输入交易数量
	 * 
	 * @param num 数量
	 */
	public void doInputNumber(String num) {
		getKeyboard().doInput(mElementOfNumber.e(), num);
	}

	/**
	 * 点击交易按钮
	 */
	public void doTrade() {
		mElementOfButtonOK.e().click();
	}

	@Override
	public void reset() {
		if (isInSelectView) {
			mPageCodeSelect.reset();
			isInSelectView = false;
		}

		super.reset();
	}
}
