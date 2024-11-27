package via.pro3.Model;

public class Part
{
  private int id;
  private String PartType;
  private String animalid;
  private String wieght;
  private int trayId;

  public Part(int id, String partType, String animalid, String wieght,
      int trayId)
  {
    this.id = id;
    PartType = partType;
    this.animalid = animalid;
    this.wieght = wieght;
    this.trayId = trayId;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getPartType()
  {
    return PartType;
  }

  public void setPartType(String partType)
  {
    PartType = partType;
  }

  public String getAnimalid()
  {
    return animalid;
  }

  public void setAnimalid(String animalid)
  {
    this.animalid = animalid;
  }

  public String getWieght()
  {
    return wieght;
  }

  public void setWieght(String wieght)
  {
    this.wieght = wieght;
  }

  public int getTrayId()
  {
    return trayId;
  }

  public void setTrayId(int trayId)
  {
    this.trayId = trayId;
  }
}