package com.jcfc.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

//因为没有加@Configuration注解，所有要使@ConfigurationProperties生效，必须使用@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jcfc.hello")
public class HelloProperties {

    private String prefix;

    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
