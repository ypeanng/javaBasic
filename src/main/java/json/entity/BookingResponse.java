package json.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookingResponse {

    @JsonProperty("ResponseStatus")
    ResponseStatusBean responseStatus ;
    /**
     * orderInfo : {"orderID":"10109","orderStatus":2,"isInstantConfirm":true,"createDateTime":"/Date(1500362570238+0800)/"}
     * message : {"code":"0","message":"success"}
     */

    private OrderInfoBean orderInfo;
    private MessageBean message;

    public static class OrderInfoBean {

        /**
         * orderID : 10109
         * orderStatus : 2
         * isInstantConfirm : true
         * createDateTime : /Date(1500362570238+0800)/
         */

        private String orderID;
        private Integer orderStatus;
        private Boolean isInstantConfirm;
        private String createDateTime;

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public Integer getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(Integer orderStatus) {
            this.orderStatus = orderStatus;
        }

        public Boolean isIsInstantConfirm() {
            return isInstantConfirm;
        }

        public void setIsInstantConfirm(Boolean isInstantConfirm) {
            this.isInstantConfirm = isInstantConfirm;
        }

        public String getCreateDateTime() {
            return createDateTime;
        }

        public void setCreateDateTime(String createDateTime) {
            this.createDateTime = createDateTime;
        }
    }

    public static class MessageBean {

        /**
         * code : 0
         * message : success
         */

        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
