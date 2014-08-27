package com.epam.bp.autobase.util;


import org.slf4j.LoggerFactory;

public abstract class Logger {
   public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());
}
