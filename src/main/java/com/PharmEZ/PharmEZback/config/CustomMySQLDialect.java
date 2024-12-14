package com.PharmEZ.PharmEZback.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class CustomMySQLDialect extends MySQLDialect {

    public CustomMySQLDialect() {
        super();
        // ST_Distance_Sphere 함수
        registerFunction("ST_Distance_Sphere", new StandardSQLFunction("ST_Distance_Sphere", StandardBasicTypes.DOUBLE));
        registerFunction("POINT", new StandardSQLFunction("POINT", StandardBasicTypes.STRING));
        // now
        registerFunction("NOW", new StandardSQLFunction("NOW", StandardBasicTypes.STRING));
        // dayofweek
        registerFunction("DAYOFWEEK", new StandardSQLFunction("DAYOFWEEK", StandardBasicTypes.INTEGER));

        registerFunction("date_format", new SQLFunctionTemplate(StandardBasicTypes.STRING, "DATE_FORMAT(?1, ?2)"));
    }

}
