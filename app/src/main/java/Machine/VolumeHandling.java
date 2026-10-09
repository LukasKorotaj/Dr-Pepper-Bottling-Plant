package Machine;

/**
 * Classes implementing this interface are
 * able, and expected, to handle volume.
 */
public interface VolumeHandling {
    int getSupportedVolume();
    void setSupportedVolume(int ml);

    float getFillSpeed();
    void setFillSpeed(float mlPerSecond);
}
