package json.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class DetailResponse {

    @JsonProperty("ResponseStatus")
    ResponseStatusBean responseStatusBean;
    /**
     * orderDetailInfo : {"roomID":"25019278","quantity":3,"personCount":1,"persons":[{"name":"er"}],"distributorOrderID":"200012","name":"dd","phone":"15021536996","dateRange":{"start":"/Date(1500739200000+0800)/","end":"/Date(1500912000000+0800)/"},"lastArrivalTime":"18:00","totalAmount":{"amount":1000,"currency":"CNY"},"orderConfirmType":0,"orderRejectType":0,"remark":"ada"}
     */

    private OrderDetailInfoBean orderDetailInfo;

    @Data
    public static class OrderDetailInfoBean {

        /**
         * roomID : 25019278
         * quantity : 3
         * personCount : 1
         * persons : [{"name":"er"}]
         * distributorOrderID : 200012
         * name : dd
         * phone : 15021536996
         * dateRange : {"start":"/Date(1500739200000+0800)/","end":"/Date(1500912000000+0800)/"}
         * lastArrivalTime : 18:00
         * totalAmount : {"amount":1000,"currency":"CNY"}
         * orderConfirmType : 0
         * orderRejectType : 0
         * remark : ada
         */

        private String roomID;
        private Integer quantity;
        private Integer personCount;
        private List<PersonsBean> persons;
        private String distributorOrderID;
        private String name;
        private String phone;
        private Integer hotelId;
        private DateRangeBean dateRange;
        private String lastArrivalTime;
        private TotalAmountBean totalAmount;
        private Integer orderStatus;
        private String orderConfirmNo;
        private Integer orderConfirmType;
        private Integer orderRejectType;
        private String remark;

        @Data
        public static class DateRangeBean {

            /**
             * start : /Date(1500739200000+0800)/
             * end : /Date(1500912000000+0800)/
             */

            private String start;
            private String end;

        }

        @Data
        public static class TotalAmountBean {

            /**
             * amount : 1000
             * currency : CNY
             */

            private BigDecimal amount;
            private String currency;

        }

        @Data
        public static class PersonsBean {

            /**
             * name : er
             */

            private String name;
        }
    }
}
