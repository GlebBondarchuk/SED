package com.bsu.sed.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author gbondarchuk
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    @Value("#{serviceProperties.documentFolder}")
    private String documentFolder;
}
