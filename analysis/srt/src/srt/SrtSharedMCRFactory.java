/*
 * MATLAB Compiler: 4.18.1 (R2013a)
 * Date: Sun Dec 29 20:40:37 2013
 * Arguments: "-B" "macro_default" "-W" "java:srt,photo_feature" "-T" "link:lib" "-d" 
 * "/usr/local/google/home/zrfan/srt/srt/analysis/srt/src" "-w" 
 * "enable:specified_file_mismatch" "-w" "enable:repeated_file" "-w" 
 * "enable:switch_ignored" "-w" "enable:missing_lib_sentinel" "-w" "enable:demo_license" 
 * "-S" "-v" 
 * "class{photo_feature:/usr/local/google/home/zrfan/srt/srt/analysis/color_f.m,/usr/local/google/home/zrfan/srt/srt/analysis/face_detection.m,/usr/local/google/home/zrfan/srt/srt/analysis/texture_f.m}" 
 */

package srt;

import java.util.concurrent.Callable;
import com.mathworks.toolbox.javabuilder.internal.MWMCR;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWComponentOptions;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class SrtSharedMCRFactory
{
    /// The singleton MWMCR instance for this component
    private static volatile MWMCR sSharedMCR = null;
    
    /// A shutdown task (Runnable) that disposes of the shared MWMCR instance
    // (initially do nothing, since sSharedMCR is lazily created)
    private static volatile Runnable sShutdownTask = new Runnable() { public void run () {} };
    
    private SrtSharedMCRFactory () {
        // Never called.
    }
    
    private static MWMCR getInstance (Callable<MWMCR> createInstance) throws MWException {
        synchronized(SrtSharedMCRFactory.class) {
            if (null == sSharedMCR) {
                try {
                    sSharedMCR = createInstance.call();
                } catch (Exception e) {
                    assert(e instanceof MWException);
                    throw (MWException)e;
                }                
                sShutdownTask = MWMCR.scheduleShutdownTask(new Runnable() {
                    public void run () {
                        synchronized(SrtSharedMCRFactory.class) {
                            assert( null != SrtSharedMCRFactory.sSharedMCR );
                            SrtSharedMCRFactory.sSharedMCR.dispose();
                            SrtSharedMCRFactory.sSharedMCR = null;
                        }
                    }
                });
            }
            sSharedMCR.use();
            return sSharedMCR;
        }
    }
    
    public static MWMCR newInstance () throws MWException {
        return getInstance(new Callable<MWMCR>() {
            public MWMCR call () throws Exception {
                return SrtMCRFactory.newInstance();
            }
        });
    }
    
    public static MWMCR newInstance (final MWComponentOptions componentOptions) throws MWException {
        return getInstance(new Callable<MWMCR>() {
            public MWMCR call () throws Exception {
                return SrtMCRFactory.newInstance(componentOptions);
            }
        });
    }

    /// Releases the shared MWMCR instance and cancels its associated shutdown task.  Subsequent calls to 
    ///  newInstance will create another MWMCR.  It is necessary to call this method if this class
    ///  is to be properly unloaded prior to JVM shutdown.  It is not necessary to call this method if 
    ///  this class does not need to be unloaded before normal JVM shutdown.
    public static void releaseSharedMCR () {
        sShutdownTask.run();
    }
}
