package com.yzp.esconfig;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.Test;

public class TestElasticsearchClient {

    @Test
    public void testInit() throws UnknownHostException {
        //创建ES客户端
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY);

        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("182.92.107.17"),9300);
        transportClient.addTransportAddress(transportAddress);

        transportClient.close();
    }
}
