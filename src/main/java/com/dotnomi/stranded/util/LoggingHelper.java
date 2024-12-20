package com.dotnomi.stranded.util;

import com.dotnomi.stranded.Stranded;

public class LoggingHelper {

  public static void sendDebugLog(String message) {
    if (Stranded.CONFIG.getConfigData().getDebugMode()) {
      Stranded.LOGGER.info(message);
    }
  }
}
