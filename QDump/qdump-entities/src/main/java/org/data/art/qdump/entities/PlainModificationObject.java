package org.data.art.qdump.entities;

public interface PlainModificationObject {
	
    /**

     * Get information about who and when was created this object.
	
     * 
	
     * @return Should be not <code>null</code> {@link PlainModificationObject} value.
	
     */
	
    HasModificationInfo getModification();
	
	
}