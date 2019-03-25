/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smartcardreader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class gen2CalibrationRecord {

    public static final int RECORD_SIZE = 168;
    private static final Date INVALID_DATE = new Date(4294967295000L);
    private static final long serialVersionUID = 1L;

    private final CalibrationPurpose purpose;
    private final String vehicleIdentificationNumber;
    private final Nation vehicleRegistrationNation;
    private String vehicleRegistrationNumber;
    private final int wValue;
    private final int kValue;
    private final int tyreCircumference;
    private final String tyreSize;
    private final int maxSpeed;
    private final int odometerValue;
    private final Date calibrationTime;
    private final Date nextCalibrationDate;
    private final String vuPartNumber;
    private final ExtendedSerialNumber vuSerialNumber;
    private final ExtendedSerialNumber sensorSerialNumber;
    private final ExtendedSerialNumber sensorGNSSSerialNumber;
    private final ExtendedSerialNumber rcmSerialNumber;
    private final int vuAbility;
    private final int numOfSealRecords;
    private final SealRecord sealRecord1;
    private final SealRecord sealRecord2;
    private final SealRecord sealRecord3;
    private final SealRecord sealRecord4;
    private final SealRecord sealRecord5;


    private String cardSerialNumber;

    public gen2CalibrationRecord(byte[] paramArrayOfByte)
            throws UnsupportedEncodingException, IOException {
        assert (paramArrayOfByte.length == 168);

        ByteArrayReader localByteArrayReader = new ByteArrayReader(paramArrayOfByte);
        Object localObject1 = null;
        try {
            this.purpose = CalibrationPurpose.get(localByteArrayReader.read());
            this.vehicleIdentificationNumber = new String(localByteArrayReader.read(17), "US-ASCII").trim();

            this.vehicleRegistrationNation = Nation.getNation(localByteArrayReader.read());

            int i = localByteArrayReader.read();
            try {
                this.vehicleRegistrationNumber = new String(localByteArrayReader.read(13), "iso-8859-" + i).trim();
            } catch (Exception localException) {
                this.vehicleRegistrationNumber = "";
            }

            this.wValue = Utilities.toInt(localByteArrayReader.read(2));

            this.kValue = Utilities.toInt(localByteArrayReader.read(2));

            this.tyreCircumference = (Utilities.toInt(localByteArrayReader.read(2)) / 8);

            this.tyreSize = new String(localByteArrayReader.read(15), "US-ASCII").trim();

            this.maxSpeed = localByteArrayReader.read();

            localByteArrayReader.discard(3);

            this.odometerValue = Utilities.toInt(localByteArrayReader.read(3));

            localByteArrayReader.discard(4);

            Date localDate = new Date(Utilities.toLong(localByteArrayReader.read(4)) * 1000L);

            if (localDate.compareTo(INVALID_DATE) == 0) {
                this.calibrationTime = new Date();
            } else {
                this.calibrationTime = localDate;
            }

            this.nextCalibrationDate = new Date(Utilities.toLong(localByteArrayReader.read(4)) * 1000L);

            this.vuPartNumber = new String(localByteArrayReader.read(16), "US-ASCII").trim();

            this.vuSerialNumber = new ExtendedSerialNumber(localByteArrayReader.read(8));
            this.sensorSerialNumber = new ExtendedSerialNumber(localByteArrayReader.read(8));
            this.sensorGNSSSerialNumber = new ExtendedSerialNumber(localByteArrayReader.read(8));
            this.rcmSerialNumber = new ExtendedSerialNumber(localByteArrayReader.read(8));
            this.vuAbility = localByteArrayReader.read();
            this.numOfSealRecords = localByteArrayReader.read();
            this.sealRecord1 = new SealRecord(localByteArrayReader.read(9));
            this.sealRecord2 = new SealRecord(localByteArrayReader.read(9));
            this.sealRecord3 = new SealRecord(localByteArrayReader.read(9));
            this.sealRecord4 = new SealRecord(localByteArrayReader.read(9));
            this.sealRecord5 = new SealRecord(localByteArrayReader.read(9));

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

    public CalibrationPurpose getPurpose() {
        return this.purpose;
    }

    public String getVehicleIdentificationNumber() {
        return this.vehicleIdentificationNumber;
    }

    public Nation getVehicleRegistrationNation() {
        return this.vehicleRegistrationNation;
    }

    public String getVehicleRegistrationNumber() {
        return this.vehicleRegistrationNumber;
    }

    public int getTyreCircumference() {
        return this.tyreCircumference;
    }

    public String getTyreSize() {
        return this.tyreSize;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public int getOdometerValue() {
        return this.odometerValue;
    }

    public Date getCalibrationTime() {
        return this.calibrationTime;
    }

    public Date getNextCalibrationDate() {
        return this.nextCalibrationDate;
    }

    public String getVuPartNumber() {
        return this.vuPartNumber;
    }

    public ExtendedSerialNumber getVuSerialNumber() {
        return this.vuSerialNumber;
    }

    public ExtendedSerialNumber getSensorSerialNumber() {
        return this.sensorSerialNumber;
    }

    public String getCardSerialNumber() {
        return this.cardSerialNumber;
    }

    public void setCardSerialNumber(String serialNumber) {
        this.cardSerialNumber = serialNumber;
    }

    public String toXML() {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Charset localCharset = Charset.defaultCharset();

        StringBuilder sb = new StringBuilder();
        sb.append(("<CalibrationRecord>"));

        sb.append("<CalibrationTime>" + localSimpleDateFormat.format(this.calibrationTime) + "</CalibrationTime>");
        sb.append("<MaxSpeed>" + this.maxSpeed + "</MaxSpeed>");
        sb.append("<NextCalibrationDate>" + localSimpleDateFormat.format(this.nextCalibrationDate) + "</NextCalibrationDate>");
        sb.append("<OdometerValue>" + this.odometerValue + "</OdometerValue>");
        sb.append("<Purpose>" + this.purpose + "</Purpose>");
        sb.append("<SensorSerialNumber>" + this.sensorSerialNumber.getSerialNumber() + "</SensorSerialNumber>");
        sb.append("<TyreSize>" + new String(this.tyreSize.getBytes(localCharset)) + "</TyreSize>");
        sb.append("<TyreCircumference>" + this.tyreCircumference + "</TyreCircumference>");
        sb.append("<VehicleIdentificationNumber>" + new String(this.vehicleIdentificationNumber.getBytes(localCharset)).replaceAll("\\p{Cc}", "") + "</VehicleIdentificationNumber>");
        sb.append("<VehicleRegistrationNation>" + this.vehicleRegistrationNation + "</VehicleRegistrationNation>");
        sb.append("<VehicleRegistrationNumber>" + new String(this.vehicleRegistrationNumber.getBytes(localCharset)) + "</VehicleRegistrationNumber>");
        sb.append("<VuPartNumber>" + new String(this.vuPartNumber.getBytes(localCharset)).replaceAll("\\p{Cc}", "") + "</VuPartNumber>");
        sb.append("<VuSerialNumber>" + this.vuSerialNumber.getSerialNumber() + "</VuSerialNumber>");
        sb.append("<VuManufacturer>" + this.vuSerialNumber.getManufacturer().getName() + "</VuManufacturer>");
        sb.append("<WFactor>" + this.wValue + "</WFactor>");
        sb.append("<KFactor>" + this.kValue + "</KFactor>");
        sb.append("<SensorGNSSSerialNumber>" + this.sensorGNSSSerialNumber.getSerialNumber()+ "</SensorGNSSSerialNumber>");
        sb.append("<RcmSerialNumber>" + this.rcmSerialNumber.getSerialNumber()+ "</RcmSerialNumber>");
        sb.append("<VuAbility>" + this.vuAbility + "</VuAbility>");
        sb.append("<CardSerialNumber>" + this.cardSerialNumber + "</CardSerialNumber>");
        sb.append("<NoOfSealRecords>" + this.numOfSealRecords + "</NoOfSealRecords>");

        sb.append("<SealRecords>");
        for(int i = 0;i<this.numOfSealRecords;i++)
        {
            if(i==0){
                sb.append("<SealRecord>");
                sb.append("<EquipmentType>" + this.sealRecord1.getEquipmentType() + "</EquipmentType>");
                sb.append("<ExtendedSealIdentifier>" + this.sealRecord1.getIdentifier() + "</ExtendedSealIdentifier>");
                sb.append("</SealRecord>");
            }
            if(i==1){
                sb.append("<SealRecord>");
                sb.append("<EquipmentType>" + this.sealRecord2.getEquipmentType() + "</EquipmentType>");
                sb.append("<ExtendedSealIdentifier>" + this.sealRecord2.getIdentifier() + "</ExtendedSealIdentifier>");
                sb.append("</SealRecord>");
            }
            if(i==2){
                sb.append("<SealRecord>");
                sb.append("<EquipmentType>" + this.sealRecord3.getEquipmentType() + "</EquipmentType>");
                sb.append("<ExtendedSealIdentifier>" + this.sealRecord3.getIdentifier() + "</ExtendedSealIdentifier>");
                sb.append("</SealRecord>");
            }
            if(i==3){
                sb.append("<SealRecord>");
                sb.append("<EquipmentType>" + this.sealRecord4.getEquipmentType() + "</EquipmentType>");
                sb.append("<ExtendedSealIdentifier>" + this.sealRecord4.getIdentifier() + "</ExtendedSealIdentifier>");
                sb.append("</SealRecord>");
            }
            if(i==4){
                sb.append("<SealRecord>");
                sb.append("<EquipmentType>" + this.sealRecord5.getEquipmentType() + "</EquipmentType>");
                sb.append("<ExtendedSealIdentifier>" + this.sealRecord5.getIdentifier() + "</ExtendedSealIdentifier>");
                sb.append("</SealRecord>");
            }
        }
        sb.append("</SealRecords>");
        sb.append("</CalibrationRecord>");

        return sb.toString();
    }

    public int getkValue() {
        return this.kValue;
    }

    public int getwValue() {
        return this.wValue;
    }

    public int compareTo(gen2CalibrationRecord paramCalibrationRecord) {
        return -this.calibrationTime.compareTo(paramCalibrationRecord.calibrationTime);
    }
}