package json.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ResponseStatusBean {

    /**
     * Timestamp : /Date(1549463413294+0800)/
     * Ack : Success
     * Errors : []
     * Version : v1
     * Extension : [{"Id":"CLOGGING_TRACE_ID","Value":"5142514677116797383"},{"Id":"RootMessageId","Value":"921813-0a028730-430406-264589"}]
     */
    @JsonProperty("Timestamp")
    private String timestamp;
    @JsonProperty("Ack")
    private String ack;
    @JsonProperty("Version")
    private String version;
    @JsonProperty("Errors")
    private List<ErrorsBean> errors;
    @JsonProperty("Extension")
    private List<ExtensionBean> extension;
    @Data
    public static class ErrorsBean{

        /**
         * Message : String
         * ErrorCode : String
         * StackTrace : String
         * SeverityCode : Error
         * ErrorFields : [{"FieldName":"String","ErrorCode":"String","Message":"String"}]
         * ErrorClassification : ServiceError
         */
        @JsonProperty("Message")
        private String message;
        @JsonProperty("ErrorCode")
        private String errorCode;
        @JsonProperty("StackTrace")
        private String stackTrace;
        @JsonProperty("SeverityCode")
        private String severityCode;
        @JsonProperty("ErrorClassification")
        private String errorClassification;
        @JsonProperty("ErrorFields")
        private List<ErrorFieldsBean> errorFields;
        @Data
        public static class ErrorFieldsBean {

            /**
             * FieldName : String
             * ErrorCode : String
             * Message : String
             */
            @JsonProperty("FieldName")
            private String fieldName;
            @JsonProperty("ErrorCode")
            private String errorCode;
            @JsonProperty("Message")
            private String message;
        }
    }
    @Data
    public static class ExtensionBean {

        /**
         * Id : CLOGGING_TRACE_ID
         * Value : 5142514677116797383
         */
        @JsonProperty("Id")
        private String Id;
        @JsonProperty("Value")
        private String Value;

    }
}
