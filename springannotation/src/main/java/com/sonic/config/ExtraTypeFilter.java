package com.sonic.config;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * ExtraTypeFilter
 *
 * @author Sonic
 * @since 2019/2/17
 */
public class ExtraTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        if (className.equals("com.sonic.extrascanpackage.Building") || className.equals("com.sonic.extrascanpackage.Shop")){
            return true;
        }
        return false;
    }
}
