package com.great.dao;

import com.great.javabean.Document;
import com.great.javabean.DocumentType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName: DocumentDao <br/>
 * Description: <br/>
 * date: 2020/4/1 10:34<br/>
 *
 * @author lenovo<br />
 * @since JDK 1.8
 */
@Mapper
public interface DocumentDao {

    List<DocumentType> findDocumentType();

    Integer insertInfByUid(Document document);

    List<Document> getDocumentTbl(Map<String, Object> map);

    Integer getDocumentNum(Map<String, Object> map);

    List<Document> getUserDocumentTbl(Map<String, Object> map);

    Integer getUserDocumentTblNum(Map<String, Object> map);
}
