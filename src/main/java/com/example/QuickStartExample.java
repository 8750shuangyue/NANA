package com.example;

import com.github.wechat.ilink.sdk.ILinkClient;
import com.github.wechat.ilink.sdk.core.config.ILinkConfig;
import com.github.wechat.ilink.sdk.core.listener.OnLoginListener;
import com.github.wechat.ilink.sdk.core.listener.OnMessageListener;
import com.github.wechat.ilink.sdk.core.login.LoginContext;
import com.github.wechat.ilink.sdk.core.model.MessageItem;
import com.github.wechat.ilink.sdk.core.model.WeixinMessage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QuickStartExample {
    // 放在类的最顶部，用ConcurrentLinkedQueue避免多线程并发异常
    private static final ConcurrentLinkedQueue<WeixinMessage> realTimeMsgQueue = new ConcurrentLinkedQueue<>();


    public static void main(String[] args) throws Exception {
        ILinkConfig config = ILinkConfig.builder()
                .connectTimeoutMs(35000)
                .readTimeoutMs(35000)
                .writeTimeoutMs(35000)
                .httpMaxRetries(3)
                .retryBaseDelayMs(1000)
                .retryMaxDelayMs(10000)
                .heartbeatEnabled(true)
                .heartbeatIntervalMs(30000)
                .channelVersion("1.0.0")
                .build();

        ILinkClient client = ILinkClient.builder()
                .config(config)
                .onLogin(new OnLoginListener() {
                    @Override
                    public void onLoginSuccess(LoginContext context) {
                        System.out.println("登录成功，botId = " + context.getBotId());
                    }

                    @Override
                    public void onLoginFailure(Throwable throwable) {
                        System.err.println("登录失败: " + throwable.getMessage());
                    }
                })
                .onMessage(new OnMessageListener() {
                    @Override
                    public void onMessages(List<WeixinMessage> messages) {
                        for (WeixinMessage msg : messages) {
                            System.out.println("收到消息 fromUserId = " + msg.getFrom_user_id());
                            realTimeMsgQueue.offer(msg);
                            if (msg.getItem_list() != null) {
                                for (MessageItem item : msg.getItem_list()) {
                                    if (item.getText_item() != null) {
                                        System.out.println("text = " + item.getText_item().getText());

                                    }
                                }
                            }
                        }
                    }
                })
                .build();

        try {
                String qrCodeContent = client.executeLogin();
                System.out.println("请扫码登录：");
                System.out.println(qrCodeContent);

                LoginContext context = client.getLoginFuture().get();
                System.out.println("登录完成，botId = " + context.getBotId());
                WeixinMessage currentMsg = realTimeMsgQueue.poll();
            while (true) {

                List<WeixinMessage> messages = client.getUpdates();
                System.out.println("首次拉取消息数 = " + messages.size());

                if (!messages.isEmpty()) {
                    String targetUserId = String.valueOf(messages.get(0).getFrom_user_id());
                    if (currentMsg.getItem_list().contains("OMO")){
                        client.sendText(targetUserId,"QWQ");
                        client.sendTextWithTyping(targetUserId,"TAT",1500L);
                        break;
                    }
                    client.sendText(targetUserId, "Hello, iLink!");
                    client.sendTextWithTyping(targetUserId, "这是一条带输入态的消息", 1500L);
                }
            }
        } finally {
            client.close();

        }
    }
}
