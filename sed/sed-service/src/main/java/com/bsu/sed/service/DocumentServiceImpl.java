package com.bsu.sed.service;

import com.bsu.sed.dao.DocumentDao;
import com.bsu.sed.dao.UserDao;
import com.bsu.sed.model.DocumentCategory;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.persistent.Document;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.utils.DateUtils;
import com.bsu.sed.utils.JsonUtils;
import com.bsu.sed.utils.SecurityUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private DocumentDao documentDao;

    @Value("#{serviceProperties.documentFolder}")
    private String documentFolder;

    private static File root;

    @PostConstruct
    private void init() {
        root = new File(documentFolder);
    }

    @Override
    public Document upload(MultipartFile file) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String fileName = file.getOriginalFilename();
            if (documentDao.exists(fileName)) {
                fileName = buildNewFileName(fileName);
            }
            inputStream = file.getInputStream();

            String directoryPath = buildDirectory();
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    throw new RuntimeException("Can't create directory " + directoryPath);
                }
            }

            File documentFile = new File(directory, fileName);

            outputStream = new FileOutputStream(documentFile);
            IOUtils.copy(inputStream, outputStream);

            User creator = userDao.getByUsername(SecurityUtils.getAuthenticatedUserName());

            Document document = new Document();
            document.setName(fileName);
            document.setCreator(creator);
            document.setPath(DateUtils.getDocumentDate() + "/" + fileName);
            document.setContentType(file.getContentType());
            document.setCategory(DocumentCategory.DOC);
            documentDao.create(document);
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
        return null;
    }

    @Override
    public Document getDocument(Long id) {
        return documentDao.load(id);
    }

    @Override
    public List<Document> find(int limit, int offset) {
        List<Document> documents = documentDao.find(limit, offset);
        for (Document document : documents) {
            Hibernate.initialize(document.getCreator());
        }
        return documents;
    }

    @Override
    public void delete(Long id) {
        Document document = documentDao.load(id);
        File file = new File(documentFolder + "/" + document.getPath());
        if (file.exists()) {
            if (!file.delete()) {
                throw new RuntimeException("Can't delete file :" + document.getName());
            }
            deleteDirectories(file);
        }
        documentDao.delete(id);
    }

    @Override
    public void setRole(Long id, Role role) {
        Document document = documentDao.load(id);
        document.setRole(role);
        documentDao.update(document);
    }

    @Override
    public Role getRole(String fileName) {
        return documentDao.getRole(fileName);
    }

    private void deleteDirectories(File file) {
        File parent = file.getParentFile();
        if (parent != null && parent.isDirectory() && ArrayUtils.isEmpty(parent.list())) {
            if(parent.toPath().toString().equals(root.toPath().toString())) {
                return;
            }
            boolean deleted = parent.delete();
            deleteDirectories(parent);
        }
    }

    @Override
    public void delete(List<Long> ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

    @Override
    public String getJson(int limit, int offset, String search, String sort, SortOrder order) {
        List<String> searchFields = Arrays.asList("name", "contentType", "creator.name");
        List<Document> documents = documentDao.find(searchFields, limit, offset, search, sort, order);
        long total = documentDao.count(searchFields, search);
        return JsonUtils.documentsToJson(documents, total);
    }

    private String buildNewFileName(String oldFileName) {
        String appender = "-" + new Date().getTime();
        if (oldFileName.contains(".")) {
            int lastIndex = oldFileName.lastIndexOf(".");
            String firstPart = oldFileName.substring(0, lastIndex);
            String secondPart = oldFileName.substring(lastIndex, oldFileName.length());
            return firstPart + appender + secondPart;
        } else {
            return oldFileName + appender;
        }
    }

    private String buildDirectory() {
        if (!documentFolder.endsWith("/")) {
            documentFolder += "/";
        }
        return documentFolder + DateUtils.getDocumentDate();
    }
}
