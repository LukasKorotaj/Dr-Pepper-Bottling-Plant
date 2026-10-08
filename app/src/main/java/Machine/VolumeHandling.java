package Machine;

/**
 * Classes implementing this interface are
 * able, and expected, to handle volume.
 */
public interface VolumeHandling {
    /**
     * Sets the supported volume of the class
     * @param ml
     * @return
     */
    public int supportedVolume(int ml);

    /**
     * Sets the fill speed of the class
     * @param mlPerSecond
     * @return
     */
    public float fillSpeed(float mlPerSecond);
}
