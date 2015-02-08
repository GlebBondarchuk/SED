package com.bsu.sed.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {
    @Value("#{serviceProperties.documentFolder}")
    private String documentFolder;
}
