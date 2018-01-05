package com.rmkj.microcap.common.listener;

import com.rmkj.microcap.common.modules.cache.CacheFacade;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 *
 */
@Component
public final class ContextDestroyListener {

    private static Logger logger = Logger.getLogger(ContextDestroyListener.class);

    @PostConstruct
    public void start() {

    }

    @PreDestroy
    public void stop() {
        CacheFacade.shutdown();
        destroyJDBCDrivers();
    }

    private void destroyJDBCDrivers() {
        final Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver;
        while (drivers.hasMoreElements()) {
            driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.warn(String.format("Deregister JDBC driver %s successful", driver));
            } catch (SQLException e) {
                logger.warn(String.format("Deregister JDBC driver %s error", driver), e);
            }
        }
    }
}
