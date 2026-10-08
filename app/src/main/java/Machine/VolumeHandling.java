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
    default int supportedVolume(int ml) {
        return ml;
    };

    /**
     * Sets the fill speed of the class
     * @param mlPerSecond
     * @return
     */
    default float fillSpeed(float mlPerSecond) {
        return mlPerSecond;
    };
}
