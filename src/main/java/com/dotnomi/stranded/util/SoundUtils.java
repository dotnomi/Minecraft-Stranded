package com.dotnomi.stranded.util;

import com.dotnomi.stranded.Stranded;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.OggAudioStream;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import javax.sound.sampled.AudioFormat;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SoundUtils {

  public static float getSoundFileDuration(Identifier soundFile) {
    float duration = 0.0f;
    try {
      ResourceManager resourceManager = MinecraftClient.getInstance().getResourceManager();

      Resource resource = resourceManager.getResource(soundFile).orElseThrow(() ->
        new IOException("Sound file not found: " + soundFile)
      );

      try (InputStream inputStream = new BufferedInputStream(resource.getInputStream())) {
        OggAudioStream oggAudioStream = new OggAudioStream(inputStream);

        AudioFormat audioFormat = oggAudioStream.getFormat();
        int sampleRate = (int) audioFormat.getSampleRate();

        int channels = audioFormat.getChannels();
        int bytesPerSample = audioFormat.getSampleSizeInBits() / 8;
        ByteBuffer byteBuffer = oggAudioStream.readAll();
        int totalBytes = byteBuffer.remaining();
        long totalSamples = totalBytes / ((long) channels * bytesPerSample);

        if (sampleRate > 0) {
          duration = (float) totalSamples / sampleRate;
        } else {
          throw new IOException("Invalid sample rate.");
        }
      }
    } catch (Exception exception) {
      Stranded.LOGGER.error("Error while getting sound file duration", exception);
    }
    return duration;
  }
}
