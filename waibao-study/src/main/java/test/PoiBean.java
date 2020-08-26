package test;

/**
 * @Author guxiang02
 * @Date 2020/6/10
 **/
public class PoiBean {
    Long poiId;
    String name;

    PoiBean(Long poiId, String name){
        this.name  = name;
        this.poiId = poiId;
    }

    @Override
    public String toString() {
        return "PoiBean{" +
                "poiId=" + poiId +
                ", name='" + name + '\'' +
                '}';
    }
}
