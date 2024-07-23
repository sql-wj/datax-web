package com.sql.datax.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 构建json dto
 */
@Data
public class RdbmsWriterDto implements Serializable {

    private String preSql;

    private String postSql;
}
