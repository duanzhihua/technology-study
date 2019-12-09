package cn.guxiangfly.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/10/30 20:38
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        annotationMetadata.getClassName();
        return new String[]{"cn.guxiangfly.pojo.Yellow","cn.guxiangfly.pojo.Blue"};
    }
}
