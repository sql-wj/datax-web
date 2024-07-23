package com.sql.datax.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 构建hive write dto
 */
@Data
public class HiveWriterDto implements Serializable {

    private String writerDefaultFS;

    private String writerFileType;

    private String writerPath;

    private String writerFileName;

    private String writeMode;

    private String writeFieldDelimiter;
}
