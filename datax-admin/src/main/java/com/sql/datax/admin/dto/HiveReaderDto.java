package com.sql.datax.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 构建hive reader dto
 */
@Data
public class HiveReaderDto implements Serializable {

    private String readerPath;

    private String readerDefaultFS;

    private String readerFileType;

    private String readerFieldDelimiter;

    private Boolean readerSkipHeader;

}
