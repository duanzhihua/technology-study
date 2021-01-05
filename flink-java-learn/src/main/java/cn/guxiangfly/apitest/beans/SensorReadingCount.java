package cn.guxiangfly.apitest.beans;

/**
 * @Author guxiang02
 * @Date 2021/1/2
 **/
public class SensorReadingCount {
    String key;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    Integer count;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "SensorReadingCount{" +
                "key='" + key + '\'' +
                ", count=" + count +
                '}';
    }
}
