package com.holley.platform.common.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * resource loader
 */
public class DefaultCommResourceLoader implements ResourceLoader {

    private static final Log             logger         = LogFactory.getLog(DefaultResourceLoader.class);

    private static DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
    private static RuntimeException      initException  = null;

    /**
     * 得到资源
     */
    public Resource getResource(String resFile) {
        if (initException != null) {
            throw initException;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getResource(String) - resource = " + resFile);
        }

        return findResource(resFile);

    }

    /**
     * 查找资源。调用spring提供的方式，也可以再次扩展
     * 
     * @param location 资源名称
     * @return 资源对象
     */
    private Resource findResource(String location) {

        Resource resource = resourceLoader.getResource(location);
        return resource;

    }

    public ClassLoader getClassLoader() {
        return null;
    }

}
