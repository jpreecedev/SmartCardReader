/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartcardreader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author ld-008
 */
public class SealRecord {
    private final equipmentType equipment;
    private final ExtendedSerialNumber extendedSealIdentifier;
    
    public String getEquipmentType()
    {
        if (this.equipment != null) {
            return this.equipment.getType(); 
        }
        return "";
    }
    
    public long getIdentifier()
    {
        return this.extendedSealIdentifier.getSerialNumber();
    }
    
    public SealRecord(byte[] paramArrayOfByte) throws UnsupportedEncodingException, IOException {
        assert (paramArrayOfByte.length == 168);
        
        ByteArrayReader localByteArrayReader = new ByteArrayReader(paramArrayOfByte);
        Object localObject1 = null;
        try {
            this.equipment = equipmentType.getEquipmentType(localByteArrayReader.read());
            this.extendedSealIdentifier = new ExtendedSerialNumber(localByteArrayReader.read(8));
        } catch (Throwable localThrowable2) {
            localObject1 = localThrowable2;
            throw localThrowable2;
        } finally {
            if (localByteArrayReader != null) {
                if (localObject1 != null) {
                    try {
                        localByteArrayReader.close();
                    } catch (Throwable localThrowable3) {
                    }
                } else {
                    localByteArrayReader.close();
                }
            }
        }
    }
}
