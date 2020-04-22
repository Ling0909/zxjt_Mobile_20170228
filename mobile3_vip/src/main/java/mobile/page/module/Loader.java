package mobile.page.module;

import mobile.page.base.AbstractPage;
import up.light.Setting;
import up.light.pagefactory.TestElement;
import up.light.wait.WaitUtil;

public class Loader extends AbstractPage {
	private TestElement mImgLoad;
	private boolean mFastMode;

	public Loader() {
		mFastMode = "fast".equals(Setting.getProperty("waitmode"));
	}

	public void waitForLoad() {
		WaitUtil.sleep(WaitUtil.WAIT_SHORT);

		if (!mFastMode) {
			while (WaitUtil.exists(driver, mImgLoad, WaitUtil.WAIT_SHORT))
				;
		}
	}
}
