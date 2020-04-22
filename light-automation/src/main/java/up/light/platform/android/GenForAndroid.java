package up.light.platform.android;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import up.light.Setting;
import up.light.platform.IDriverGenerator;
import up.light.utils.LogUtil;

/**
 * @version 1.0
 */
public class GenForAndroid implements IDriverGenerator {

	@Override
	public WebDriver generate() {
		/*AndroidDriver<AndroidElement> driver = null;
		String nodeJS = Setting.getProperty("nodejs");
		String appiumJS = Setting.getProperty("appium");

		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.usingDriverExecutable(new File(nodeJS));
		builder.withAppiumJS(new File(appiumJS));
		builder.withLogFile(new File(
				Setting.getPath(FolderType.LOG) + DateUtil.getDateString("'appium.'yyyy.MM.dd-HH.mm.ss'.txt'")));
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "warn:debug");
		AppiumDriverLocalService service = builder.build();
		driver = new AndroidDriver<>(service, getDc());
		driver.manage().timeouts().implicitlyWait(Integer.valueOf(Setting.getProperty("driver.timeout")),
				TimeUnit.SECONDS);*/

		 AndroidDriver<AndroidElement> driver = null;
		 try {
		 driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
		 getDc());
		 } catch (MalformedURLException e) {
		 throw new RuntimeException(e);
		 }
		LogUtil.log.info("[Driver] generate driver " + driver);

		return driver;
	}

	private DesiredCapabilities getDc() {
		DesiredCapabilities dc = new DesiredCapabilities();
		String key, value;

		for (Map.Entry<String, String> e : Setting.getProperties()) {
			key = e.getKey();
			if (key.startsWith("dc.")) {
				value = e.getValue();
				dc.setCapability(key.substring(3), value);
			}
		}

		return dc;
	}
}
