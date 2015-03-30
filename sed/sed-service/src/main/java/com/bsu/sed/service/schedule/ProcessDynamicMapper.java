package com.bsu.sed.service.schedule;

import com.bsu.sed.model.BackgroundProcessKey;
import com.bsu.sed.service.process.NewsDeleteProcess;
import com.bsu.sed.service.process.NewsUpdateProcess;
import com.bsu.sed.service.process.ProcessExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gbondarchuk
 */
@Component
public class ProcessDynamicMapper {
    @Autowired
    private NewsUpdateProcess newsUpdateProcess;
    @Autowired
    private NewsDeleteProcess newsDeleteProcess;

    /**
     * Relations between processes and executable classes.
     */
    private Map<BackgroundProcessKey, ProcessExecutor> processMap;

    @PostConstruct
    private void init() {
        processMap = Collections.unmodifiableMap(new HashMap<BackgroundProcessKey, ProcessExecutor>() {{
            put(BackgroundProcessKey.UPDATE_NEWS, newsUpdateProcess);
            put(BackgroundProcessKey.DELETE_NEWS, newsDeleteProcess);
        }});
    }

    public Object getTargetClass(BackgroundProcessKey processKey) {
        return processMap.get(processKey);
    }

}
