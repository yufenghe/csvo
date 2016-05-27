package com.id.tools.dict;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

/**
 * 查找标注@FixedDict的类
 */
public class FixedDictPostProcessor implements InitializingBean, ResourceLoaderAware {
	private static final Logger		logger					= LoggerFactory.getLogger(FixedDictPostProcessor.class);
	private List<String>			packages;
	private Map<String, Class<?>>	fixedDictClass;
	private final Class<FixedDict>	FIXED_DICT_ANNOTATION	= FixedDict.class;
	private static final String		CLASS_RESOURCE_PATTERN	= "**/*.class";

	private ResourcePatternResolver	resourcePatternResolver;
	private MetadataReaderFactory	metadataReaderFactory	= new SimpleMetadataReaderFactory();
	private TypeFilter				annotationFilter		= new AnnotationTypeFilter(FIXED_DICT_ANNOTATION);

	public void setPackages(List<String> packages) {
		this.packages = packages;
	}

	public Map<String, Class<?>> getFixedDictClass() {
		return fixedDictClass;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(packages, "packages must not be null");
		if (packages != null) {
			fixedDictClass = new HashMap<String, Class<?>>();

			try {
				for (String p : packages) {
					String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
							+ ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(p)) + "/" + CLASS_RESOURCE_PATTERN;

					Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
					for (Resource resource : resources) {
						if (resource.isReadable()) {
							MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
							if (this.annotationFilter.match(metadataReader, metadataReaderFactory)) {
								Class clazz = Class.forName(metadataReader.getAnnotationMetadata().getClassName());
								if (Enum.class.isAssignableFrom(clazz)) {
									fixedDictClass.put(clazz.getSimpleName(), clazz);
								} else {
									logger.info("class " + clazz + " is not an instance of Enum");
								}
							}
						}
					}
				}
			} catch (IOException ex) {
				throw new RuntimeException("I/O failure during classpath scanning", ex);
			}
		}
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
		this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
	}
}
