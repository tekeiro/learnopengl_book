package utils

/**
 * OS (Operative System) Detector
 */
class OSDetector {

    val osName = System.getProperty("os.name").toLowerCase()


    val isWindow: Boolean = "win" in osName
    val isMac: Boolean = "mac" in osName
    val isUnix: Boolean = "nix" in osName
    val isSolaris: Boolean = "sunos" in osName

}