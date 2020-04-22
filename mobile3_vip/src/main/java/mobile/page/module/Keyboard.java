package mobile.page.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import mobile.page.base.AbstractPage;
import up.light.Setting;
import up.light.pagefactory.TestElement;
import up.light.utils.LogUtil;
import up.light.wait.WaitUtil;

public class Keyboard extends AbstractPage {
	private static final String PLATFORM = Setting.getPlatform();
	// android识别整块，ios识别数字1
	private TestElement oViewKeyboard;
	// private TestElement oBtnClose;
	private boolean inited, inited2;
	private Map<Character, Point> pointsTemp;
	private Map<Character, Point> points;
	private Map<Character, Point> points2;

	public void doInput(WebElement e, String str) {
		LogUtil.log.debug("[Keyboard Input] " + str);

		e.click();

		/*
		 * try { att = e.getAttribute("readonly"); } catch (Exception ex) { }
		 * 
		 * if (!"true".equals(att)) { e.sendKeys(str); return; }
		 * 
		 * e.click();
		 */
		// WaitUtil.sleep(1000);

		WebElement ele = null;
		if ("ios".equals(PLATFORM)) {
			ele = oViewKeyboard.e();

		} else if ("android".equals(PLATFORM)) {
			List<WebElement> es = oViewKeyboard.es();
			if (es.size() == 15) {
				ele = es.get(0);
			} else if (es.size() == 19) {
				ele = es.get(1);
			}
		}

		Point vPoint = ele.getLocation();
		if (vPoint.getX() == 0) {
			if (!inited) {
				points = inited(ele);
				inited = true;
			}
			// 循环参数字符串中的每个字符计算坐标并点击
			input(points, str);
		} else {
			if (!inited2) {
				points2 = inited(ele);
				inited2 = true;
			}
			// 循环参数字符串中的每个字符计算坐标并点击
			input(points2, str);
		}
	}

	public boolean exists() {
		return WaitUtil.exists(driver, oViewKeyboard, WaitUtil.WAIT_SHORT);
	}

	public Map<Character, Point> inited(WebElement eleTemp) {

		Point vPoint = eleTemp.getLocation();
		// 键盘左上角坐标
		int vX = vPoint.getX();
		int vY = vPoint.getY();
		Dimension vSize = eleTemp.getSize();
		// 每一块的宽、高
		int vWidth = 0, vHeight = 0;

		// 根据driver类型分类处理
		// android的键盘只能识别为一整块
		// ios的键盘可以单独识别每个按键

		vWidth = vSize.getWidth();
		vHeight = vSize.getHeight();

		pointsTemp = new HashMap<>();
		int vRow = 0, vColumn = 0;

		pointsTemp.put(Character.valueOf('0'), new Point(vX + vWidth + vWidth / 2, vY + 3 * vHeight + vHeight / 2));

		for (char i = '1'; i <= '9'; ++i) {
			vRow = (i - 49) / 3;
			vColumn = (i - 49) % 3;
			Point p = new Point(vX + vColumn * vWidth + vWidth / 2, vY + vRow * vHeight + vHeight / 2);
			pointsTemp.put(Character.valueOf(i), p);
		}

		// inited = true;
		LogUtil.log.debug("[Initialize Keyboard] " + points);
		return pointsTemp;
	}

	public void input(Map<Character, Point> pointsTemp, String str) {
		for (int i = 0; i < str.length(); ++i) {
			char c = str.charAt(i);
			Point p = pointsTemp.get(c);
			driver.tap(1, p.x, p.y, 100);
			LogUtil.log.debug("[Keyboard Input] tap point: " + p.x + ", " + p.y);
		}

		// if (exists()) {
		// oBtnClose.e().click();
		// }
	}
}
