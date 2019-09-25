package json;

import jarTest.json.JsonUtil;
import json.entity.BookingResponse;
import json.entity.DetailResponse;

public class JsonPropertyTest {

    public static void main(String[] args) {
        String res = "{\"ResponseStatus\":{\"Timestamp\":\"/Date(1551062620152+0800)/\",\"Ack\":\"Success\",\"Errors\":[],\"Version\":\"v1\",\"Extension\":[{\"Id\":\"CLOGGING_TRACE_ID\",\"Value\":\"8760181168363857627\"},{\"Id\":\"RootMessageId\",\"Value\":\"921813-0a0224f1-430850-479860\"}]},\"orderDetailInfo\":{\"roomID\":\"30076628\",\"quantity\":1,\"personCount\":2,\"persons\":[{\"name\":\"QYANG/ERA\"},{\"name\":\"YANG/ZHAOPENG\"}],\"distributorOrderID\":\"190225104328122959\",\"name\":\"chuncuilvxing\",\"phone\":\"5233554\",\"hotelID\":215,\"orderStatus\":1,\"dateRange\":{\"start\":\"/Date(1551715200000+0800)/\",\"end\":\"/Date(1551801600000+0800)/\"},\"totalAmount\":{\"amount\":1177.88,\"currency\":\"CNY\"},\"orderConfirmType\":0,\"orderRejectType\":0,\"remark\":\"\"}}";

        DetailResponse response = JsonUtil.fromJsonLax(res,DetailResponse.class);

        System.out.println(response.getResponseStatusBean().getAck());

        String bookingRes = "{\"ResponseStatus\":{\"Timestamp\":\"/Date(1551062616038+0800)/\",\"Ack\":\"Success\",\"Errors\":[],\"Extension\":[{\"Id\":\"CLOGGING_TRACE_ID\",\"Value\":\"5246233463245400583\"},{\"Id\":\"RootMessageId\",\"Value\":\"921813-0a028730-430850-479173\"}]},\"orderInfo\":{\"orderID\":\"141031\",\"orderStatus\":1,\"isInstantConfirm\":false,\"createDateTime\":\"/Date(1551062615972+0800)/\"},\"message\":{\"code\":\"0\",\"message\":\"success\"}}";

        BookingResponse bkres = JsonUtil.fromJsonLax(bookingRes,BookingResponse.class);

        System.out.println(bkres.getResponseStatus().getAck());


    }
}
