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

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class SrtMCRFactory
{
   
    
    /** Component's uuid */
    private static final String sComponentId = "srt_DD712E4B79B4F45746C92F22E416865B";
    
    /** Component name */
    private static final String sComponentName = "srt";
    
   
    /** Pointer to default component options */
    private static final MWComponentOptions sDefaultComponentOptions = 
        new MWComponentOptions(
            MWCtfExtractLocation.EXTRACT_TO_CACHE, 
            new MWCtfClassLoaderSource(SrtMCRFactory.class)
        );
    
    
    private SrtMCRFactory()
    {
        // Never called.
    }
    
    public static MWMCR newInstance(MWComponentOptions componentOptions) throws MWException
    {
        if (null == componentOptions.getCtfSource()) {
            componentOptions = new MWComponentOptions(componentOptions);
            componentOptions.setCtfSource(sDefaultComponentOptions.getCtfSource());
        }
        return MWMCR.newInstance(
            componentOptions, 
            SrtMCRFactory.class, 
            sComponentName, 
            sComponentId,
            new int[]{8,1,0}
        );
    }
    
    public static MWMCR newInstance() throws MWException
    {
        return newInstance(sDefaultComponentOptions);
    }
}
