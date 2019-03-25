/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smartcardreader;

public enum equipmentType
{
  NA(0, " "), 

  DRIVER_CARD(1, "DRIVER CARD"), 

  WORKSHOP_CARD(2, "WORSKHOP CARD"), 

  CONTROL_CARD(3, "CONTROL CARD"), 

  COMPANY_CARD(4, "COMPANY CARD"), 

  MANUFACTURING_CARD(5, "MANUFACTURING CARD"), 

  VEHICLE_UNIT(6, "VEHICLE UNIT"), 

  MOTION_SENSOR(7, "MOTION SENSOR"), 

  GNSS_FACILITY(8, "GNSS FACILITY"), 

  REMOTE_COMMS(9, "REMOTE COMMUNICATION MODULE"), 

  ITS_INTERFACE(10, "ITS INTERFACE MODULE"), 

  PLAQUE(11, "PLAQUE"), 

  M1_ADAPTER(12, "M1/N1 ADAPTER"), 

  EURO_CA(13, "EUROPEAN ROOT CA (ERCA)"), 

  MEMBER_CA(14, "MEMBER STATE CA (MSCA)"), 

  EXTERN_GNSS(15, "EXTERNAL GNSS CONNECTION"), 

  UNUSED(16, "UNUSED");

  private final int value;
  private final String type;

  private equipmentType(int paramInt, String paramString) {
    this.value = paramInt;
    this.type = paramString;
  }

  public String getType()
  {
    return this.type;
  }

  public static equipmentType getEquipmentType(int paramInt)
  {
    for (equipmentType localequipmentType : values()) {
      if (localequipmentType.value == paramInt) {
        return localequipmentType;
      }
    }
    return null;
  }
}