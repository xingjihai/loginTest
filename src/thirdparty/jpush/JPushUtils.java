package thirdparty.jpush;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.schedule.ScheduleResult;

public class JPushUtils {
	private static final Logger logger = LoggerFactory.getLogger(JPushUtils.class);
	private static final String appKey = "3ff5ce29ebec69bb37b70de7";
	private static final String masterSecret = "72aee931d5db4358577ea444";
	private static final JPushClient jpushClient = new JPushClient(masterSecret, appKey);

	private JPushUtils() {
	}

	private static PushPayload buildPayload(String title, String alert, Map<String, String> extras, String... alias) {
		// @formatter:off
		return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.alias(alias))
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(AndroidNotification.newBuilder()
								.setAlert(alert)
								.setTitle(title)
								.addExtras(extras)
								.build())
						.addPlatformNotification(IosNotification.newBuilder()
								.setAlert(alert)
								.addExtras(extras)
								.setBadge(0)
								.build())
						.build())
				.setOptions(Options.newBuilder().setApnsProduction(false).build())
				.build();
		// @formatter:on
	}

	public static PushResult sendNotificationByAlias(String title, String alert, Map<String, String> extras, String... alias) {
		PushPayload payload = buildPayload(title, alert, extras, alias);
		System.out.println("payload: "+payload);
		try {
			PushResult pushResult = jpushClient.sendPush(payload);
			System.out.println(pushResult);
			return pushResult;
		} catch (APIConnectionException e) {
			e.printStackTrace();
			return null;
		} catch (APIRequestException e) {
			System.out.println(e.getErrorMessage());
			return null;
		}
	}

	public static ScheduleResult createSchedule(Date time, String title, String alert, Map<String, String> extras, String... alias) {
		String timeStr = DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss");
		PushPayload payload = buildPayload(title, alert, extras, alias);
		try {
			return jpushClient.createSingleSchedule(RandomStringUtils.randomAlphabetic(32), timeStr, payload);
		} catch (APIConnectionException e) {
			throw new RuntimeException(e);
		} catch (APIRequestException e) {
			throw new RuntimeException(e.getErrorMessage(), e);
		}
	}
}
