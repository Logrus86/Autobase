package com.epam.bp.autobase.util;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.MigrationInfo;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.sql.DataSource;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class FlyWayMigrate {

    @Inject
    Logger log;
    @Resource
    DataSource dataSource;

    @PostConstruct
    public void onStartup() {

        if (dataSource == null) {
            log.info("No datasource found to execute the db migrations!");
        } else {
            Flyway flyway = new Flyway();
            flyway.setInitOnMigrate(true);
            flyway.setDataSource(dataSource);
            for (MigrationInfo i : flyway.info().all()) {
                log.info("Flyway migrate task: " + i.getVersion() + " : "
                        + i.getDescription() + " from file: " + i.getScript());
            }
            try {
                flyway.migrate();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

}
