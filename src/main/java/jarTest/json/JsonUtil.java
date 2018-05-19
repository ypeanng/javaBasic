package jarTest.json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
/**
 * Created by wcydh on 16/4/12.
 */
public class JsonUtil {

    public static String toJsonWithoutNull(Object object) {
        String result = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String toJson(Object object) {
        return toJsonDefault(object);
    }

    public static String toJsonDefault(Object object) {
        String result = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static <T> T fromJson(String jsonString, Class<T> type) {
        Object o = null;
        try {
            o = new ObjectMapper().readValue(jsonString, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) o;

    }

    public static <T> T fromJsonThrown(String jsonString, Class<T> type) throws IOException {
        return new ObjectMapper().readValue(jsonString, type);
    }

    public static <T> T fromJsonLaxThrown(String jsonString, Class<T> type) throws Exception {
        Object o = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        o = objectMapper.readValue(jsonString, type);
        return (T) o;

    }

    public static <T> T fromJsonLax(String jsonString, Class<T> type) {
        Object o = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            o = objectMapper.readValue(jsonString, type);
        } catch (Exception e){
            e.printStackTrace();
        }
        return (T) o;

    }

    public static <T> List<T> fromJsonLax_List(String jsonString,Class<T> contentType){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, contentType);
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, contentType);
        Object o = null;
        try {
            o = objectMapper.readValue(jsonString, collectionType);
            return (List<T>) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static <K,V> Map<K,V> fromJsonLax_Map(String jsonString,Class<K> key,Class<V> value){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType keyType = objectMapper.getTypeFactory().constructType(key);
        JavaType valueType = objectMapper.getTypeFactory().constructType(value);
        JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class,keyType,valueType);
        Object o = null;
        try {
            o = objectMapper.readValue(jsonString, javaType);
            return (Map<K, V>) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();

    }

    /**
     * 适用于 Map<String,List<T>>
     * @param jsonString
     * @param contentType
     * @param <T>
     * @return
     */
    public static <T> Map<String,List<T>> fromJsonLax_MapList(String jsonString, Class<T> contentType){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType keyType = objectMapper.getTypeFactory().constructType(String.class);
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, contentType);
        JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class,keyType,collectionType);
        Object o = null;
        try {
            o = objectMapper.readValue(jsonString, javaType);
            return (Map<String, List<T>>) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }


    /**
     * 对象转json,忽略注解
     * 即无视对象字段的@JsonProperty("")属性,转化出来的是长json
     *
     * @param object
     * @return
     */
    public static String toJsonWithoutAnnotations(Object object) {
        String result = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从json转对象,忽略注解
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJsonWithoutAnnotations(String jsonString, Class<T> type) {
        Object o = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
            o = objectMapper.readValue(jsonString, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) o;
    }

    /**
     * 从json转对象,忽略注解,未知属性不失败
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJsonWithoutAnnotationsLax(String jsonString, Class<T> type) {
        Object o = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
            o = objectMapper.readValue(jsonString, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) o;
    }

    /**
     * 根据json和TypeReference转换成对象.用于包裹泛型的情况
     * 使用方法如下
     * CommonCover1<Data> dataCommonCover1 = fromJsonLaxByTypeReference(str, new TypeReference<CommonCover1<Data>>() {});
     * CommonCover2<Data, User> dataUserCommonCover2 = fromJsonLaxByTypeReference(str2, new TypeReference<CommonCover2<Data, User>>() {});
     *
     * @param jsonString
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T fromJsonLaxByTypeReference(String jsonString, TypeReference<T> valueTypeRef) {
        Object o = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            o = objectMapper.readValue(jsonString, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) o;
    }

}
